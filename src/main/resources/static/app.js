var stompClient = null;
var currentUser = null;
var selectedUserId = null;

async function connect() {

    await getCurrentUser();

    var socket = new SockJS('/ws-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        getUsers();
        // getMessages();
        // getMessagesByReceiverId(currentUser.userId);
        console.log('user id ' + currentUser.userId);
        stompClient.subscribe('/user/' + currentUser.userId + '/queue/messages', function (message) {
            appendMessage(JSON.parse(message.body).text);
        });
        stompClient.subscribe('/user/' + currentUser.userId + '/contact', function () {
            getUsers()
        });
        stompClient.subscribe("/user/" + currentUser.userId + "/status",
            function (data) {
                getUsersWithData(JSON.parse(data.body))
            })
    });
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    $("#messages").empty()
    $("#welcome").empty()

    console.log("Disconnected");
}


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#messages").html("");
    $("#currentUser").html("");
}

var timer = null;
let isTyping = false;

function messageKeyUpHandle() {

    // if (!isTyping) {
    //     console.log('TYPING...')
    //     isTyping = true;
    // }else{
    //
    // }


    if (!isTyping) {
        console.log('start typing...')
        isTyping = true
        // let receiver = $("#userId").val();
        let receiver = selectedUserId;
        console.log(receiver);
        fetch("/api/user/" + currentUser.userId + "/" + receiver + "/typing")
            .then(response => {
                if (response.ok) {
                }
            })
    }
    clearTimeout(timer);
    timer = setTimeout(messageChangeHandle, 5000)
    console.log('changed: ' + new Date())
}

function messageChangeHandle() {
    console.log('stop!!!');
    isTyping = false;
    // let receiver = $("#userId").val();
    let receiver = selectedUserId;
    fetch("/api/user/" + currentUser.userId + "/" + receiver + "/online")
        .then(response => {
            if (response.ok) {
            }
        })
}

async function getCurrentUser() {
    //......
    await fetch("/api/user/me")
        .then(function (response) {
            if (response.ok) {
                response.json().then(body => {
                    currentUser = body
                    console.log(currentUser.fullName)
                    $('#welcome').append(currentUser.fullName)
                })
            }
        })
        .catch(e => {
            console.log(e);
        })
    //.........
}


function getMessages() {
    fetch('/api/message')
        .then(function (response) {
            if (response.ok) {
                response.json().then(body => {
                    console.log(body)
                    $("#messages").empty()
                    body.map(message => {
                        appendMessage(message.text)
                    })
                })
            }
        })
}

function getMessagesByReceiverId(receiverId) {
    selectedUserId = receiverId;
    console.log(receiverId);
    if (receiverId != null) {
        fetch('/api/message/' + receiverId)
            .then(function (response) {
                if (response.ok) {
                    response.json().then(body => {
                        $("#messages").empty()
                        body.map(message => {
                            appendMessage(message.text)
                        })
                    })
                }
            })
    } else
        $('#messages').empty()

}

function getUsers() {
    fetch('/api/user')
        .then(function (response) {
            if (response.ok) {
                response.json().then(body => {
                    console.log(body);
                    appendUsers(body)
                })
            }
        })
}

function getUsersWithData(users) {
    $('#userId').empty();
    appendUsers(users)
}


function sendMessage() {
    let dataStr = JSON.stringify({
        'text': $("#message").val(),
        // 'userId': $("#userId").val(),
        'receiverId': selectedUserId,
        'senderId': currentUser.userId
    });

    stompClient.send("/app/hello", {}, dataStr);

    // showMessage(JSON.parse(dataStr).text);

}

function receiverChangeHandle(selectedObj) {
    let selectedUserId = selectedObj.value;
    console.log(selectedUserId);
    getMessagesByUserId(selectedUserId);
}

function getMessagesByUserId(receiverId) {
    fetch('api/message/' + receiverId)
        .then(response => {
            response.json()
                .then(messages => {
                    $('#messages').empty()
                    messages.map(message => appendMessage(message.text))
                })
        })
}

function appendMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}


function appendUsers(users) {
    $('#userId').empty();
    const setActive = selectedUserId != null ? '' : 'active';
    $("#userId").append('<button onclick="getMessagesByReceiverId(null)" type="button"\n' +
        '                                class="list-group-item list-group-item-action ' + setActive + '">\n' +
        '                            Select User\n' +
        '                        </button>')
    users.map(user => {
        const setActive = selectedUserId === user.userId ? 'active' : '';
        $("#userId").append(
            '<button onclick="getMessagesByReceiverId(\'' + user.userId + '\')" type="button"\n' +
            '                                class="list-group-item list-group-item-action ' + setActive +
            '">' +
            '' + user.fullName + '  ' + user.status + '</button>'
            // '<li onclick="getMessagesByReceiverId(' + user.userId + ')" class="list-group-item">' + user.fullName + '  ' + user.status + '</li>'
        )
    })

}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendMessage();
    });

    $('#userId').on('click', '.list-group-item', function () {
        $('.list-group-item').removeClass('active');
        $(this).closest('.list-group-item').addClass('active');
    });
});