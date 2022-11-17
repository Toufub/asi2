class GameManager {

    userMapper = null;
    gameMapper = null;

    constructor(userMapper, gameMapper) {
        this.userMapper = userMapper;
        this.gameMapper = gameMapper;
    }

    dispatch(socket, action, data) {
        switch(action) {
            case "create-game":
                this.createGame(socket, data);
                break;
            case "cancel-game":
                this.cancelGame(socket, data);
                break;
            case "accept-game":
                this.acceptGame(socket, data);
                break;
            case "deny-game":
                this.denyGame(socket, data);
                break;
            default:
                console.log("...Error...");
        }
    }

    createGame(socket, other_login) {
        let game = this.gameMapper.initialise_gaming_room(socket.login, other_login);
        if(game) {
            console.log(socket.login + " wants to fight against " + other_login + " !");
            socket.to(this.userMapper.get_socket_id(other_login)).emit("game-created", game);
            socket.emit("create-game-confirm", "ok");
        }
    }

    cancelGame(socket, other_login) {
        if(this.gameMapper.cancel_gaming_room(socket.login, other_login)) {
            console.log(socket.login + " has cancel his request to " + other_login);
            socket.to(this.userMapper.get_socket_id(other_login)).emit("game-canceled", socket.login);
            socket.emit("cancel-game-confirm", "ok");
        }
    }

    acceptGame(socket, other_login) {
        if(this.gameMapper.confirm_gaming_room(other_login, socket.login)) {
            console.log(socket.login + " accept to fight against " + other_login + " !");
            socket.to(this.userMapper.get_socket_id(other_login)).emit("game-confirm", socket.login);
            socket.emit("accept-game-confirm", "ok");
        }
    }

    denyGame(socket, other_login) {
        if(this.gameMapper.deny_gaming_room(other_login, socket.login)) {
            console.log(socket.login + " refuse to fight against " + other_login + " !");
            socket.to(this.userMapper.get_socket_id(other_login)).emit("game-deny", socket.login);
            socket.emit("deny-game-confirm", "ok");
        }
    }
}

module.exports = GameManager;