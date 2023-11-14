package com.email.email_service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.internet.MimeMessage;

public class EmailThread implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private JavaMailSender javaMailService;
    private MimeMessage message;

    public EmailThread(JavaMailSender javaMailService, MimeMessage message) {
        super();
        this.javaMailService = javaMailService;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            javaMailService.send(message);
        } catch (MailException e) {
            logger.error("Error Sending Email " + e);
        }
    }
}