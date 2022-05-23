package com.example.sensor.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

/* Confen. Allt intressant händer i publish för nu =) */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(new WebSocketHandler(), "/publish");
  }
}

