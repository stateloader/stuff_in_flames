package one.solution.sensorreader.backend;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.StringTokenizer;

/* Klass som styr all logik som rör hämtning av data från den (genom pythonscriptet) ständigt uppdaterade filen
   current_reading.txt
   */

public class DataFetcher {

  // Kalle-Anka-pathing. Ska implementera Oscars lösning sinom tid.
  private static final String FILEPATH =
          "C:\\Users\\jakob\\stateloader\\stuff_in_flames\\sensor\\SensorReader\\src\\main\\resources\\current_reading.txt";

  // Klassattribut.
  private String previous;
  private String incoming;
  private boolean isUpdate = false;

  private String dateTime;
  private String temperature;
  private String humidity;

  public DataFetcher() {}

  // Getters.
  public String getIncoming() {return incoming;}
  public String getPrevious() {return previous;}
  public boolean isUpdate() {return isUpdate;}
  public String getDateTime() {return dateTime;}
  public String getTemperature() {return temperature;}
  public String getHumidity() {return humidity;}

  // Setters.
  public void setPrevious(String previous) {this.previous = previous;}
  public void setIncoming(String incoming) {this.incoming = incoming;}
  public void setUpdate(boolean update) {this.isUpdate = update;}
  public void setDateTime(String dateTime) {this.dateTime = dateTime;}
  public void setTemperature(String temperature) {this.temperature = temperature;}
  public void setHumidity(String humidity) {this.humidity = humidity;}

  public void fetchData() {
    readFile();
    // Methoden _readFile_ anropas för att läsa senaste värdet i filen current_reading.txt. Lyckat utfört och värdet
    // läses in till variabeln "incoming" i strängformatet "temp,humid,timestamp".

    tokenize();
    // Därefter jagar logiken vidare in i methoden _tokenize_ som splittar upp denna sträng i tre variabler;
    //                    temperature, humidity, dateTime. (Klassattributen)
  }

  private void readFile() {
    /* Läser senaste värdet publicerat i current_reading.txt. */

    try {

      File file = new File(FILEPATH);       // Skapar en fil-instans.
      Scanner scanner = new Scanner(file);  // Scanner-instans för att skanna filen (raden).

      if (scanner.hasNext())
        // När javaprogrammet scannar filen så kan det hända att så görs PRECIS NÄR PYTHON-SCRIPTET UPPDATERAR/SKRIVER.
        // I det fallet finns ingen "nextLine()" varpå programmet kraschar. Det fockar i sin tur upp saker och ting
        // högre upp i stacken. Jag använder därför en scanner-instans som hedge mot detta ("if...").

        setIncoming(scanner.nextLine());
        // Om det finns en nextLine (i nära alla fall av alla) så tilldelas denna till incoming.

      if (!getIncoming().equals(getPrevious())) {
        // Gammal hederlig last/new-tilldelning/swap.

        // Om inläst incoming INTE är identiskt med föregående så innebär det att ett nytt värde i current_reading.txt
        // lästs in av python-scriptet (från fysiska temperatursensorn).

        setPrevious(getIncoming());
        // I detta fallet så kommer vi vilja uppdatera "previous" eftersom det i nästa iteration ligger till grund för
        // om incoming är ett nytt värde.

        setUpdate(true);
        // "Yay! nytt värde detekterat". Detta kommer att trigga logik högre upp i stacken som konstant sniffar efter
        // att få skriva ut stuff till browsern om denna flagg är true.

        // VIKTIGT  - Browsern; ELLER(!!!!) till DATABAS, EMAIL, UTSKRIFT PÅ PAPPER ATT POSTA TILL MORMOR <WHATEVER!>

        //          DET ENDA som KRÄVS i detta lösningsförslag är att folk med idéer om hur inläst data ämnas tas vidare
        //          skriver en egen klass med logik för ändamålet; senaste data hämtas relativt enkelt genom att ärva
        //          från, eller skapa en instans av, denna klass.

      } else
        setUpdate(false);
        // Annars så är värdet ej nytt varpå inget intressant förmedlas vidare.

      scanner.close();

    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private void tokenize() {
    /* "Tokeniserar" incoming-strängen. */

    List<String> items = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(getIncoming(), ",");
    // Som bekant så läses från current_reading.txt senast publicerade mätvärde in i formatet "temp, humid, timestamp".

    // Så vill vi inte ha, vi vill komma åt vardera av dessa värden enskilt vilket Javaklassen StringTokenizer löser
    // genom att dela upp strängen i tre delar med kommatecken som delimiter.

    while (tokenizer.hasMoreElements())
        items.add(tokenizer.nextToken());
    // Dessa tre element kommer vi nu att adda till en temporär lista - items.

    setTemperature(items.get(0)); // index 0 är *alltid temperature.
    setHumidity(items.get(1));    // index 1 är *alltid humidity.
    setDateTime(items.get(2));    // index 2 är *alltid datetime.
    // * Det såvida vi inte ändrar något längre ned i stacken; pythonscriptet eller rentav Arduino:ns flash-kod.

    // Nu kan vi "setta" respektive klassattribut som rör respektive mätdata medelst simpel indexering. Vid knackandet
    // av dessa comments så är samtliga attribut (utom "boolen") av typ "String". Konvertering till exempelvis floats
    // går dock att få till i ett nafs om så önskas; i denna klass eller logik högre upp eller sidledes mot "DataBase".
  }
}