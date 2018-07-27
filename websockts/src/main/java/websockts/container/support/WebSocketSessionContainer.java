package websockts.container.support;

import java.util.Collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import websockts.container.Container;
/**
 * websocket session container
 * 
 * @author will
 *
 */
@Component("sessionContainer")
public class WebSocketSessionContainer implements Container<WebSocketSession, String>{
	
	private static final Map<String, WebSocketSession> seesionMaps= 
			new ConcurrentHashMap<String, WebSocketSession>();

	
	public void add(WebSocketSession bean) {
			seesionMaps.put(bean.getId(), bean);
	}

	public WebSocketSession get(String id) {
		return seesionMaps.get(id);
	}

	public void remove(String id) {
		seesionMaps.remove(id);
	}

	public Collection<WebSocketSession> get() {
		return seesionMaps.values();
	}
	
}
