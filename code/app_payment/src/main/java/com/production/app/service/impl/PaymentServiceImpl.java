package com.production.app.service.impl;

import com.production.app.model.Notification;
import com.production.app.model.Payment;
import com.production.app.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.production.app.service.PaymentService;

/**
 *
 * @author Milos
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    public PaymentServiceImpl(PaymentRepository paymentRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment savePayment(Payment payment) {
        Payment data = paymentRepository.save(payment);
        restTemplate.postForObject(notificationServiceUrl + "/notifications", new Notification(0L, payment.getIssuer(), payment.getCustomer(), "New Payment Saved", "Payment Status: " + payment.getStatus()), String.class);
        return data;
    }

}
