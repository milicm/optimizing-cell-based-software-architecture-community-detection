package com.production.app.service.impl;

import com.production.app.dto.InventoryDTO;
import com.production.app.dto.ProductDTO;
import com.production.app.model.Production;
import com.production.app.model.ProductionInventory;
import com.production.app.repository.ProductionInventoryRepository;
import com.production.app.repository.ProductionRepository;
import com.production.app.service.ProductionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Milos
 */
@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;

    private final ProductionInventoryRepository productionInventoryRepository;

    private final RestTemplate restTemplate;
    ;

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public ProductionServiceImpl(ProductionRepository productionRepository, ProductionInventoryRepository productionInventoryRepository, RestTemplate restTemplate) {
        this.productionRepository = productionRepository;
        this.productionInventoryRepository = productionInventoryRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Production createProduction(Production production, List<ProductionInventory> inventories) {
        // Validate Product
        ProductDTO product = restTemplate.getForObject(
                productServiceUrl + "/products/" + production.getProductId(), ProductDTO.class);

        if (product == null) {
            throw new RuntimeException("Invalid product ID: " + production.getProductId());
        }

        // Validate Inventory
        for (ProductionInventory inventory : inventories) {
            InventoryDTO inventoryDTO = restTemplate.getForObject(
                    inventoryServiceUrl + "/inventories/" + inventory.getInventoryId(), InventoryDTO.class);

            if (inventoryDTO == null) {
                throw new RuntimeException("Invalid inventory ID: " + inventory.getInventoryId());
            }

            if (inventoryDTO.getQuantity() < inventory.getQuantity()) {
                throw new RuntimeException("Not enough inventory available for ID: " + inventory.getInventoryId());
            }
        }

        // Save Production
        Production savedProduction = productionRepository.save(production);

        for (ProductionInventory inventory : inventories) {
            inventory.setProductionId(savedProduction.getId());
            productionInventoryRepository.save(inventory);

            // Decrease Inventory Quantity
            restTemplate.put(inventoryServiceUrl + "/inventories/" + inventory.getInventoryId() + "/decrease/" + inventory.getQuantity(), null);
        }

        // Increase Product Quantity
        restTemplate.put(productServiceUrl + "/products/" + production.getProductId() + "/increase/1", null);

        return savedProduction;
    }

    @Override
    public int getTotalInventoryUsage(Long inventoryId) {
        return productionRepository.getTotalInventoryUsage(inventoryId);
    }

    @Override
    public int getTotalProductsProduced(Long productId) {
        return productionRepository.getTotalProductsProduced(productId);
    }

}
