package websockts.container;

import org.springframework.web.socket.WebSocketSession;

public interface SessionDispather {

	/**
	 * 第一次连接过来，将session加入容器
	 * @param session
	 */
	void connection(WebSocketSession session);

}
