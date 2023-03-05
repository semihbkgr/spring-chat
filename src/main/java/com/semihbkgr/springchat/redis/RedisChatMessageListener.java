package com.semihbkgr.springchat.redis;

import com.semihbkgr.springchat.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Slf4j
@RequiredArgsConstructor
public class RedisChatMessageListener {

    static final String LISTENER_METHOD_NAME = "onReceiveMessage";

    private final SimpMessagingTemplate simpTemplate;

    public void onReceiveMessage(ChatMessage message) {
        log.info("on receive - {}", message);
        simpTemplate.convertAndSendToUser(message.getSender(), "/queue/messages", message);
        simpTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
    }

}