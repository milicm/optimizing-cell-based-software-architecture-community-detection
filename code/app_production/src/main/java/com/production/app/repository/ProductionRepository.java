package com.production.app.repository;

import com.production.app.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Milos
 */
@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {

    @Query("SELECT COALESCE(SUM(pi.quantity), 0) FROM ProductionInventory pi WHERE pi.inventoryId = :inventoryId")
    int getTotalInventoryUsage(@Param("inventoryId") Long inventoryId);

    @Query("SELECT COUNT(p) FROM Production p WHERE p.productId = :productId")
    int getTotalProductsProduced(@Param("productId") Long productId);

}
