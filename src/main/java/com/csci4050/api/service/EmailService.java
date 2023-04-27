package com.csci4050.api.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.csci4050.api.model.User;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setFrom("cinecity@fastmail.com");
    msg.setTo(to); 
    msg.setSubject(subject);
    msg.setText(body);

    javaMailSender.send(msg);
  }
  
  public String confirmEmail(String email) {
	  Random rand = new Random();
      String code = String.format("%04d", rand.nextInt(10000));
      sendEmail(email, "Cine City Email Confirmation",
      "Thanks for registering an account! Please enter the code: " + code + " on the page to create your account.");
      return code;
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

  public void sendPromotion(String code, List<User> users) {
    for (User user : users) {
      if (Boolean.TRUE.equals(user.getRecievePromotions())) {
        sendEmail(user.getEmail(), "Cine City Promotion", "You have been sent a promotion, please enter the code: " + code + " on the page to redeem your promotion.");
      }
    }
  }
}
