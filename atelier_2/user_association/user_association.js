/*
var http = require('http');
var userControler = require('./User');

exports.my_association = function (id1, id2, surname1, surname2) {
    var user1 = userControler.GetUser(id1, surname1);
    var user2 = userControler.GetUser(id2, surname2);
    return [user1, user2];
};

http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/html'});
  res.end(
    'Hello World!'
    );
}).listen(8081);
*/
const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);

app.use(express.static('www'))

server.listen(8081, () => {
    console.log('listening on *:8081');
});