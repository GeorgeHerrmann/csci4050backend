package com.csci4050.api.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Create a class which features a basic email service. For Deliverable 6,
 * this class will be used to send emails to users when they register for an
 * account.
 * 
 * Implementator: Kartikey
 */

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.fastmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("cinecity@fastmail.com");
        mailSender.setPassword("p9bjap675td25zc8");

        var props = mailSender.getJavaMailProperties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}