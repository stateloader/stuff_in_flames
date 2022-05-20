package one.solution.sensorreader;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SensorReaderHandler extends TextWebSocketHandler {

  List <WebSocketSession>sessions = new CopyOnWriteArrayList<>();
  SensorReaderFetcher fetcher = new SensorReaderFetcher();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    sessions.add(session);
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

    fetcher.fetchData();

    Map value = new Gson().fromJson(message.getPayload(), Map.class);
    for(WebSocketSession webSocketSession : sessions) {
      webSocketSession.sendMessage(new TextMessage(fetcher.getDateTime() + value.get("name") + " !"));
    }
  }
}
