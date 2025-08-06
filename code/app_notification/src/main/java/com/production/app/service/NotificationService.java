package com.production.app.service;

import com.production.app.model.Notification;
import jakarta.mail.MessagingException;

/**
 *
 * @author Milos
 */
public interface NotificationService {
    void sendEmail(Notification notification) throws MessagingException;
}
