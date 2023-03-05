package com.semihbkgr.springchat.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.semihbkgr.springchat.ChatMessage;
import com.semihbkgr.springchat.ChatMessageBroadcastService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("hazelcast")
@Slf4j
@RequiredArgsConstructor
public class HazelcastChatMessageBroadcastService implements ChatMessageBroadcastService {

    private final HazelcastInstance instance;

    @Override
    public void publish(@NonNull ChatMessage message) {
        log.info("publishing to hazelcast topic - {}", message);
        instance.getTopic("chat").publish(message);
    }

}
