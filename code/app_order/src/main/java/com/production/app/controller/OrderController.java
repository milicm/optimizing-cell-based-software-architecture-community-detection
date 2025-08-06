package com.production.app.controller;

import com.production.app.model.Notification;
import com.production.app.model.Order;
import com.production.app.model.Product;
import com.production.app.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Milos
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping("/find-product/{id}")
    public Product findProduct(@PathVariable Long id) {
        return orderService.findProduct(id);
    }

    @PostMapping("/notify")
    public String sendNotification(@RequestBody Notification notification) {
        return orderService.sendNotification(notification);
    }

}
