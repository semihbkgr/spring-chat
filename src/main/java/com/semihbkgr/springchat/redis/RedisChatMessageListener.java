package com.semihbkgr.springchat.redis;

import com.semihbkgr.springchat.ChatMessage;
import com.semihbkgr.springchat.ChatMessageStompBrokerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RedisChatMessageListener {

    static final String LISTENER_METHOD_NAME = "onReceiveMessage";

    private final ChatMessageStompBrokerService stompBrokerService;

    public void onReceiveMessage(ChatMessage message) {
        log.info("received from redis channel - {}", message);
        stompBrokerService.send(message);

    }

}