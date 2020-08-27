package com.code.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Value("$spring.mail.from")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationEmail(String email, String token) {
        String subject = "Cadastro realizado";
        String text = "Para confirmar o seu cadastro, clique no link abaixo:\n" +
                "http://localhost:8081/demo/users/activate/" + token;
        sendEmail(email, subject, text);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);
        mailSender.send(email);
    }

}
