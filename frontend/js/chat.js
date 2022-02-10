const url = 'http://localhost:8080/api/v1';
let stompClient;

function connectToChat() {
    console.log("connecting to warn channel...")
    let socket = new SockJS(url + '/warn');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/topic/notify", function (response) {
            let data = JSON.parse(response.body);
            render(data.sectorId, data.temperature, data.status);
        });
    });
}

function sendMsg(sectorId, temperature) {
    stompClient.send("/app/warn", {}, JSON.stringify({
        sectorId: sectorId,
        temperature: temperature
    }));
}
