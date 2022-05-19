GENERELLT
-----------------------------------------------------------------------------------------------------------------
- Min lokala "end to end" i detta nu. I teststadie så kör direkt in o pc:n istället för att blanda in Raspberry.

- Javascrriptet på absoluta frontend-barrikaden tillsammans med trådning ej på plats. Att ny mätdata faktiskt
  lästs in i går dock att se då jag kidnappat en av Sigruns variabler. Vid varje klick av sänd så kommer en
  timestamp:en från uspringliga rådatat att skrivas ut. Ändras var femte sekund (med nuvarande conf). 
-----------------------------------------------------------------------------------------------------------------


KICKA IGÅNG CIRKUSEN
-----------------------------------------------------------------------------------------------------------------

1) Flasha Arduinon enligt kod nedanför. Du måste importera dht-biblioteket om så inte gjorts redan.

-----------------------------------------------------------------------------------------------------------------
                                                                                                     ARDINO FLASH
-----------------------------------------------------------------------------------------------------------------
#include <dht.h>
dht DHT;
#define DHT11_PIN 4     <- Upp till ur du kopplat. Jag har kopplat in läsaren till pin 4

#define INTERVAL 5000   <- Busy Delay för nu satt till att sända ut ny mätdata var 5:e sekund. 

float temp, hum;

void setup() {
Serial.begin(9600);
}

void loop() {

  int chk = DHT.read11(DHT11_PIN);

  temp = DHT.temperature;
  hum = DHT.humidity;

  Serial.print(temp);
  Serial.print(",");
  Serial.print(hum);
  Serial.println();
 
  delay(INTERVAL);
}
------------------------------------------------------------------------------------------------------------------
                                                                                                     PYTHON-SCRIPT
------------------------------------------------------------------------------------------------------------------

2) Stäng Arduino-IDE:t så det inte stör uppkommande python-script. Från nu kommer microcontrollern stå på egna ben
   enligt flash.

3) Sparka igång python-scriptet.

   - PORT är i mitt fall com3 och just nu hårdkodat. Antar detta kan behöva ändras mellan användare (och OS).

4) Pythonscriptet läser nu konstant av USB-porten. När ett nytt paket anländer så skrivs det till filen
   current_reading.txt.
------------------------------------------------------------------------------------------------------------------
                                                                                   JAVA-APPLIKATION "SensorReader"
------------------------------------------------------------------------------------------------------------------
5) Exekvera programmet.
6) Öppna Browsern, jaga in på localhost.

7) Sigruns original, timestamp kommer att synas i den kidnappade payload-variabeln om allt funkar som det ska.
------------------------------------------------------------------------------------------------------------------
