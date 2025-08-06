package com.production.app.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author Milos
 */
@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String measurementUnit;
    private int totalUnits;
}
