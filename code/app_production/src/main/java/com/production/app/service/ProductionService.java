package com.production.app.service;

import com.production.app.model.Production;
import com.production.app.model.ProductionInventory;
import java.util.List;

/**
 *
 * @author Milos
 */
public interface ProductionService {

    Production createProduction(Production production, List<ProductionInventory> inventories);

    int getTotalInventoryUsage(Long inventoryId);

    int getTotalProductsProduced(Long productId);

}
