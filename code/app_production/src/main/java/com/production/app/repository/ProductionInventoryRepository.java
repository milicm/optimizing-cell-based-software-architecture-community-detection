package com.production.app.repository;

import com.production.app.model.ProductionInventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milos
 */
public interface ProductionInventoryRepository extends JpaRepository<ProductionInventory, Long> {

}
