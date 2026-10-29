package com.yuldashov.messangermail.service;

import com.yuldashov.messangermail.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListnerService {
    private static final String TOPIC_NAME = "EMAIL_SENDING_TASKS";
    private static final String GROUP_ID = "mail-messenger-group";
    private final MailService mailService;

    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
    public void listen(EmailMessage message) {
        mailService.sendEmail(message);
    }
}

