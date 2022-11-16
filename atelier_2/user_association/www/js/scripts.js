var btn = document.getElementById('btn');
var title = document.getElementById('title');
var card_surname1 = document.getElementById('card_surname1');
var card_id1 = document.getElementById('card_id1');
var card_surname2 = document.getElementById('card_surname2');
var card_id2 = document.getElementById('card_id2');

var users = get_false_users_list();

/*** USER ***/
class User {
    constructor(id, surname){
        this.id = id;
        this.surname = surname;
    }
}

var users = [];

function SetAssociateUsers(user1, user2){
    users = [user1, user2];
}

function GetUsersAssociated(){
    return users;
}
/*** END USER ***/

// Provisionnal
function get_false_users_list() {
    var user1 = new User("1", "eloi");
    var user2 = new User("2", "totobo");
    return [user1, user2];
}

function get_users_list() {
    send_ajax_request("http://localhost:8081/users")
}

function send_ajax_request(url, method, callback) {
    const Http = new XMLHttpRequest();
    Http.open(method, url);
    Http.send();
    Http.onreadystatechange = callback;
}

btn.onclick = function() {
    console.log('on passe');
    //changeAssociatedUsers();
    //onChangeTextFields();
};

function onChangeTextFields() {
    title.textContent = "Vous avez lié " + GetUsersAssociated[0].surname + " et " + GetUsersAssociated[1].surname;
    card_surname1 = GetUsersAssociated[0].surname;
    card_surname2 = GetUsersAssociated[1].surname;
    card_id1 = GetUsersAssociated[0].id;
    card_id2 = GetUsersAssociated[1].id;
}

function changeAssociatedUsers() {
    SetAssociateUsers(users[0], users[1]);
}