package com.production.app.dto;

import lombok.Data;

/**
 *
 * @author Milos
 */
@Data
public class InventoryDTO {

    private Long id;
    private String name;
    private String description;
    private String measurementUnit;
    private int quantity;
}
