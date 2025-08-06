package com.production.app.service.impl;

import com.production.app.model.Order;
import com.production.app.repository.OrderRepository;
import com.production.app.service.OrderService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milos
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

}
