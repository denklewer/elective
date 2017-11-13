
function createRow(user){
    console.log(user);
    var row = document.createElement("tr");
    row.appendChild(createCol(user.id));
    row.appendChild(createCol(user.firstName + " " + user.lastName));
    row.appendChild(createCol(user.login));
    row.appendChild(createCol(user.email));
    row.appendChild(createButton(user.id));

    return row;
}

function createCol(text){
    var col = document.createElement("th");
    var content = document.createTextNode(text);
    col.appendChild(content);
    return col;
}

function createButton(id){
    //<button class="btn btn-lg btn-primary btn-block" type="Subscribe">Sign in</button>
    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Feedback");
    button.appendChild(text);
    element.appendChild(button);

    return element;
}

function createTableBody(users){
    var tableBody = document.getElementById('students');
    for (var i in users) {
        tableBody.appendChild(createRow(users[i]));
    }
}


var users = [
    {
         id: 0,
         firstName: "Shipilev",
         lastName: "Alexey",
         login: "shipilev",
         password: "123456",
         email: "email"
    },
    {
         id: 1,
         firstName: "Shipilev",
         lastName: "Alexey",
         login: "shipilev",
         password: "123456",
         email: "email"
    },
    {
         id: 2,
         firstName: "Shipilev",
         lastName: "Alexey",
         login: "shipilev",
         password: "123456",
         email: "email"
    }

];


function getUsers(){
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/mycourses",
        contentType: 'applcation/json',
        success: function(users){
            createTableBody(users);
        },
        error: function(){
            createTableBody(users);
        }
    })
};

getUsers();
