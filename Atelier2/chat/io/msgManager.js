class MsgManager {

    userMapper = null;

    constructor(userMapper) {
        this.userMapper = userMapper;
    }

    dispatch(socket, action, data) {
        switch(action) {
            case "message":
                this.message(socket, data);
                break;
            case "tchatting-with":
                this.tchattingWith(socket, data);
                break;
            default:
                console.log("...Error...");
        }
    }

    /**
     *
     * Gestion de la messagerie instantannée
     * (Broadcast, message privés)
     *
     */

    message(socket, msg) {
        let dest = this.userMapper.get_tchatting_partner(socket.login);
        if(dest === false) {
            socket.broadcast.emit("message", "From " + socket.login + ": " + msg);
            socket.emit("message", "You said: " + msg);
        } else {
            socket.to(this.userMapper.get_socket_id(dest)).emit(
                "message", "From " + socket.login + " to You: " + msg);
            socket.emit("message", "You said to " + dest + ": " + msg);
        }
    }

    tchattingWith(socket, other_login) {
        if(this.userMapper.create_tchatting_room(socket.login, other_login) === false) {
            console.log(socket.login + " discuss with everybody.");
        } else {
            console.log(socket.login + " discuss with " + this.userMapper.get_tchatting_partner(socket.login) + ".");
        }
    }
}

module.exports = MsgManager;