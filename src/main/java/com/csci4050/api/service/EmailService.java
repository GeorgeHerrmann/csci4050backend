package com.csci4050.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setFrom("kartikeysankhdher@fastmail.com");
    msg.setTo(to); 
    msg.setSubject(subject);
    msg.setText(body);

    javaMailSender.send(msg);
  }
}
