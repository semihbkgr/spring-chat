package com.semihbkgr.springchat;

import io.netty.buffer.PooledByteBufAllocator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferWrapper;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

class SpringChatApplicationTests {

	@Test
	void contextLoads() throws URISyntaxException {
		WebSocketClient client = new ReactorNettyWebSocketClient();

		URI url = new URI("ws://localhost:9000/chat");
		client.execute(url, session ->
				session.send(Mono.just(new WebSocketMessage(WebSocketMessage.Type.TEXT, new NettyDataBufferFactory(new PooledByteBufAllocator()).wrap("hello".getBytes(StandardCharsets.UTF_8)))))
				.flatMap(i-> {
					return session.receive()
							.doOnNext(System.out::println)
							.then();
				})).block();
	}

}
