package com.production.app.controller;

import com.production.app.model.Notification;
import com.production.app.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Milos
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public String sendNotification(@RequestBody Notification notification) {
        try {
            notificationService.sendEmail(notification);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
