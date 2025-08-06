package com.production.app.controller;

import com.production.app.model.Inventory;
import com.production.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Milos
 */
@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    private final RestTemplate restTemplate;

    @Value("${production.service.url}")
    private String productionServiceUrl;

    public InventoryController(InventoryService inventoryService, RestTemplate restTemplate) {
        this.inventoryService = inventoryService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable Long id) {
        return inventoryService.getInventory(id);
    }

    @PutMapping("/{id}/decrease/{quantity}")
    public void decreaseInventory(@PathVariable Long id, @PathVariable int quantity) {
        inventoryService.decreaseInventory(id, quantity);
    }

    @GetMapping("/{inventoryId}/usage")
    public int getInventoryUsage(@PathVariable Long inventoryId) {
        return restTemplate.getForObject(
                productionServiceUrl + "/productions/inventory-usage/" + inventoryId, Integer.class);
    }
}
