package com.test.Chat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Profile("test")
class ChatApplicationTests {



	@Test
	void testChatDms() throws ExecutionException, InterruptedException, TimeoutException {

		String uuid = UUID.randomUUID().toString();

		List<Transport> transports = new ArrayList<>();
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));

		WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(transports));
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSession session=stompClient.connect("ws://localhost:8080/chat", new StompSessionHandlerAdapter(){})
				.get(3, TimeUnit.SECONDS);

		BlockingQueue<ChatMessage> blockingQueue = new LinkedBlockingQueue<>();

		session.subscribe("/topic/messages/"+uuid, new CreateStompFrameHandler(blockingQueue));

		ChatMessage message= new ChatMessage("Hi,User2","User1","User2");
		session.send("/app/sendMessage/"+uuid,message);

		ChatMessage chatMessage=blockingQueue.poll(5, TimeUnit.SECONDS);
		System.out.println("chatMessage"+chatMessage);

		assertThat(chatMessage).isNotNull();
		Assertions.assertEquals("Hi,User2",chatMessage.getMessage());
		Assertions.assertEquals("User1",chatMessage.getSender());
		Assertions.assertEquals("User2",chatMessage.getReceiver());

	}

	public static class CreateStompFrameHandler implements StompFrameHandler {
		BlockingQueue<ChatMessage> queue;
		public CreateStompFrameHandler(BlockingQueue<ChatMessage> queue) {
			this.queue = queue;
		}

		@Override
		public Type getPayloadType(StompHeaders headers) {
			return ChatMessage.class;
		}

		@Override
		public void handleFrame(StompHeaders headers, Object payload) {
			System.out.printf(payload.toString());
			queue.offer((ChatMessage) payload);
		}
	}

}
