stompClient = Stomp.client(`ws://${location.host}/stomp`);

stompClient.connect({}, function (res) {
    const username = res.headers["user-name"];
    document.getElementById("sender").readOnly = true
    document.getElementById("sender").value = username
    console.log("successfully connected - username: " + username)
    stompClient.subscribe('/user/queue/messages', function (message) {
        const chatMessage=JSON.parse(message.body)
        const messageElement = document.createElement("p")
        messageElement.innerText = JSON.stringify(chatMessage, null, 4)
        document.getElementById("messages").appendChild(messageElement)
    });
}, function (err) {
    console.error(`error during connecting, ${err}`)
});

document.getElementById("send").onclick = function (_) {
    const recipient = document.getElementById("recipient").value;
    if (!recipient){
        return
    }
    const text = document.getElementById("text").value;
    const chatMessage = {
        "recipient": recipient,
        "text": text
    }
    const chatMessageJson = JSON.stringify(chatMessage);
    stompClient.send("/app/send", null, chatMessageJson)
}
