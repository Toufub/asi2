const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const { Server } = require("socket.io");
const io = new Server(server);

// private services
const UserMapper = require('./services/userMapper.js');
const GameMapper = require('./services/gameMapper');

const userMapper = new UserMapper();
const gameMapper = new GameMapper(userMapper);

app.use(express.static('www'))

io.on('connection', function(socket) {

    socket.on('send-login', function (login) {
        if(userMapper.add_user(login, socket.id)) {
            socket.login = login;
            socket.emit("welcome-message", userMapper.get_all_logins_connected());
            socket.broadcast.emit("connect-event", login);
            console.log(login + " join.");
        } else {
            socket.emit("forbidden", "already-use");
            socket.disconnect();
        }
    });

    socket.on('disconnect', () => {
        if(userMapper.check_user_connection(socket.login)) {
            userMapper.remove_user(socket.login);
            console.log(socket.login + " left.");
            socket.broadcast.emit("disconnect-event", socket.login);
        }
    });

    socket.on('message', (msg) => {
        let dest = userMapper.get_tchatting_partner(socket.login);
        if(dest === false) {
            socket.broadcast.emit("message", "From " + socket.login + ": " + msg);
            socket.emit("message", "You said: " + msg);
        } else {
            socket.to(userMapper.get_socket_id(dest)).emit(
                "message", "From " + socket.login + " to You: " + msg);
            socket.emit("message", "You said to " + dest + ": " + msg);
        }
    });

    socket.on('tchatting-with', function(other_login) {
        if(userMapper.create_tchatting_room(socket.login, other_login) === false) {
            console.log(socket.login + " discuss with everybody.");
        } else {
            console.log(socket.login + " discuss with " + userMapper.get_tchatting_partner(socket.login) + ".");
        }
    });

});

server.listen(3000, () => {
    console.log('listening on *:3000');
});