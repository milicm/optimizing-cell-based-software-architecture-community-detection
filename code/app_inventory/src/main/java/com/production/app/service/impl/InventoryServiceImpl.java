package com.production.app.service.impl;

import com.production.app.model.Inventory;
import com.production.app.repository.InventoryRepository;
import com.production.app.service.InventoryService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milos
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory getInventory(Long inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    @Override
    public void decreaseInventory(Long inventoryId, int quantity) {
        Inventory inventory = getInventory(inventoryId);
        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Not enough inventory available");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }

}
