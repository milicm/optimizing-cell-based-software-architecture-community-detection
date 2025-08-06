package com.production.app.repository;

import com.production.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milos
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
