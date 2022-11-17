const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const { Server } = require("socket.io");
const io = new Server(server);

// private services
const UserMapper = require('./services/userMapper.js');
const GameMapper = require('./services/gameMapper.js');
const UserManager = require('./io/userManager.js');
const MsgManager = require('./io/msgManager.js');
const GameManager = require('./io/gameManager.js')

const userMapper = new UserMapper();
const gameMapper = new GameMapper(userMapper);

const userManager = new UserManager(userMapper);
const msgManager = new MsgManager(userMapper);
const gameManager = new GameManager(userMapper, gameMapper);

app.use(express.static('www'))

io.on('connection', function(socket) {

    socket.on("data", function (data) {

        switch(data.type) {
            case "user":
                console.log("...User data...");
                userManager.dispatch(socket, data.action, data.message);
                break;
            case "msg":
                console.log("...Msg data...");
                msgManager.dispatch(socket, data.action, data.message);
                break;
            case "game":
                console.log("...Game data...");
                gameManager.dispatch(socket, data.action, data.message);
                break;
            default:
                console.log("...Incorrect data...");
        }

    });

    // impossible de mettre dans le onAny
    socket.on('disconnect', () => {
        if(userMapper.check_user_connection(socket.login)) {
            userMapper.remove_user(socket.login);
            console.log(socket.login + " left.");
            socket.broadcast.emit("disconnect-event", socket.login);
        }
    });
});

server.listen(3000, () => {
    console.log('listening on *:3000');
});