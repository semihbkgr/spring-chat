package com.semihbkgr.springchat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChatMessageController {

    @GetMapping("/chat")
    public String getWebSocketBroadcast() {
        return "chat";
    }

    @MessageMapping("/send")
    @SendTo("/topic/message")
    public ChatMessage send(ChatMessage chatMessage) {
        log.info("send - ChatMessage: {}", chatMessage);
        return chatMessage;
    }

    @MessageMapping("/notifications")
    @SendToUser()
    public ChatMessage notification(ChatMessage chatMessage){
        log.info("notification - ChatMessage: {}", chatMessage);
        return chatMessage;
    }

}
