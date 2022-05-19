package one.solution.sensorreader;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Fetcher {

  private static final String FILEPATH =
          "C:\\Users\\jakob\\stateloader\\stuff_in_flames\\sensor\\SensorReader\\src\\main\\resources\\current_reading.txt";

  private String previous;
  private String incoming;
  private boolean update;

  private String dateTime;
  private String temperature;
  private String humidity;

  public Fetcher() {}

  public String getIncoming() {return incoming;}
  public String getPrevious() {return previous;}

  public boolean isUpdate() {return update;}

  public String getDateTime() {return dateTime;}
  public String getTemperature() {return temperature;}
  public String getHumidity() {return humidity;}

  public void setPrevious(String previous) {this.previous = previous;}
  public void setIncoming(String incoming) {this.incoming = incoming;}
  public void setUpdate(boolean update) {this.update = update;}

  public void setDateTime(String dateTime) {this.dateTime = dateTime;}
  public void setTemperature(String temperature) {this.temperature = temperature;}
  public void setHumidity(String humidity) {this.humidity = humidity;}

  public void fetchData() {
    readFile();
    tokenize();
  }

  private void readFile() {

    try {

      File file = new File(FILEPATH);
      Scanner scanner = new Scanner(file);

      if (scanner.hasNext())
        setIncoming(scanner.nextLine());
      //tokenize();

      if (!getIncoming().equals(getPrevious())) {
        setPrevious(getIncoming());
        setUpdate(true);
      } else
        setUpdate(false);

      scanner.close();

    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private void tokenize() {

    List<String> items = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(getIncoming(), ",");

    while (tokenizer.hasMoreElements())
        items.add(tokenizer.nextToken());

    setTemperature(items.get(0));
    setHumidity(items.get(1));
    setDateTime(items.get(2));
  }
}