package com.semihbkgr.springchat.config;

import com.semihbkgr.springchat.ws.AppWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.HashMap;
import java.util.Map;

@EnableWebSocket
@Configuration
public class WebSocketConfig {

    @Bean
    public HandlerMapping webSocketMapping() {
        Map<String, Object> map = new HashMap<>();
        map.put("/chat", new AppWebSocketHandler());
        return new SimpleUrlHandlerMapping(map,-1);
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter(webSocketService());
    }

    @Bean
    public WebSocketService webSocketService() {
        var strategy = new ReactorNettyRequestUpgradeStrategy();
        return new HandshakeWebSocketService(strategy);
    }

}
