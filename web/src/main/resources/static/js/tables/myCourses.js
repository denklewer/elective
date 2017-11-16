
var currentUser;
getUser();
getCourses();
getScore();

function createRow(course){
    console.log(course);
    var row = document.createElement("tr");
    row.appendChild(createCol(course.id));
    row.appendChild(createCol(course.name));
    row.appendChild(createCol(course.instructor.firstName + " " + course.instructor.lastName));
    row.appendChild(createCol(course.start));
    row.appendChild(createCol(course.end));
    row.appendChild(createButtonSub(course));
    return row;
}

function createCol(text){
    var col = document.createElement("th");
    var content = document.createTextNode(text);
    col.appendChild(content);
    return col;
}

function createTableBody(courses){
    var tableBody = document.getElementById('myCoursesTbody');
    for (var i in courses) {
        tableBody.appendChild(createRow(courses[i]));
    }
}

function createButtonSub(course){
    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Unsubscribe");
    button.appendChild(text);
    button.addEventListener("click", function(){
        var studentScore = {
            student: currentUser,
            course: course,
            score: "",
            feedback: ""
        }
        unsubscribe(studentScore);
    });
    element.appendChild(button);

    return element;
}


function createScoreRow(score){
    console.log(score);
    var row = document.createElement("tr");
    row.appendChild(createCol(score.course.name));
    row.appendChild(createCol(score.score));
    row.appendChild(createCol(score.feedback));
    return row;
}

function createScoreTableBody(scores){
    var tableBody = document.getElementById('feedbackTbody');
    for (var i in scores) {
        tableBody.appendChild(createScoreRow(scores[i]));
    }
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
    },
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
    },
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


function getCourses(){
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/courses/" + currentUser.id,
        contentType: 'application/json',
        success: function(courses){
            console.log(courses);
            createTableBody(courses);
        },
        error: function(){
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

var testScore = [
    {
        course: {
            name: "Swimming"
        },
        score: 1,
        feedback: "You need to swim deeper"
    },
    {
        course: {
            name: "Darts"
        },
        score: 1,
        feedback: "You need to go better"
    },
    {
        course: {
            name: "Swimming"
        },
        score: 1,
        feedback: "You need to go better"
    }




];


function getScore(){
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/score/" + currentUser.id,
        contentType: 'application/json',
        success: function(scores){
            console.log(scores)
            createScoreTableBody(scores);
        },
        error: function(result){
            console.log(result);
            createScoreTableBody(testScore);
        }
    })
};


function unsubscribe(studentScore){
    $.ajax({
        type: 'Delete',
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
