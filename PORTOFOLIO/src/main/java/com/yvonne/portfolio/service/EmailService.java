package com.yvonne.portfolio.service;

import com.yvonne.portfolio.model.ContactMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final boolean enabled;
    private final String username;
    private final String password;
    private final String recipient;

    public EmailService(
            JavaMailSender mailSender,
            @Value("${portfolio.mail.enabled}") boolean enabled,
            @Value("${spring.mail.username}") String username,
            @Value("${spring.mail.password}") String password,
            @Value("${portfolio.contact.recipient}") String recipient
    ) {
        this.mailSender = mailSender;
        this.enabled = enabled;
        this.username = username;
        this.password = password;
        this.recipient = recipient;
    }

    public String sendContactMessage(ContactMessage contactMessage) {
        if (!enabled) {
            return "email_not_enabled";
        }
        if (!StringUtils.hasText(username)) {
            return "email_username_missing";
        }
        if (!StringUtils.hasText(password)) {
            return "email_password_missing";
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo(recipient);
            message.setReplyTo(contactMessage.getEmail());
            message.setSubject("Portfolio message from " + contactMessage.getFirstName() + " " + contactMessage.getLastName());
            message.setText("""
                    A new message was sent from your portfolio.

                    Name: %s %s
                    Email: %s

                    Message:
                    %s
                    """.formatted(
                    contactMessage.getFirstName(),
                    contactMessage.getLastName(),
                    contactMessage.getEmail(),
                    contactMessage.getMessage()
            ));

            mailSender.send(message);
            return "email_sent";
        } catch (MailException exception) {
            return "email_failed";
        }
    }
}
