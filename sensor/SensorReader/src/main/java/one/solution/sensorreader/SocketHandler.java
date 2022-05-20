package one.solution.sensorreader;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* Socket-hanterare (Bättre kortfattad förklaring) */

@Component
public class SocketHandler extends TextWebSocketHandler {
  //Thread safe list
  List <WebSocketSession>sessions = new CopyOnWriteArrayList<>();
  SocketUpdate update = new SocketUpdate(sessions);

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    //the messages will be broadcasted to all users.
    sessions.add(session);
  }
}
