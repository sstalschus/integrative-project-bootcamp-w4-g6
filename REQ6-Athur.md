# Requisito 6 Projeto Integrador


Este requisito tem como finalidade implementar um modo de monitorar as alteracoes 
de temperatura nos setores em tempo real, e assim, conseguir identificar problemas de altas temperaturas que podemm acarregar em perca de lotes armazenados.

Para esta solucao foi utilizado um serviço de Web Socket que possibilita a utilizacao de inumeras ferramentas
para enviar e receber atualizacoes de temperatura de determinados setores.

### Configuracao inicial conectar no WebSocket usando JavaScript

```js
function connectToWarnNotify() {
    console.log("connecting to warn channel...")
    let socket = new SockJS(url + '/warn');
    stompClient = Stomp.over(socket);
    stompClient.connect();
}
```


## Alteracao de temperatura de um setor

Para realizar a alteracao de temperatura de um determinado setor, o cliente deve publicar no topico ``/api/v1/warn`` um objeto JSON que possua os campos de ``sectorID`` e ``temperature``.


#### Exemplo de implementacao para envio de alterecao de temperatura
```JavaScript
function sendMsg(sectorId, temperature) {
    stompClient.send("/app/warn", {}, JSON.stringify({
        sectorId: sectorId,
        temperature: temperature
    }));
}
```

#### Json enviado na menagem:

```json
{
  "sectorId": 1,
  "temperature": -3.2
}
```

## Monitorar a alteracao de temperatura de todos os setores


Caso queira monitorar as alteracoes de temperaturas dos setore, basta se inscrever no topico ``/topic/notify``, toda alteracao feita, caso ocorra de forma correta, sera publicada neste topico.

#### Exemplo de inscricao utilizando SocketJs:

```js
function subscribeTopic() {
    stompClient.subscribe("/topic/notify", function (response) {
        let data = JSON.parse(response.body);
        renderMessageFunc(data.sectorId, data.temperature, data.status);
    });
}
```

#### JSON publicados no topico :

Caso a Temperatura do seta menor ou igual a necessaria para manter seus lotes conervados
```json
{
  "sectorId": 1,
  "temperature": -3.2,
  "status": "OK"
}
```

Caso a Temperatura do seta maior que a necessaria para manter seus lotes conervados
```json
{
  "sectorId": 1,
  "temperature": 43.0,
  "status": "WARN"
}
```

|Status|Descricao|
|----|--------|
|OK|Identifica que a temperatura foi alterada com sucesso e o setor ainda esta na temperatura que possibilita a conservacao dos lotes|
|WARN|Identifica que a temperatura foi alterada com sucesso, mas que esta acima da necessaria para manter os lotes conservados