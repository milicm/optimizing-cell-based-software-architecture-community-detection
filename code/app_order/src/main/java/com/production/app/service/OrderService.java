package com.production.app.service;

import com.production.app.model.Notification;
import com.production.app.model.Order;
import com.production.app.model.Product;

/**
 *
 * @author Milos
 */
public interface OrderService {

    Order saveOrder(Order order);
    
    Product findProduct(Long id);

    String sendNotification(Notification notification);
}
