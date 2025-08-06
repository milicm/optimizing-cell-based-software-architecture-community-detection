package com.production.app.controller;

import com.production.app.model.Product;
import com.production.app.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Milos
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/find-by-price/{price}")
    public List<Product> findAllByPrice(@PathVariable double price) {
        return productService.findAllByPrice(price);
    }
}
