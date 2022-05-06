package com.semihbkgr.springchat;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final CustomWebSocketService socketService;

    @GetMapping("/chat")
    public String getWebSocketBroadcast() {
        return "chat";
    }

    @MessageMapping("/send")
    public void send(SimpMessageHeaderAccessor sha, Principal principal, @Payload ChatMessage chatMessage) throws JsonProcessingException {
        log.info("send - name: {}, ChatMessage: {}", principal.getName(), chatMessage);
        socketService.convertAndSend(chatMessage);
    }

}
