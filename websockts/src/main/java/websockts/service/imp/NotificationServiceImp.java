package websockts.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import websockts.container.WebSocketSessionService;
import websockts.service.NotificationService;

@Service("notificationService")
public class NotificationServiceImp implements NotificationService {

    @Autowired
    @Qualifier("chatSessionService")
    private WebSocketSessionService sessionService;

}
