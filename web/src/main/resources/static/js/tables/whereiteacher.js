function createRowStud(user, course){

    console.log(user);
    var row = document.createElement("tr");
    row.appendChild(createColStud(user.id));
    row.appendChild(createColStud(user.firstName + " " + user.lastName));
    row.appendChild(createColStud(user.login));
    row.appendChild(createColStud(user.email));
    row.appendChild(createButtonStudList(user, course));
    return row;

}
function createColStud(text){

    var col = document.createElement("th");
    var content = document.createTextNode(text);
    col.appendChild(content);
    return col;
}
function createButtonStudList(user, course){

    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Feedback");
    button.appendChild(text);
    button.setAttribute("id", "button" + user.id);
    button.addEventListener("click", function(){
               var options = {
                   "backdrop" : "static"
               }
               $('#studentsModalScore').modal(options);
               document.getElementById("userName")
                        .appendChild(document
                                    .createTextNode(user.firstName + " " + user.lastName));

               document.getElementById("saveScore").addEventListener("click", function(){
                    console.log(course);
                    var studentScore = {
                        student: user,
                        course: course,
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
function createTableBodyStud(users, course){
    refresh('students');
    var tableBody = document.getElementById('students');
    for (var i in users) {
        tableBody.appendChild(createRowStud(users[i], course));
    }
}
function createRow(course){
    console.log(course);
    var row = document.createElement("tr");
    row.appendChild(createCol(course.id));
    row.appendChild(createCol(course.name));
    row.appendChild(createCol(course.start));
    row.appendChild(createCol(course.end));
    row.appendChild(createButtonStud(course));
    row.appendChild(createButtonDel(course));
    return row;
}
function createCol(text){

    var col = document.createElement("th");
    var content = document.createTextNode(text);
    col.appendChild(content);
    return col;
}
function createButtonStud(course) {

    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Students");
    button.appendChild(text);
    button.addEventListener("click", function(){
        var options = {
            "backdrop" : "static"
        }
        $('#modalStudents').modal(options);
        getUsers(course);
    });
    element.appendChild(button);
    return element;

}
function createTableBody(courses){

    refresh('courses');
    var tableBody = document.getElementById('courses');
    for (var i in courses) {
        tableBody.appendChild(createRow(courses[i]));
    }
}

function refresh(id){
    var tableBody = document.getElementById(id);
    while (tableBody.hasChildNodes()) {
        tableBody.removeChild(tableBody.firstChild);
    }
}

function createButtonDel(course){

    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Delete");
    button.appendChild(text);
    button.addEventListener("click", function(){
        deleteCourse(course);
    });
    element.appendChild(button);
    return element;

}
var deck = [
    {
        id: 1,
        name: "JavaCore",
        instructor: {
              id: 0,
              firstName: "Shipilev",
              lastName: "Alexey",
              login: "shipilev",
              password: "123456",
              email: "email"
        },
        start: "10.02.2017",
        end: "12.05.2017"
    }
];

function test() {
}

function getCourses(){
    console.log("get courses");
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/i_teach/" + currentUser.id,
        contentType: 'application/json',
        success: function(courses){
            createTableBody(courses);
            console.log("ok");
        },
        error: function(){
            createTableBody(deck);
            console.log("not ok");
        }
    })
};
function deleteCourse(course){
    $.ajax({
        type: 'Delete',
        url: "http://localhost:8080/elective/courses/" + course.id,
        data: JSON.stringify(course),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
             console.log("ok");
             console.log(result);
             getCourses();
        },
        error: function (result)
        {
            console.log("not ok");
            console.log(result);
            getCourses();
        }
    })
};
function addCourse(course){
    $.ajax({
        type: 'Post',
        url: "http://localhost:8080/elective/courses/",
        data: JSON.stringify(course),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
             console.log("ok");
             console.log(result);
             getCourses();
        },
        error: function (result)
        {
            console.log("not ok");
            console.log(result);
            getCourses();
        }
    })
};
function getUser(){

    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/current",
        contentType: 'application/json',
        success: function(user){
            console.log(user);
            currentUser = user;
            getCourses();
        },
        error: function(){
            deck[0].user;
        }
    })
};
function getUsers(course){

    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/courses/students/"+course.id,
        contentType: 'application/json',
        success: function(users){
            createTableBodyStud(users, course);
        },
        error: function(){
            createTableBodyStud(deck, course);
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

var currentUser;
getUser();
document.getElementById("add")
        .addEventListener("click", function(){
               var options = {
                   "backdrop" : "static"
               }
               $('#basicModal').modal(options);
               document.getElementById("save").addEventListener("click", function(){
                    var course = {
                        id: 0,
                        name: $('#name').val(),
                        start: $('#startDate').val(),
                        end: $('#endDate').val(),
                        user: currentUser
                    };
                    addCourse(course);
                    console.log(course);
               });
        });
