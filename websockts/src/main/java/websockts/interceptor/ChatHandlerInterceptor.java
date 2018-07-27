package websockts.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class ChatHandlerInterceptor implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		ServletServerHttpRequest req=(ServletServerHttpRequest) request;
		System.out.println(req.getServletRequest().getSession().getId());
		System.out.println("websockts.interceptor.ChatHandlerInterceptor.beforeHandshake(ServerHttpRequest, ServerHttpResponse, WebSocketHandler, Map<String, Object>)");
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		System.out.println("websockts.interceptor.ChatHandlerInterceptor.afterHandshake(ServerHttpRequest, ServerHttpResponse, WebSocketHandler, Exception)");
		
	}

}
