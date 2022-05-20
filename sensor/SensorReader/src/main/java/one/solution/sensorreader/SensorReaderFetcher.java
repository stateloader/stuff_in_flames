package one.solution.sensorreader;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.StringTokenizer;

                                  /* Rad för rad förklaring på vad som försiggår */

  /* Logiken är naturligtvis uppenbar för alla men övertydlighet i rådande läge tror jag inte är till skada för någon */
                                                   /* av oss =) */

public class SensorReaderFetcher {

  // Kalle-Anka-pathing. Ska implementera Oscars lösning sinom tid.
  private static final String FILEPATH =
          "C:\\Users\\jakob\\stateloader\\stuff_in_flames\\sensor\\SensorReader\\src\\main\\resources\\current_reading.txt";

  // Klassattribut.
  private String previous;
  private String incoming;
  private boolean update;

  private String dateTime;
  private String temperature;
  private String humidity;

  // Konstruktor.
  public SensorReaderFetcher() {}

  // Getters.
  public String getIncoming() {return incoming;}
  public String getPrevious() {return previous;}
  public boolean isUpdate() {return update;}
  public String getDateTime() {return dateTime;}
  public String getTemperature() {return temperature;}
  public String getHumidity() {return humidity;}

  // Setters.
  public void setPrevious(String previous) {this.previous = previous;}
  public void setIncoming(String incoming) {this.incoming = incoming;}
  public void setUpdate(boolean update) {this.update = update;}
  public void setDateTime(String dateTime) {this.dateTime = dateTime;}
  public void setTemperature(String temperature) {this.temperature = temperature;}
  public void setHumidity(String humidity) {this.humidity = humidity;}

  public void fetchData() {
    readFile();
    // Privata methoden _readFile_ kallas på för att läsa senaste värdet i filen current_reading.txt.
    // Lyckat utfört och värdet läses in till variabeln "incoming" i trängformatet "temp,humid,timestamp".

    tokenize();
    // Därefter jagar logiken vidare in i privata methoden _tokenize_ som splittar upp denna sträng i tre variabler;
    //                    temperature, humidity, dateTime. (Klassattributen)
  }

  private void readFile() {
    /* Method som läser senaste värdet publisherat i current_reading.txt. */

    try {

      File file = new File(FILEPATH);       // Skapar en fil-instans.
      Scanner scanner = new Scanner(file);  // Scanner-instans för att skanna filen (raden).

      if (scanner.hasNext())
        // En liten notis om "varför" angående detta med hasNext().

        // När javaprogrammet scannar filen så kan det hända att så görs PRECIS NÄR PYTHON-SCRIPTET UPPDATERAR/SKRIVER.
        // I det fallet finns ingen "nextLine()" varpå programmet kraschar. Det fockar i sin tur upp saker och ting
        // högre upp i stacken.

        setIncoming(scanner.nextLine());
        // Om det däremot finns en nextLine (i nära alla fall av alla) så tilldelas denna till incoming.

      if (!getIncoming().equals(getPrevious())) {
        // Gammal hederlig last/new-tilldelning/swap.

        // Om inläst incoming INTE är identisk med föregående så innebär det att ett nytt värde i current_reading.txt
        // publisherats av python-scriptet.

        setPrevious(getIncoming());
        // I det fallet så kommer vi vilja uppdatera "previous" eftersom det i nästa iteration ligger till grund för
        // om incoming är ett nytt värde.

        setUpdate(true);
        // För nu sätter jag bool om att "yes, nytt värde är detekterat" med idén om att detta kanske kan trigga logik
        // högre upp i stacken om att "yo! nytt värde detekterat, skriv ut detta i browsern!"

            /* Browsern, ELLER(!!!!) till DATABAS, EMAIL, UTSKFIT PÅ PAPPER ATT POSTA TILL MORMOR <WHATEVER!> */

        // DET ENDA som KRÄVS i dess nuvarande form är att folk med idéer om hur detta kan tas vidare skriver
        // en egen klass för ändamålet som innehåller logik som gör detta möjligt; senaste data att trycka in i klassen
        // SendToGrandma/QueryToDatabase/SendToPrinter eller vad man nu har på hjärtat går i vilket fall relativt lätt
        // att fetcha från denna klass - SensorReaderFetcher.

      } else
        setUpdate(false);
        // Annars så är värdet ej nytt varpå

      scanner.close();

    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private void tokenize() {
    /* Method som tokeniserar incoming-strängen. */

    List<String> items = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(getIncoming(), ",");
    // Som bekant så läses från current_reading.txt senast publicherade mätvärde in som strängformatet
    //                              "temp, humid, timestamp".

    // Så vill vi inte ha, vi vill komma åt vardera av dessa värden enskilt vilket Javaklassen StringTokenizer
    // löser som i deklarationen ovan kommer dela upp strängen i tre delar med kommatecken som delimeter.


    while (tokenizer.hasMoreElements())
        items.add(tokenizer.nextToken());
    // Dessa tre "item" kommer vi nu att adda till en temporär lista - items.

    setTemperature(items.get(0)); // index 0 är *alltid temperature.
    setHumidity(items.get(1));    // index 1 är *alltid humidity.
    setDateTime(items.get(2));    // index 2 är *alltid datetime.

    // Nu kan vi "setta" respektive klassattribut som rör respektive mätdata medelst simpel indexering.
    // Vid knackandet av dessa comments så är samtliga attribut (utom "boolen") typ "string". konvertering till
    // exempelvis floats går dock att få till i ett nafs om så önskas; i denna klass eller logik högre upp.

    // * Det såvida vi inte ändrar något längre ned i stacken; pythonscriptet eller i arduinons flashkod.
  }
}