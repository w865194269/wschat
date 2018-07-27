package websockts.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import websockts.container.SessionDispather;

public class ChatHandler extends TextWebSocketHandler{
	
	@Autowired
	@Qualifier("sessionDispatcher")
	private SessionDispather sessionDispatcher;

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handler message:"+message.getPayload());
		session.sendMessage(new TextMessage(("Server call "+message.getPayload()+": "+session.getId()).getBytes()));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished");
		sessionDispatcher.connection(session);
		System.out.println(session.getId()+":"+session.getUri());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionclosed");
	}

	
	
}
