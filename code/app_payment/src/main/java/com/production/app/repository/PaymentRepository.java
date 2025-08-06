package com.production.app.repository;

import com.production.app.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milos
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
