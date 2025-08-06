package com.production.app.service;

import com.production.app.model.Product;

/**
 *
 * @author Milos
 */
public interface ProductService {

    Product getProduct(Long productId);

    void increaseProductQuantity(Long productId, int quantity);

}
