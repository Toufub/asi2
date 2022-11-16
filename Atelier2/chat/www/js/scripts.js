var socket = io();

//var self_login = getCookie("login");

var probable_login = ["jdoe555","jdoe444","jdoe333", "jdoe222", "jdoe111"];
var self_login = probable_login[getRandomInt(5)];

var tchatting_with = "";

var messages = document.getElementById('messages');
var form = document.getElementById('form');
var input = document.getElementById('input');

var connected_users = [];

document.addEventListener("DOMContentLoaded", function(event) {
    //setCookie("login", "jdoe222", 1);
    if(self_login) {
        socket.emit('send-login', self_login);
    }
    get_users_list();
});

form.addEventListener('submit', function(e) {
    e.preventDefault();
    if (input.value) {
        socket.emit('message', input.value);
        input.value = '';
    }
});

socket.on("forbidden", (msg) => {
    if(msg === "already-use") {
        append_message("User already connected. Sorry.");
    }
});

socket.on('message', function (msg) {
   append_message(msg);
});

socket.on('welcome-message', function(logins) {
    append_message("Welcome on tchat : " + self_login + " !")
    connected_users = logins;
    update_states_users();
});

socket.on('connect-event', function(login) {
    append_message(login + " joined us !")
    connected_users.push(login);
    update_states_users();
});

socket.on('disconnect-event', function(login) {
    append_message(login + " left us !")
    let index = connected_users.indexOf(login);
    connected_users.splice(index, 1);
    update_states_users();
});

function get_users_list() {
    document.getElementById("users").innerHTML = "<li class='list-group-item fw-bold'>Connected users :</li>";
    send_ajax_request("http://127.0.0.1:8081/users", "GET", function (users) {
        users.forEach(user =>  {
            if(user.login != self_login) {
                let li = document.createElement("li");
                li.login = user.login;
                li.classList.add('list-group-item');
                li.classList.add('user-list-item');
                li.innerHTML = user.login + " (" + user.surName + " " + user.lastName + ")";
                li.onclick = onclick_to_user;
                document.getElementById("users").append(li);
            }
        });
        update_states_users();
    });
}

function update_states_users() {
    let elems = document.getElementsByClassName("user-list-item");
    for (var i = 0; i < elems.length; i++) {
        let elem = elems[i];
        elem.classList.add("disabled");
        elem.classList.remove("pointer");
        console.log(connected_users);
        if(connected_users.includes(elem.login)) {
            elem.classList.remove("disabled");
            elem.classList.add("pointer");
        }
    }
}

function onclick_to_user() {
    if(!this.classList.contains("disabled")) {
        if(this.classList.contains("active")) {
            this.classList.remove("active");
            append_message("Your tchatting with everybody.");
            socket.emit("tchatting-with", "");
        } else {
            let elems = document.getElementsByClassName("user-list-item");
            for (var i = 0; i < elems.length; i++) {
                let elem = elems[i];
                elem.classList.remove("active");
            }
            this.classList.add("active");
            append_message("Your tchatting with " + this.login + ".");
            socket.emit("tchatting-with", this.login);
        }
    }
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

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}

function append_message(msg) {
    let item = document.createElement('li');
    item.textContent = msg;
    messages.appendChild(item);
}