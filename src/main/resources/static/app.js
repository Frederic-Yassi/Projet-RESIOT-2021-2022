var stompClient = null;

var pausebool=false ;
function setConnected(connected) {
   
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/stomp-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            //showGreeting(JSON.parse(greeting.body));
            stompClient.send("/app/knx/work", {}, JSON.stringify({'name': 'start'}));
            var positions = JSON.stringify(JSON.parse(greeting.body).message);
            var p = JSON.parse(positions);
            actualiser(p[6],p[7],p[8],p[9]);

        });
    });
    httpRequest = new XMLHttpRequest();
    httpRequest.open('GET', 'http://localhost:8013/api/conf', true);
    httpRequest.send();
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
           if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
           } else {
                alert('Il y a eu un problème avec la requête.');
          }
     }
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
function actualiser(a,b,c,d){
       $("#1").prop("src", a === "1" ? "icon/ampoule.png" : "icon/ampoule2.png" );
       $("#2").prop("src", b === "1" ? "icon/ampoule.png" : "icon/ampoule2.png" );
       $("#3").prop("src", c === "1" ? "icon/ampoule.png" : "icon/ampoule2.png" );
       $("#4").prop("src", d === "1" ? "icon/ampoule.png" : "icon/ampoule2.png" );
}


function start() {
     httpRequest = new XMLHttpRequest();
     httpRequest.open('POST', 'http://localhost:8013/api/start', true);
     httpRequest.send();
     if (httpRequest.readyState === XMLHttpRequest.DONE) {
           if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
           } else {
                alert('Il y a eu un problème avec la requête.');
          }
     }
    stompClient.send("/app/knx/work", {}, JSON.stringify({'name': 'start'}));
}

function stop() {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'http://localhost:8013/api/stop', true);
    httpRequest.send();
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
           if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
           } else {
                alert('Il y a eu un problème avec la requête.');
          }
     }
    stompClient.send("/app/knx/work", {}, JSON.stringify({'name': 'stop'}));
}

function pause() {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'http://localhost:8013/api/pause', true);
    httpRequest.send();
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
           if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
           } else {
                alert('Il y a eu un problème avec la requête.');
          }
     }
    stompClient.send("/app/knx", {}, JSON.stringify({'name': 'pause'}));
}

function goToLeft() {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'http://localhost:8013/api/left', true);
    httpRequest.send();
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
           if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
           } else {
                alert('Il y a eu un problème avec la requête.');
          }
     }
   stompClient.send("/app/knx", {}, JSON.stringify({'name': 'left'}));
}

function goToRight() {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'http://localhost:8013/api/right', true);
    httpRequest.send();
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
           if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
           } else {
                alert('Il y a eu un problème avec la requête.');
          }
     }
    stompClient.send("/app/knx", {}, JSON.stringify({'name': 'right'}));
}

function accelerate() {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'http://localhost:8013/api/accelerate', true);
    httpRequest.send();
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
           if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
           } else {
                alert('Il y a eu un problème avec la requête.');
          }
     }
    stompClient.send("/app/knx", {}, JSON.stringify({'name': 'accelerate'}));
}
function delay() {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'http://localhost:8013/api/delay', true);
    httpRequest.send();
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
           if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
           } else {
                alert('Il y a eu un problème avec la requête.');
          }
     }
    stompClient.send("/app/knx", {}, JSON.stringify({'name': 'delay'}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });

     $( "#start" ).click(function() { start(); });
     $( "#stop" ).click(function() { stop(); });
     $( "#pause" ).click(function() {
         pause();
         $("#pause").html(pausebool==false ? "Resume":"Pause");
         pausebool=!pausebool;
     });

      $( "#gauche" ).click(function() { goToLeft(); });
      $( "#droite" ).click(function() { goToRight(); });

       $( "#accelerate" ).click(function() { accelerate(); });
       $( "#delay" ).click(function() { delay(); });
});

