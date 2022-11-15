const bodyParser = require('body-parser')

const express = require("express");
const app = express();
const server = require('http').Server(app);
const io = require('socket.io')(server);
const port = 3000;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.use(express.static('public'));

app.get('/', function (req, res) {   res.sendFile('index.html', { root: __dirname })});

app.get('/json', function (req, res) {   res.status(200).json({"message":"ok"})});// établissement de la connexion
//Whenever someone connects this gets executed
io.on('connection', function(socket) {
    console.log('A user connected');

    //Whenever someone disconnects this piece of code executed
    socket.on('disconnect', function () {
        console.log('A user disconnected');
    });
    socket.on("message", function (message) {
        console.log(message);
    })
});
server.listen(port, function () { console.log('Votre app est disponible sur localhost:3000 !')});
