let ws = new WebSocket('ws://localhost:8080/publish');

ws.onmessage = function(event) {

    let latest = document.getElementById("latest");
    let table = document.getElementById("table");

    let message = event.data;

    if (message.length <= 65)
        latest.innerHTML = message;
    else
        table.innerHTML = message;
}