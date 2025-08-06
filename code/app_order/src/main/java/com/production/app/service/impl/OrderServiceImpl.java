package com.production.app.service.impl;

import com.production.app.model.Notification;
import com.production.app.model.Order;
import com.production.app.model.Product;
import com.production.app.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Milos
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Order saveOrder(Order order) {
        Product product = findProduct(order.getProduct().getId());

        if (product == null || product.getTotalUnits() < 0) {
            throw new RuntimeException("Invalid product or total units for product ID: " + order.getProduct().getId());
        }
        return restTemplate.postForObject(orderServiceUrl + "/orders", order, Order.class);
    }

    @Override
    public String sendNotification(Notification notification) {
        return restTemplate.postForObject(notificationServiceUrl + "/notifications", notification, String.class);
    }

    @Override
    public Product findProduct(Long id) {
        return restTemplate.getForObject(productServiceUrl + "/products/" + id, Product.class);
    }

}
