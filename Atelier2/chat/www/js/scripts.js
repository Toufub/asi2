var socket = io();

var messages = document.getElementById('messages');
var form = document.getElementById('form');
var input = document.getElementById('input');

var users = [];

form.addEventListener('submit', function(e) {
    e.preventDefault();
    if (input.value) {
        socket.emit('chat message', input.value);
        input.value = '';
    }
});

socket.on('chat message', function(msg) {
    var item = document.createElement('li');
    item.textContent = msg;
    messages.appendChild(item);
    window.scrollTo(0, document.body.scrollHeight);
});

function get_users_list() {
    send_ajax_request("http://localhost:8081/users")
}

function send_ajax_request(url, method, callback) {
    const Http = new XMLHttpRequest();
    Http.open(method, url);
    Http.send();
    Http.onreadystatechange = callback;
}