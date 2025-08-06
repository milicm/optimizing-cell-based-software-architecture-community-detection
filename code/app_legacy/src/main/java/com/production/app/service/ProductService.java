package com.production.app.service;

import com.production.app.model.Product;
import java.util.List;

/**
 *
 * @author Milos
 */
public interface ProductService {

    List<Product> findAll();

    List<Product> findAllByPrice(double price);

}
