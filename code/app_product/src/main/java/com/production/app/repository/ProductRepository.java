package com.production.app.repository;

import com.production.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milos
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
