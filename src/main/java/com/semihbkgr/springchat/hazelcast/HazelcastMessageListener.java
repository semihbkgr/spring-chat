package com.semihbkgr.springchat.hazelcast;

import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;
import com.semihbkgr.springchat.ChatMessage;
import com.semihbkgr.springchat.ChatMessageStompBrokerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HazelcastMessageListener implements MessageListener<ChatMessage> {

    private final ChatMessageStompBrokerService stompBrokerService;

    @Override
    public void onMessage(Message<ChatMessage> message) {
        var chatMessage = message.getMessageObject();
        log.info("received from hazelcast topic - {}", chatMessage);
        stompBrokerService.send(chatMessage);
    }

}
