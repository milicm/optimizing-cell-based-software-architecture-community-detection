package com.production.app.service.impl;

import com.production.app.model.Product;
import com.production.app.repository.ProductRepository;
import com.production.app.service.ProductService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milos
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void increaseProductQuantity(Long productId, int quantity) {
        Product product = getProduct(productId);
        product.setTotalUnits(product.getTotalUnits() + quantity);
        productRepository.save(product);
    }

}
