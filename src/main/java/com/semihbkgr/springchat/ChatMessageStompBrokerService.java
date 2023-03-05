package com.semihbkgr.springchat;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageStompBrokerService {

    private final SimpMessagingTemplate simpTemplate;

    public void send(@NonNull ChatMessage message) {
        log.info("message is sending - {}", message);
        simpTemplate.convertAndSendToUser(message.getSender(), "/queue/messages", message);
        simpTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
    }

}
