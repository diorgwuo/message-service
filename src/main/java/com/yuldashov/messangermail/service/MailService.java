package com.yuldashov.messangermail.service;

import com.yuldashov.messangermail.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private static final String EMAIL_SUBJECT = "Добро пожаловать в планировщик";
    private static final String EMAIL_BODY = "Привет, ты зарегистрировался в планировщике";
    private static final String REPORT_SUBJECT = "Отчет за сутки";
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(EmailMessage message) {
        String body;
        String subject;
        switch (message.type()) {
            case WELCOME -> {
                subject = EMAIL_SUBJECT;
                body = EMAIL_BODY;
            }
            case REPORT -> {
                subject = REPORT_SUBJECT;
                body = message.body();
            }
            default -> throw new IllegalArgumentException("Unknown email type");
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(message.address());
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }
}
