package websockts.container.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import websockts.container.AbstractChatSessionService;
import websockts.container.Container;
import websockts.container.WebSocketSessionContainer;
import websockts.container.WebSocketSessionService;
import websockts.message.Message;
import websockts.parser.Parser;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component("chatSessionService")
public class DefaultChatSessionService extends AbstractChatSessionService<Message> {

	@Autowired
	@Qualifier("chatContainer")
	private WebSocketSessionContainer container;

	@Autowired
	@Qualifier("parser")
	private Parser parser;


	public void onLine(WebSocketSession session, String id) {
		container.add(session,id);
		Collection<WebSocketSession> sessions = container.get();
		for (WebSocketSession sess:sessions) {
			if(!session.getId().equals(sess.getId())){
				try {
					sess.sendMessage(new TextMessage((id+": on-line:").getBytes()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public void offLine(String id) {
		container.remove(id);
		Collection<WebSocketSession> sessions = container.get();
		System.out.println(sessions+":" +sessions.size());
		for (WebSocketSession sess:sessions) {
			try {
				if (sess!=null) {
					sess.sendMessage(new TextMessage((id+": off-line").getBytes()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}


	protected Message parseMessage(String content) {
		return parser.stringToObject(content,Message.class);
	}

	protected void sendGroup(String id, Message message) {
		Object objectTo=message.getTo();
		if (objectTo instanceof List){
			for (String uid : (List<String>)objectTo){//将消息发送所有人
				System.out.println(uid);
				WebSocketSession session = container.get(uid);
				try {
					if (session!=null) {//当前用户还在线
						session.sendMessage(new TextMessage(((String) message.getContent()).getBytes()));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	protected void sendSingle(String id, Message message) {

	}

	/**
	 * 信息通告全部在线成员
	 * @param id
	 * @param message
	 */
	protected void sendAll(String id, Message message) {

		Collection<WebSocketSession> sessions = container.get();
		WebSocketSession sessionSelf=container.get(id);
		for (WebSocketSession sess:sessions) {
			try {
				if (sess!=null&&sess.getId()!=sessionSelf.getId()) {//当前用户还在线,且不是自己
					sess.sendMessage(new TextMessage(((String) message.getContent()).getBytes()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	

	protected void sendTypeError(String id) {

	}

}
