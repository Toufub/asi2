class UserManager {

    userMapper = null;

    constructor(userMapper) {
        this.userMapper = userMapper;
    }

    dispatch(socket, action, data) {
        switch(action) {
            case "send-login":
                this.send_login(socket, data);
                break;
            default:
                console.log("...Error...");
        }
    }

    /**
     *
     * Initialisation de l'utilisateur sur le Socket
     * (Authentification)
     *
     */

    send_login(socket, login) {
        if(this.userMapper.add_user(login, socket.id)) {
            socket.login = login;
            socket.emit("welcome-message", this.userMapper.get_all_logins_connected());
            socket.broadcast.emit("connect-event", login);
            console.log(login + " join.");
        } else {
            socket.emit("forbidden", "already-use");
            socket.disconnect();
        }
    }

}

module.exports = UserManager;