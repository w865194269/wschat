package websockts.container.support;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import websockts.container.SessionDispather;

@Component("sessionDispatcher")
public class WebSocketSessionDispatcher implements SessionDispather{
	
	public void connection(WebSocketSession session) {
		
	}

}
