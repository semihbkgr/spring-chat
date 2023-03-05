package com.semihbkgr.springchat.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.semihbkgr.springchat.ChatMessage;
import com.semihbkgr.springchat.ChatMessageStompBrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("hazelcast")
@EnableConfigurationProperties(HazelcastConfigurationProperties.class)
@RequiredArgsConstructor
public class HazelcastTopicConfiguration {

    private final HazelcastConfigurationProperties configurationProperties;
    private final ChatMessageStompBrokerService stompBrokerService;

    @Bean
    public ClientConfig config() {
        var config = new ClientConfig();
        config.getNetworkConfig().addAddress(configurationProperties.getAddresses().toArray(new String[]{}));
        config.setClusterName(configurationProperties.getClusterName());
        config.setInstanceName(configurationProperties.getInstanceName());
        config.getSerializationConfig().addSerializerConfig(chatMessageSerializerConfig());
        return config;
    }

    @Bean
    public HazelcastInstance instance() {
        return HazelcastClient.getOrCreateHazelcastClient(config());
    }

    @Bean
    public SerializerConfig chatMessageSerializerConfig() {
        var serializerConfig = new SerializerConfig();
        serializerConfig.setClass(HazelcastChatMessageSerializer.class);
        serializerConfig.setTypeClass(ChatMessage.class);
        return serializerConfig;
    }

    @Bean
    public HazelcastMessageListener messageListener() {
        var messageListener = new HazelcastMessageListener(stompBrokerService);
        instance().<ChatMessage>getTopic("chat").addMessageListener(messageListener);
        return messageListener;
    }

}
