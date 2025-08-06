package com.production.app.service;

import com.production.app.model.Inventory;

/**
 *
 * @author Milos
 */
public interface InventoryService {

    Inventory getInventory(Long inventoryId);

    void decreaseInventory(Long inventoryId, int quantity);

}
