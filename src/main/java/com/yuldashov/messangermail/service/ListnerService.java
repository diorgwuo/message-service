package com.yuldashov.messangermail.service;

import com.yuldashov.messangermail.dto.EmailMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ListnerService {

    private final MailServise mailServise;

    public ListnerService(MailServise mailServise) {
        this.mailServise = mailServise;
    }

    @KafkaListener(topics = "EMAIL_SENDING_TASKS", groupId = "mail-messenger-group")
    public void listen(EmailMessage message) {
        mailServise.sendEmail(message);
    }
}

