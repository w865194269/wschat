package websockts.container;

import websockts.container.support.DefaultChatSessionService;
import websockts.message.Message;

/**
 *聊天抽象
 */
public abstract class AbstractChatSessionService<T extends Message> implements WebSocketSessionService {



    public void send(String id,String content) {
        T message=parseMessage(content);

        switch (message.getType()){
            case WSConstant.MESSAGE_TYPE_SINGLE:
                sendSingle(id,message);
                break;
            case WSConstant.MESSAGE_TYPE_GROUP:
                sendGroup(id,message);
                break;
            case WSConstant.MESSAGE_TYPE_ALL:
                sendAll(id,message);
                break;
            default:
                    sendTypeError(id);
        }
    }


    protected abstract T parseMessage(String content);

    /**
     * 给组内人发消息
     */
    protected abstract void sendGroup(String id,T message);

    /**
     * 私聊
     */
    protected abstract void sendSingle(String id,T message);

    /**
     *
     */
    protected  abstract void sendAll(String id,T message);

    /**
     * 信息发送类型出错
     */
    protected  abstract void sendTypeError(String id);
}
