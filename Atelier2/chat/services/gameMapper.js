class GameMapper {

    user_mapper = null;
    gaming_rooms = [];

    constructor(user_mapper) {
        this.user_mapper = user_mapper;
    }

    initialise_gaming_room(initializer, dest) {
        let gaming_room = false;
        if (this.user_mapper.check_user_connection(dest)) {
            if (this.get_game(initializer, dest) === false) {
                this.gaming_rooms.push({
                    initializer: initializer,
                    dest: dest,
                    accept: false
                });
            }
            gaming_room = this.get_game(initializer,dest);
        }
        return gaming_room;
    }

    get_game(initializer, dest) {
        let index = this.get_game_index(initializer, dest);
        if (index === -1) {
            return false;
        } else {
            return this.gaming_rooms[index];
        }
    }

    get_game_index(initializer, dest) {
        return this.gaming_rooms.findIndex(object => {
            return object.initializer === initializer && object.dest === dest;
        });
    }

    confirm_gaming_room(initializer, dest) {
        let confirm = false;
        let index = this.get_game_index(initializer, dest);
        if(index >= 0) {
            if(this.gaming_rooms[index].accept === false) {
                this.gaming_rooms[index].accept = true;
                confirm = true;
            }
        }
        return confirm;
    }

    deny_gaming_room(initializer, dest) {
        let confirm = false;
        let index = this.get_game_index(initializer, dest);
        if(index >= 0) {
            if(this.gaming_rooms[index].accept === false) {
                this.gaming_rooms.splice(index, 1);
                confirm = true;
            }
        }
        return confirm;
    }

    cancel_gaming_room(initializer, dest) {
        let confirm = false;
        let index = this.get_game_index(initializer, dest)
        if(index >= 0) {
            this.gaming_rooms.splice(index, 1);
            confirm = true;
        }
        return confirm;
    }

}

module.exports = GameMapper;