var currentUser;
getUser();
getCourses();

function createRow(course){
    console.log(course);
    var row = document.createElement("tr");
    row.appendChild(createCol(course.id));
    row.appendChild(createCol(course.name));
    row.appendChild(createCol(course.instructor.firstName + " " + course.instructor.lastName));
    row.appendChild(createCol(course.start));
    row.appendChild(createCol(course.end));
    row.appendChild(createButton(course));

    return row;
}

function createCol(text){
    var col = document.createElement("th");
    var content = document.createTextNode(text);
    col.appendChild(content);
    return col;
}

function createButton(course){
    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Subscribe");
    button.appendChild(text);
    button.addEventListener("click", function(){
        var studentScore = {
            student: currentUser,
            course: course,
            score: "",
            feedback: ""
        }
        subscribe(studentScore);
    });
    element.appendChild(button);

    return element;
}

function createTableBody(courses){
    var tableBody = document.getElementById('courses');
    for (var i in courses) {
        tableBody.appendChild(createRow(courses[i]));
    }
}


var deck = [
    {
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
    },
    {
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
    },
    {
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
    }
];


function getCourses(){
    console.log("getCourses");
    getUser();
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/available_courses/" + currentUser.id,
        contentType: 'application/json',
        success: function(courses){
            console.log("ok");
            createTableBody(courses);
        },
        error: function(){
            console.log("not so ok");
            createTableBody(deck);
        }
    })
};

function getUser(){
    $.ajax({
        async: false,
        type: 'GET',
        url: "http://localhost:8080/elective/current",
        contentType: 'application/json',
        success: function(user){
            console.log(user);
            currentUser = user;
        },
        error: function(){
            deck[0].user;
        }
    })
};


function subscribe(studentScore){
    console.log(studentScore);
    $.ajax({
        type: 'Post',
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


