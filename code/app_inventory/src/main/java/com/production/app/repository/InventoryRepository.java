package com.production.app.repository;

import com.production.app.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milos
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
