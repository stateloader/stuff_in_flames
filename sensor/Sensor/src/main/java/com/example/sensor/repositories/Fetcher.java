package com.example.sensor.repositories;

import com.example.sensor.Models.SensorData;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Fetcher extends SensorData {

  private static final String CURRENT_READING_PATH =
          "C:\\Users\\jakob\\flames\\Sensor\\src\\main\\resources\\current_reading.txt";

  private String previous;
  private String incoming;
  private boolean isUpdate = false;

  public Fetcher() {}

  public String getIncoming() {return incoming;}
  public String getPrevious() {return previous;}
  public boolean isUpdate() {return isUpdate;}

  public void setPrevious(String previous) {this.previous = previous;}
  public void setIncoming(String incoming) {this.incoming = incoming;}
  public void setUpdate(boolean update) {this.isUpdate = update;}

  public void fetch() {
    scanFile();
  }

  private void scanFile() {

    try {
      File file = new File(CURRENT_READING_PATH);
      Scanner scanner = new Scanner(file);

      if (scanner.hasNext()) {
        setIncoming(scanner.nextLine());

      } if (!getIncoming().equals(getPrevious())) {
        setPrevious(getIncoming());
        tokenize();
        setUpdate(true);
      }
      scanner.close();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
  public void tokenize() {

    List<String> items = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(getIncoming(), ",");

    while (tokenizer.hasMoreElements())
      items.add(tokenizer.nextToken());

    setTemperature(Float.parseFloat(items.get(0)));
    setHumidity(Float.parseFloat(items.get(1)));
    setTimestamp(items.get(2));

  }
}