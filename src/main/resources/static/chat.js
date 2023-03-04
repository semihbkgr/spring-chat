class ChatMessage {

    constructor(from, text, recipient) {
        this.from = from
        this.text = text
        this.recipient = recipient
    }

}

stompClient = Stomp.client(`ws://${location.host}/stomp`);

stompClient.connect({}, function (res) {
    const username = res.headers["user-name"];
    document.getElementById("from").readOnly = true
    document.getElementById("from").value = username
    console.log("successfully connected - username: " + username)
    stompClient.subscribe('/user/queue/messages', function (output) {
        document.getElementById("messages").innerText += output + "\n"
    });
}, function (err) {
    console.error("error during connecting")
});

document.getElementById("send").onclick = function (_) {
    const from = document.getElementById("from").value;
    const text = document.getElementById("text").value;
    const recipient = document.getElementById("recipient").value;
    const chatMessage = new ChatMessage(from, text, recipient);
    const chatMessageJson = JSON.stringify(chatMessage);
    stompClient.send("/app/send", null, chatMessageJson)
}