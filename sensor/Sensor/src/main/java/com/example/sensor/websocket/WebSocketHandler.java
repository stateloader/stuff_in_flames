package com.example.sensor.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

  List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
  WebSocketDriver driver = new WebSocketDriver(sessions);

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    sessions.add(session);
  }
}

