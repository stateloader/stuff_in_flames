package com.example.sensor.websocket;

import com.example.sensor.utilities.Canonical;
import com.example.sensor.utilities.DataFetcher;
import com.example.sensor.utilities.SensorData;
import com.example.sensor.repositories.DatabaseDAO;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

/* Drivers. Denna är nog den rörigaste implementationen i hela lösningsförslaget men den funkar */

public class WebSocketDriver extends Thread {

  static List<WebSocketSession> sessions;
  // Statisk lista över samtliga sessions.

  DataFetcher fetcher = new DataFetcher();
  DatabaseDAO databaseDAO = new DatabaseDAO();

  private List<SensorData> samples;
  // Listattribut som förvarar all inläsning av all data från databasen.

  // Getters/Setters.
  public List<SensorData> getSamples() {return samples;}
  public void setSamples(List<SensorData> samples) {this.samples = samples;}

  // Constructor som dammar igång sessionen. Därefter tar run()-methoden över (se längre ned).
  public WebSocketDriver(List<WebSocketSession> sessions) {
    this.sessions = sessions;
    this.start();
  }

  // Denna
  private void runDrivers() throws IOException {
    // Läser från fil. Om nytt värde detekterats följer en tillsnyggning av datat före publicering på servern.

    fetcher.readDevice();

    if (fetcher.isUpdate()) {

      setSamples(databaseDAO.readSamples());
      databaseDAO.writeSample(fetcher);
      publish();
      fetcher.setUpdate(false);
    }
  }

  private void publish() throws IOException {
    // Klassen Canonical skapar print-strängar av alla inläst data - i ena fallet senaste i det andra hela databasen.

    Canonical canonical = new Canonical();
    canonical.canonize(getSamples());

    synchronized (sessions){
      for (WebSocketSession webSocketSession : sessions) {
        webSocketSession.sendMessage(new TextMessage(canonical.getLatestEntry()));
        webSocketSession.sendMessage(new TextMessage(canonical.getEntireTable()));
      }
    }
  }
  public void run() {
    // Run-methoden. Kommer löpande köra drivarna i denna tråd.
    try {
      while(true) {runDrivers();}
    } catch(Exception exception){
      exception.printStackTrace();
    }
  }
}