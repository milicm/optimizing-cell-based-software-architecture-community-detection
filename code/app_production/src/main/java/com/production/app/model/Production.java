package com.production.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Milos
 */
@Entity
@Data
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String customer;
    private Long productId;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private ProductionStatus status;
}
