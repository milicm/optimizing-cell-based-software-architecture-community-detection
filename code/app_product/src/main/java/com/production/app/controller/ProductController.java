package com.production.app.controller;

import com.production.app.model.Product;
import com.production.app.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Milos
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final RestTemplate restTemplate;

    @Value("${production.service.url}")
    private String productionServiceUrl;

    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}/increase/{quantity}")
    public void increaseProductQuantity(@PathVariable Long id, @PathVariable int quantity) {
        productService.increaseProductQuantity(id, quantity);
    }

    @GetMapping("/{productId}/produced")
    public int getTotalProductsProduced(@PathVariable Long productId) {
        return restTemplate.getForObject(
                productionServiceUrl + "/productions/product-produced/" + productId, Integer.class);
    }
}
