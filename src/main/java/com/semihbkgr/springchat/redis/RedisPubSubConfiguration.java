package com.semihbkgr.springchat.redis;

import com.semihbkgr.springchat.ChatMessage;
import com.semihbkgr.springchat.ChatMessageStompBrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@Profile("redis")
@RequiredArgsConstructor
public class RedisPubSubConfiguration {

    private final ChatMessageStompBrokerService stompBrokerService;

    @Bean
    public RedisSerializer<ChatMessage> chatMessageSerializer() {
        return new Jackson2JsonRedisSerializer<>(ChatMessage.class);
    }

    @Bean
    public RedisTemplate<Long, ChatMessage> template(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Long, ChatMessage> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(chatMessageSerializer());
        return template;
    }

    @Bean
    public RedisChatMessageListener chatMessageListener() {
        return new RedisChatMessageListener(stompBrokerService);
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        var messageListenerAdapter = new MessageListenerAdapter(chatMessageListener());
        messageListenerAdapter.setDefaultListenerMethod(RedisChatMessageListener.LISTENER_METHOD_NAME);
        messageListenerAdapter.setSerializer(chatMessageSerializer());
        return messageListenerAdapter;
    }

    @Bean
    public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter(), new ChannelTopic("chat"));
        return container;
    }

}