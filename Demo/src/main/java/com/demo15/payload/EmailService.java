package com.demo15.payload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String sub, String msg){
        SimpleMailMessage s = new SimpleMailMessage();
        s.setTo(to);
        s.setFrom("saptarshikarmakar1998@gmail.com");
        s.setSubject(sub);
        s.setText(msg);

        javaMailSender.send(s);
    }
}
