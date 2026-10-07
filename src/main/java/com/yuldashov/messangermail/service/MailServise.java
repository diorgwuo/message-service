package com.yuldashov.messangermail.service;

import com.yuldashov.messangermail.dto.EmailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServise {
    private final JavaMailSender mailSender;


    public MailServise(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(EmailMessage message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(message.getAddress());
        mailMessage.setSubject(message.getTitle());
        mailMessage.setText(message.getBody());

        mailSender.send(mailMessage);
    }

}
