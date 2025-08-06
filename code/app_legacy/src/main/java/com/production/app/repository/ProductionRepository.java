package com.production.app.repository;

import com.production.app.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milos
 */
public interface ProductionRepository extends JpaRepository<Production, Long> {

}
