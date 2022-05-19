let ws;
ws = new WebSocket('ws://localhost:8080/name');

ws.onmessage = function(event) {
    let log = document.getElementById("log");
    let message = event.data;
    log.innerHTML += message + "\n";
}

function send() {
    let content = document.getElementById("username").value;
    let json = JSON.stringify({"name":content});
    ws.send(json);
}