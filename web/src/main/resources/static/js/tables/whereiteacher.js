
function getLang(){
    var lang = $.cookie('lang');
    console.log(lang);

    if(lang == null){
        return 'en_EN';
    }

    return lang;
}

var langProp = {
    name: 'Messeges',
    path: '/',
    mode: 'both',
    language: getLang(),
    callback: function() {
        $("#profile").text($.i18n.prop('profile'));
        $("#mycourses").text($.i18n.prop('mycourses'));
        $("#teached").text($.i18n.prop('teached'));
        $("#allcourses").text($.i18n.prop('allcourses'));

        $("#profilepills").text($.i18n.prop('profile'));
        $("#mycoursespills").text($.i18n.prop('mycourses'));
        $("#teachedpills").text($.i18n.prop('teached'));
        $("#allcoursespills").text($.i18n.prop('allcourses'));

        $("#profileHeader").text($.i18n.prop('profile'));

        $("#lang").text($.i18n.prop('lang'));
        $("#teachednav").text($.i18n.prop('teached'));
        $("#teachedh2").text($.i18n.prop('teached'));
        $("#coursename").text($.i18n.prop('coursename'));
        $("#startdate").text($.i18n.prop('startdate'));
        $("#enddate").text($.i18n.prop('enddate'));
        $("#startdate2").text($.i18n.prop('startdate'));
        $("#enddate2").text($.i18n.prop('enddate'));
        $("#add").text($.i18n.prop('addcourse'));
        $("#studentsh").text($.i18n.prop('students'));
        $("#namem1").text($.i18n.prop('name'));
        $("#name").text($.i18n.prop('name'));
        $("#loginm1").text($.i18n.prop('login'));
        $("#emailm1").text($.i18n.prop('email'));
        $("#close1").text($.i18n.prop('close'));
        $("#close2").text($.i18n.prop('close'));
        $("#close3").text($.i18n.prop('close'));
        $("#newcourse").text($.i18n.prop('newcourse'));
        $("#saveScore").text($.i18n.prop('save'));
        $("#save").text($.i18n.prop('save'));
        $("#delete").text($.i18n.prop('deleteDel'));
        $("#studentsBut").text($.i18n.prop('students'));
        $("#feedbackBut").text($.i18n.prop('feedback'));


    }
};


$.i18n.properties(langProp);

document.getElementById("lang").addEventListener("click", function () {
    var lang = $.cookie('lang');
    console.log(lang);

    if(lang == null){
        lang = 'ru_RU';
    }else if(lang == 'ru_RU'){
        lang = 'en_EN';
    }else{
        lang = 'ru_RU';
    }
    $.cookie('lang', lang, {
        expires: 5,
        path: '/',
    });
    langProp.language = lang;
    $.i18n.properties(langProp);

});




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
    button.setAttribute("class", "btn btn-sm btn-primary btn-block");
    button.setAttribute("id", "feedbackBut");
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
    button.setAttribute("class", "btn btn-sm btn-primary btn-block");
    button.setAttribute("id", "studentsBut");
    var text = document.createTextNode("Students");
    button.appendChild(text);
    button.addEventListener("click", function(){
        var options = {
            "backdrop" : "static"
        }
        $('#modalStudents').modal(options);
        getUsers(course);
        document.getElementById('prevst').addEventListener("click", function () {
            numSt--;
            getUsers(course);
        });

        document.getElementById('nextst').addEventListener("click", function () {
            numSt++;
            getUsers(course);
        });
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
    button.setAttribute("class", "btn btn-sm btn-primary btn-block");
    button.setAttribute("id", "delete");
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


function getCourses(){
    console.log("get courses");
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/i_teach/" + currentUser.id + " " + limit + " " + num,
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
        url: "http://localhost:8080/elective/courses/students/" + course.id + " " + limitSt + " " + numSt,
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


var num = 0;
var limit = 4;

document.getElementById('prev').addEventListener("click", function () {
    num--;
    getCourses();
});

document.getElementById('next').addEventListener("click", function () {
    num++;
    getCourses();
});

var numSt = 0;
var limitSt = 4;

