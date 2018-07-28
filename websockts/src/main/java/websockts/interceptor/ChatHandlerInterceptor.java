package websockts.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import websockts.container.WSConstant;

import javax.servlet.http.HttpSession;

public class ChatHandlerInterceptor implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		ServletServerHttpRequest req=(ServletServerHttpRequest) request;
		HttpSession session = req.getServletRequest().getSession();
		if (session!=null){
			attributes.put(WSConstant.HTTP_SESSION_NAME,session.getId());
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {

	}

}
