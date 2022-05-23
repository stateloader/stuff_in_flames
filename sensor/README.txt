Ingen kod i pythonscriptet eller arduinon är ändrad på.

----

Det som främst hänt sedan sist är att applikationen nu läser och skriver från en databas.

Nuvarande lösning är knappast "DRY":ad och publicerandet bygger fortfarande på fulhack. I sin nuvarande form uppfyller detta
lösningsexempel VG-kraven, åtminstone som jag tolkat dem. Oavsett vad så kanske nått går att använda i någon av alla
våra övriga implementationer.

------

Källkoden orienteras genom 3 packet.

	1 repositories
      2 utilities
	3 websocket

1)

  Här hittar vi klassen "DatabaseDAO" som läser- och skriker till databasen på det vis vi gjort tidigare i kursen.

    DATABASEN:    

    CREATE TABLE sensor_data(
    id int not null auto_increment primary key,
    temperature DECIMAL(4,2),
    humidity DECIMAL(4,2),
    timestamp VARCHAR(30));

2)

  Här ligger klasserna "Canonical", "DataFetcher" och "DataSample".

   - "Datafetch" är densamma som senast (då som Fetcher).

   - "SensorData" är modellen över motsvarande tabell i databasen enligt kursen övriga practice.

   - "Canonical" mest rörig. Enda som man behöver veta om denna är att jag efter att ha läst från databas kanoniserar
      datat till strängar (fulhacket) som javascript accepterar.

3) 

  Websocket-klasserna, samma som innan fast utan "tryck-logik". I nuvarande form så sköter sig allt automatiskt.

-------

I övrigt en liten css- och html-fil outöver app.js.


    NOTIS!
    I detta nu så får nånting i koden programmet att skriva två instanser av varje mätning till databasen. Detta problem uppstod
    verkligen precis mot slutet av denna kodsession efter att fungerat hela vägen. Fortsätter felsöka, Jätteretligt.
