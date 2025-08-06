package com.production.app.service.impl;

import com.production.app.model.Product;
import com.production.app.repository.ProductRepository;
import com.production.app.service.ProductService;
import java.util.List;
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
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByPrice(double price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }

}
