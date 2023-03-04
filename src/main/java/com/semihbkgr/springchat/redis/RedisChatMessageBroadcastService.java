package com.semihbkgr.springchat.redis;

import com.semihbkgr.springchat.ChatMessage;
import com.semihbkgr.springchat.ChatMessageBroadcastService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisChatMessageBroadcastService implements ChatMessageBroadcastService {

    private final RedisTemplate<Long, ChatMessage> redisTemplate;

    @Override
    public void send(@NonNull ChatMessage message) {
        log.info("on broadcast - {}", message);
        redisTemplate.convertAndSend("chat", message);
    }

}