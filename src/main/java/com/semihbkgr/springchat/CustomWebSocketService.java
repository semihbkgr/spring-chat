package com.semihbkgr.springchat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomWebSocketService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper mapper;

    public void convertAndSend(ChatMessage chatMessage) throws JsonProcessingException {
        String data = mapper.writeValueAsString(chatMessage);
        log.info("Broadcaster - data: {}", data);
        redisTemplate.convertAndSend("chat", data);
    }

}