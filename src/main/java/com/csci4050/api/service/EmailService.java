package com.csci4050.api.service;

import java.util.Random;

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
  
  public void confirmEmail(String email) {
	  Random rand = new Random();
      String code = String.format("%04d", rand.nextInt(10000));
      sendEmail(email, "Cine City Email Confirmation",
      "Thanks for registering an account! Please enter the code: " + code + " on the page to create your account.");
  }
  
  public void updatePassword(String email) {
	  sendEmail(email, "Cine City Password Update", "Your password has been updated, if you did not make this change please contact us immediately.");
  }
  
  public void resetPassword(String email) {
	 sendEmail(email, "Cine City Password Reset",
		        "You have requested a password reset, please click the link below to reset your password. \n" +
		        "http://localhost:3000/resetpassword");
  }
  
  public void updateUser(String email) {
	  sendEmail(email, "Cine City Account Update", "Your account has been updated, if you did not make this change please contact us immediately.");
  }
}
