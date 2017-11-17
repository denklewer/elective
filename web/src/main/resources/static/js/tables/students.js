var courseId = getUrlVars()["id"];

console.log(courseId);

getUsers();
var courseThat;
getCourse();

function createRowStud(user){
    console.log(user);
    var row = document.createElement("tr");
    row.appendChild(createCol(user.id));
    row.appendChild(createCol(user.firstName + " " + user.lastName));
    row.appendChild(createCol(user.login));
    row.appendChild(createCol(user.email));
    row.appendChild(createButton(user));

    return row;
}

function createColStud(text){
    var col = document.createElement("th");
    var content = document.createTextNode(text);
    col.appendChild(content);
    return col;
}

function createButtonStud(user){
    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Feedback");
    button.appendChild(text);
    button.setAttribute("id", "button" + user.id);
    button.addEventListener("click", function(){
               var options = {
                   "backdrop" : "static"
               }
               $('#basicModal').modal(options);
               document.getElementById("userName")
                        .appendChild(document
                                    .createTextNode(user.firstName + " " + user.lastName));

               document.getElementById("save").addEventListener("click", function(){
                    console.log(courseThat);
                    var studentScore = {
                        student: user,
                        course: courseThat,
                        score: $('#score').val(),
                        feedback: $('#feedback').val()
                    };
                    setScore(studentScore);
                    console.log(studentScore);


               });
           });
    element.appendChild(button);

    return element;
}

function createTableBodyStud(users){
    var tableBody = document.getElementById('students');
    for (var i in users) {
        tableBody.appendChild(createRow(users[i]));
    }
}


var deck = [
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
        url: "http://localhost:8080/elective/courses/students/"+courseId,
        contentType: 'application/json',
        success: function(users){
            createTableBody(users);
        },
        error: function(){
            createTableBody(deck);
        }
    })
};

function getCourse(){
    $.ajax({
        type: 'GET',
        async: false,
        url: "http://localhost:8080/elective/allcourses/" + courseId,
        contentType: 'application/json',
        success: function(course){
            console.log(course);
            courseThat = course;
        },
        error: function(){
            //createTableBody(users);
            return {
                id: 1,
                name: "JavaCore",
                user: {
                      id: 0,
                      firstName: "Shipilev",
                      lastName: "Alexey",
                      login: "shipilev",
                      password: "123456",
                      email: "email"
                     },
                     start: "10.02.2017",
                     end: "12.05.2017"
            };
        }
    })
};

function setScore(studentScore){
    $.ajax({
        type: 'Put',
        url: "http://localhost:8080/elective/score",
        data: JSON.stringify(studentScore),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
             console.log("ok");
             console.log(result);
        },
        error: function (result)
        {
            console.log("not ok");
            console.log(result);
        }
    })
};



function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
