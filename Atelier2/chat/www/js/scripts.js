var socket = io();

var messages = document.getElementById('messages');
var form = document.getElementById('form');
var input = document.getElementById('input');

document.addEventListener("DOMContentLoaded", function(event) {
    get_users_list();
});

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
    document.getElementById("users").innerHTML = "<li><h2>Liste des utilisateurs :</h2></li>";
    send_ajax_request("http://127.0.0.1:8081/users", "GET", function (users) {
        users.forEach(user =>  {
            let row = document.createElement("row");
            let col_left = document.createElement("div");
            let col_right = document.createElement("div");
            let btn = document.createElement("button");
            btn.innerHTML = "Contacter en privé";
            btn.classList.add("btn");
            btn.classList.add("btn-primary");
            btn.classList.add("private-contact");
            row.classList.add("row");
            col_left.classList.add("col-6");
            col_left.classList.add("text-align-left");
            col_right.classList.add("col-6");
            col_right.classList.add("text-align-right");
            col_left.innerHTML = user.lastName + " " + user.surName;
            col_right.append(btn);
            row.append(col_left);
            row.append(col_right);
            let li = document.createElement("li");
            li.id = user.id;
            li.append(row);
            document.getElementById("users").append(li);
        });
    });
}

function send_ajax_request(url, method, callback) {
    const Http = new XMLHttpRequest();
    Http.open(method, url);
    Http.send();
    Http.onreadystatechange = function() {
        if (Http.readyState === XMLHttpRequest.DONE) {
            callback(JSON.parse(Http.responseText));
        }
    };
}