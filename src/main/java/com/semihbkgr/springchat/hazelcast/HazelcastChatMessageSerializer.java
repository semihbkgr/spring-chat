package com.semihbkgr.springchat.hazelcast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.nio.serialization.ByteArraySerializer;
import com.semihbkgr.springchat.ChatMessage;

import java.io.IOException;

public class HazelcastChatMessageSerializer implements ByteArraySerializer<ChatMessage> {

    private final ObjectMapper objectMapper;

    public HazelcastChatMessageSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] write(ChatMessage object) throws IOException {
        return objectMapper.writeValueAsBytes(object);
    }

    @Override
    public ChatMessage read(byte[] buffer) throws IOException {
        return objectMapper.readValue(buffer, ChatMessage.class);
    }

    @Override
    public int getTypeId() {
        return 1;
    }

}
