package com.semihbkgr.springchat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageBroadcastService broadcastService;

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @MessageMapping("/send")
    public void send(SimpMessageHeaderAccessor headerAccessor, Principal principal, @Payload ChatMessage message) throws IOException {
        message.setSender(principal.getName());
        message.setSentAt(System.currentTimeMillis());
        log.info("on send - {}", message);
        broadcastService.send(message);
    }

}
