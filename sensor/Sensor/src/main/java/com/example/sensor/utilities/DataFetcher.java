package com.example.sensor.utilities;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.StringTokenizer;

/* Klass som fetchar senaste data från current_reading. Notera arvet från DataSample vilket förklaras närmare i methoden
   tokenize */

public class DataFetcher extends SensorData {

  private static final String CURRENT_READING_PATH =
          "C:\\Users\\jakob\\flames\\Sensor\\src\\main\\resources\\current_reading.txt";

  private String previous;
  private String incoming;
  private boolean isUpdate = false;

  public DataFetcher() {}

  public String getIncoming() {return incoming;}
  public String getPrevious() {return previous;}
  public boolean isUpdate() {return isUpdate;}
  public void setPrevious(String previous) {this.previous = previous;}
  public void setIncoming(String incoming) {this.incoming = incoming;}
  public void setUpdate(boolean update) {this.isUpdate = update;}

  public void readDevice() {
    readFile();
    tokenize();
  }

  private void readFile() {
    // Läser från current_reading.txt.

    try {
      File file = new File(CURRENT_READING_PATH);
      Scanner scanner = new Scanner(file);

      if (scanner.hasNext())
        setIncoming(scanner.nextLine());
      if (!getIncoming().equals(getPrevious())) {
        setPrevious(getIncoming());
        setUpdate(true);
      }
      scanner.close();

    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private void tokenize() {
    // Tokeniserar rådata-strängen (nu i formatet "temp,humid,time") och "setter" attributen. Ärver från DataSample -
    // modelklassen för databasen. Detta kändes naturligt eftersom denna klass i annat fall hade behövt skapa samma
    // variabler samt getters/setters.

    List<String> items = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(getIncoming(), ",");

    while (tokenizer.hasMoreElements())
      items.add(tokenizer.nextToken());

    setTemperature(Float.parseFloat(items.get(0)));
    setHumidity(Float.parseFloat(items.get(1)));
    setTimestamp(items.get(2));

    // Nytt för denna gång är datatypskonvertering för att SQL ska vara med på noterna.
  }
}

