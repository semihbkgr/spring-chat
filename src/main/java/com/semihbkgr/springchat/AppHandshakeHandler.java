package com.semihbkgr.springchat;

import com.sun.security.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.thymeleaf.util.StringUtils;

import java.security.Principal;
import java.util.Map;

@Slf4j
public class AppHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        var username = StringUtils.randomAlphanumeric(9);
        log.info("Handshake - username: {}", username);
        return new UserPrincipal(username);
    }

}
