package one.solution.sensorreader;

import org.springframework.web.socket.WebSocketSession;

import java.util.List;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Fetcher {

  static List<WebSocketSession> sessions;

  private static final String FILEPATH =
          "C:\\Users\\jakob\\stateloader\\stuff_in_flames\\sensor\\SensorReader\\src\\main\\resources\\current_reading.txt";

  // Två variabler. Förra mätningen. Nya mätningen. Dessa anländer i strängformatet "temp,humid,timestamp".
  private String previous;
  private String incoming;
  private boolean update;

  private String dateTime;
  private String temperature;
  private String humidity;

  // Constructor.
  public Fetcher() {}

  public String getIncoming() {return incoming;}
  public String getPrevious() {return previous;}

  public boolean isUpdate() {return update;}

  public String getDateTime() {return dateTime;}
  public String getTemperature() {return temperature;}
  public String getHumidity() {return humidity;}

  // Setters samtliga variabler deklarerade ovanför constructor.
  public void setPrevious(String previous) {this.previous = previous;}
  public void setIncoming(String incoming) {this.incoming = incoming;}
  public void setUpdate(boolean update) {this.update = update;}

  public void setDateTime(String dateTime) {this.dateTime = dateTime;}
  public void setTemperature(String temperature) {this.temperature = temperature;}
  public void setHumidity(String humidity) {this.humidity = humidity;}

  public void readFile() {

    try {

      File file = new File(FILEPATH);
      Scanner scanner = new Scanner(file);

      if (scanner.hasNext())
        setIncoming(scanner.nextLine());

      if (!getIncoming().equals(getPrevious())) {
        setPrevious(getIncoming());
        setUpdate(true);
      }
      else
        setUpdate(false);
      scanner.close();

    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  /*
  private void tokenize() {

    List<String> incomingItems = new ArrayList<>();
    StringTokenizer tokens = new StringTokenizer(getIncoming());

    while (tokens.hasMoreTokens())
        incomingItems.add(tokens.nextToken());

    setTemperature(incomingItems.get(0));
    setHumidity(incomingItems.get(1));
    setDateTime(incomingItems.get(2));
  }
  */
}