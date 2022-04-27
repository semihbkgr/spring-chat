package com.semihbkgr.springchat.ws;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class AppWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        var output = session.receive()
                .doOnNext(message->{
                    System.out.println(message.getPayload().toString(StandardCharsets.UTF_8));
                }).concatMap(message->{
                    return Mono.just(message);
                })
                .map(value->session.textMessage("echo - "+value));
        return session.send(output);
    }

}
