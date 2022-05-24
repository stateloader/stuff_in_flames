package com.example.sensor.Models;

/* Model over database-table sensor_data (som den heter i mitt experiment nere i SQL-basen) */

public class SensorData {

  private int id;
  private float temperature;
  private float humidity;
  private String timestamp;

  public SensorData() {}

  public SensorData(int id, float temperature, float humidity, String timestamp) {
    this.id = id;
    this.temperature = temperature;
    this.humidity = humidity;
    this.timestamp = timestamp;
  }

  public int getId() {return id;}
  public float getTemperature() {return temperature;}
  public float getHumidity() {return humidity;}
  public String getTimestamp() {return timestamp;}

  public void setId(int id) {this.id = id;}
  public void setTemperature(float temperature) {this.temperature = temperature;}
  public void setHumidity(float humidity) {this.humidity = humidity;}
  public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
}