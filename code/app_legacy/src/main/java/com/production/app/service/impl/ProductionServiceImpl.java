package com.production.app.service.impl;

import com.production.app.model.Production;
import com.production.app.repository.ProductionInventoryRepository;
import com.production.app.repository.ProductionRepository;
import com.production.app.service.ProductionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milos
 */
@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final ProductionInventoryRepository productionInventoryRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository, ProductionInventoryRepository productionInventoryRepository) {
        this.productionRepository = productionRepository;
        this.productionInventoryRepository = productionInventoryRepository;
    }

    @Override
    public Production saveProduction(Production production) {
        return productionRepository.save(production);
    }

}
