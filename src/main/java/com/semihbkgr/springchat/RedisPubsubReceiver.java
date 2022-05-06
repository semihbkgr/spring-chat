package com.semihbkgr.springchat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Slf4j
@RequiredArgsConstructor
public class RedisPubsubReceiver {

    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public void receiveMessage(String message) throws JsonProcessingException {
        var data = mapper.readValue(message, ChatMessage.class);
        log.info("Receiver - data: {}", data);
        template.convertAndSendToUser(data.getRecipient(), "/queue/messages", message);
    }

}