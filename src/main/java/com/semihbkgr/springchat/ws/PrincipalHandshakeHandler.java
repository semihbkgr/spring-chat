package com.semihbkgr.springchat.ws;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class PrincipalHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        var principal = IdPrincipal.uuid();
        log.info("handshake - {}", principal.getName());
        return principal;
    }

    @ToString
    @RequiredArgsConstructor
    public static class IdPrincipal implements Principal {

        private final String id;

        public static IdPrincipal uuid() {
            return new IdPrincipal(UUID.randomUUID().toString());
        }

        @Override
        public String getName() {
            return id;
        }

    }

}
