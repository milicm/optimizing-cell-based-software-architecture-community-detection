package com.production.app.service.impl;

import com.production.app.model.Notification;
import com.production.app.service.NotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milos
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    public NotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Notification notification) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(notification.getSender());
//        helper.setTo(notification.getReceiver());
//        helper.setSubject(notification.getSubject());
//        helper.setText(notification.getText(), true);
//        mailSender.send(message);
        System.out.println("Sending mail to " + notification.getReceiver());
    }

}
