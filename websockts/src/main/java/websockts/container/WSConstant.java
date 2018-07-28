package websockts.container;

public class WSConstant {
     //http session的名字，用于标记WebSocketSession
     public final static String HTTP_SESSION_NAME="http_session";

     //私聊
     final static int MESSAGE_TYPE_SINGLE=0;
     //组聊
     final static int MESSAGE_TYPE_GROUP=1;
     //推送通知
     final static int MESSAGE_TYPE_ALL=2;
     //第一次上线通知
     final static int MESSAGE_TYPE_ONLINE=3;
     //下线、连接断开通知
     final static int MESSAGE_TYPE_OFFLINE=4;

}
