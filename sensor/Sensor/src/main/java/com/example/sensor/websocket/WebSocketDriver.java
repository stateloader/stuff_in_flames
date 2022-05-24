package com.example.sensor.websocket;

import com.example.sensor.Models.SensorData;
import com.example.sensor.repositories.Fetcher;
import com.example.sensor.utilities.Canonical;
import com.example.sensor.repositories.DatabaseDAO;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

public class WebSocketDriver extends Thread {

  static List<WebSocketSession> sessions;

  DatabaseDAO databaseDAO = new DatabaseDAO();
  Fetcher fetcher = new Fetcher();

  private List<SensorData> samples;

  public List<SensorData> getSamples() {return samples;}
  public void setSamples(List<SensorData> samples) {this.samples = samples;}


  public WebSocketDriver(List<WebSocketSession> sessions) {
    this.sessions = sessions;
    this.start();
  }

  private void runDrivers() throws IOException {

    fetcher.fetch();

    if (fetcher.isUpdate()) {
      setSamples(databaseDAO.readSamples());
      databaseDAO.writeSample(fetcher);
      broadcast();
    }
  }

  public void broadcast() throws IOException {
    Canonical canonical = new Canonical();
    canonical.canonize(getSamples());

    for (WebSocketSession webSocketSession : sessions) {
      webSocketSession.sendMessage(new TextMessage(canonical.getLatestEntry()));
      webSocketSession.sendMessage(new TextMessage(canonical.getEntireTable()));
    }
    fetcher.setUpdate(false);
  }

  @Override
  public void run() {
    try {
      while(true) {runDrivers();}
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}