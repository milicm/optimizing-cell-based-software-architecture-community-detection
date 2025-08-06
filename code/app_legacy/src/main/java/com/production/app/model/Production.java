package com.production.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

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
    @ManyToOne
    private Product product;
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL)
    private List<ProductionInventory> inventories;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private ProductionStatus status;
}
