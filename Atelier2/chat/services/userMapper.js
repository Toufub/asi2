class UserMapper {

    logins_connected = [];
    logins_sockets = [];
    tchatting_rooms = [];

    constructor() {

    }

    get_all_logins_connected() {
        return this.logins_connected;
    }

    get_socket_id(login) {
        if(this.check_user_connection(login)) {
            return this.logins_sockets[login];
        } else {
            return false;
        }
    }

    check_user_connection(login) {
        return this.logins_connected.includes(login);
    }

    add_user(login, socket_id) {
        let success = false;
        if(this.logins_connected.includes(login) === false) {
            this.logins_connected.push(login);
            this.logins_sockets[login] = socket_id;
            this.tchatting_rooms[login] = false;
            success = true;
        }
        return success;
    }

    remove_user(login) {
        this.logins_connected.splice(this.logins_connected.indexOf(socket.login), 1);
        delete this.logins_sockets[socket.login];
        delete this.tchatting_rooms[login];
    }

    create_tchatting_room(login, partner) {
        let is_tchatting = false;
        if(partner !== "" && this.check_user_connection(partner)) {
            this.tchatting_rooms[login] = partner;
            is_tchatting = true;
        } else {
            this.tchatting_rooms[login] = false;
        }
        return is_tchatting;
    }

    get_tchatting_partner(login) {
        let partner = false;
        if(this.tchatting_rooms[login] !== false) {
            if(this.check_user_connection(this.tchatting_rooms[login])) {
                partner = this.tchatting_rooms[login];
            } else {
                this.tchatting_rooms[login] = false;
            }
        }
        return partner;
    }
}

module.exports = UserMapper;