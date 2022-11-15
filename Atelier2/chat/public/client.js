// Alternative to load event
document.onreadystatechange = () => {  if (document.readyState === 'complete') {    initApplication();  }}


function initApplication(){
    const socket = io("ws://localhost:3000");
    let textbox = document.getElementById("textbox");
    let messages = document.getElementById("messages");
// client-side
    socket.on("connect", () => {
        console.log(socket.id); // x8WIv7-mJelg7on_ALbx
    });

    socket.on("disconnect", () => {
        console.log(socket.id); // undefined
    });

    textbox.onchange = function () {
        let new_element = document.createElement("li");
        new_element.innerHTML = this.value;
        document.getElementById("messages").append(new_element);
        socket.emit("message", this.value)
    }

    /*

    .on("send", ()=>{
        let text = textbox.innerText;
        socket.emit("message", text)
    })*/


    socket.on("message", (message)=>{
        messages.innerText += "\n"+ message;
    })
}

