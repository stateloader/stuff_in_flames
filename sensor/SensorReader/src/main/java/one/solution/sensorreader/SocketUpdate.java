package one.solution.sensorreader;

import one.solution.sensorreader.backend.DataFetcher;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

/* Trådad klass som uppdaterar läsvärdena i realtid */

public class SocketUpdate extends Thread {

  static List<WebSocketSession> sessions;
  // Lista över alla sessions i socket:en.

  DataFetcher fetcher = new DataFetcher();
  // "client-unik" instans av fetcher.

  // Variabler för att "snygga till" publisher-strängen (nedan) och därmed browserutskriften.
  static final String N = "\n";
  static final String TLABEL = "Temperature\t\t";
  static final String HLABEL = "Humidity\t\t";
  static final String CLABEL = "Timestamp\t\t";

  // Denna lista kommer att samla på sig alla värden. Del i ett (för nu) fulhack redovisat nedan.
  private List<String> packageList = new ArrayList<>();

  // Getters, Setters för packageList.
  public List<String> getPackageList() {return packageList;}
  public void setPackageList(List<String> packageList) {this.packageList = packageList;}

  public SocketUpdate(List<WebSocketSession> sessions) {
    // Tråda igång en ny session!
    this.sessions = sessions;
    this.start();
  }

  private String publish(DataFetcher fetcher) {
    // När Fetcher-attributet isUpdate (bool) flaggas så betyder det att ett nytt paket anlänt. Denna data bearbetas
    // i denna method före publicering i Browsern (fö nu). Ingen SQL/Databashantering alls på plats i detta nu men bara
    // lägga till vid intresse.

    // Placeholders för de Fetcher-attribut som vaskats fram ur (senast) inkommande paket.
    String latestPackage =
            TLABEL + fetcher.getTemperature() + N +
            HLABEL + fetcher.getHumidity() + N +
            CLABEL + fetcher.getDateTime() + N + N;

    // Paketlistan uppdateras med latestPackage.
    getPackageList().add(latestPackage);

    String allSessionRecords = "";

    // ENORMT FULHACK för nu. Eftersom jag fortfarande sitter och trycker på en (1) av variablerna (med tillhörande
    // logik) utifrån Sigruns exempelkod utan att själv ha botaniserat i frontend (javascript + html) så skapar jag vid
    // varje ny inläsning en sträng som består av samtliga tidigare inlästa paket (förevigade i packageList) genom att
    // springa över och addera dessa till strängen "allSessionRecords" i en for-loop.

    for (int i = 0; i < getPackageList().size(); i++)
      allSessionRecords += getPackageList().get(i);
    return allSessionRecords;
  }

  @Override
  public void run() {
    // Trådens run-method. "Medan sant" - fetcha data! Om det flaggats för uppdatering så kommer samtliga sessioner
    // att få ta del av dessa fina värden.
    try{
      while(true) {
        fetcher.fetchData();
        if (fetcher.isUpdate()) {
          synchronized (sessions) {
            for (WebSocketSession webSocketSession : sessions) {
              webSocketSession.sendMessage(new TextMessage(publish(fetcher)));
            }
          }
        }
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}