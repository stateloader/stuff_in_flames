package one.solution.sensorreader;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public class NumberGenerator extends Thread {

  static List<WebSocketSession> sessions;

  public NumberGenerator (List<WebSocketSession> sessions){
    this.sessions = sessions;
    this.start();
  }

  @Override
  public void run() {
    try{
      while(true) {
        Thread.sleep(1000);
        Fetcher fetcher = new Fetcher();
        fetcher.fetchData();
        synchronized (sessions){
          for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(fetcher.getIncoming()));
          }
        }
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }

  }
}
