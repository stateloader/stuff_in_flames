package com.example.sensor.utilities;

import com.example.sensor.Models.SensorData;

import java.util.ArrayList;
import java.util.List;

public class Canonical {

  private static final String SAMPLE_FORMAT = "Temperature\t\t%s\nHumidity\t\t%s\nTimestamp\t\t%s\n\n";

  private String latestEntry;
  private String entireTable;

  public String getLatestEntry() {return latestEntry;}
  public String getEntireTable() {return entireTable;}

  public void setLatestEntry(String latestEntry) {this.latestEntry = latestEntry;}
  public void setEntireTable(String entireTable) {this.entireTable = entireTable;}

  public Canonical() {}

  public void canonize(List<SensorData> samples) {

    List<String> entries = new ArrayList<>();

    for (int i = 0; i < samples.size(); i++) {
      String entry = String.format(
              SAMPLE_FORMAT,
              samples.get(i).getTemperature(),
              samples.get(i).getHumidity(),
              samples.get(i).getTimestamp()
      );
      entries.add(entry);
    }

    setLatestEntry(entries.get(0));

    String tableAsString = "";
    for (int i = 0; i < entries.size(); i++)
      tableAsString += entries.get(i);

    setEntireTable(tableAsString);
  }
}