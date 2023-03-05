package com.semihbkgr.springchat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {

    private final ChatMessageBroadcastService broadcastService;

    @MessageMapping("/send")
    public void send(SimpMessageHeaderAccessor headerAccessor, Principal principal, @Payload ChatMessage message) throws IOException {
        message.setSender(principal.getName());
        message.setSentAt(System.currentTimeMillis());
        log.info("message in controller - {}", message);
        broadcastService.publish(message);
    }

}
