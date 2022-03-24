package com.example.javapatternmirea2.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String mail, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail);
        message.setSubject("Test Simple Email");
        message.setText(text);

        // Send Message!
        javaMailSender.send(message);
    }
}
