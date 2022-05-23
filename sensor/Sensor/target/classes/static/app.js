let ws = new WebSocket('ws://localhost:8080/publish');

// Medan jag skriver detta så består "dashboarden" av två fält, senaste värde och en databas som uppdateras löpande.
// För att förgrena den data som läses ut till rätt destination så kommer all data under 65 length att landa i
// latest sample, annars table (all mätdata i databasen).

ws.onmessage = function(event) {

    let latest = document.getElementById("latest");
    let table = document.getElementById("table");

    let message = event.data;

    if (message.length <= 65)
        latest.innerHTML = message + "\n";
    else
        table.innerHTML = message + "\n";
}