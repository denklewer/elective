
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
        $("#coursename").text($.i18n.prop('coursename'));
        $("#trainer").text($.i18n.prop('trainer'));
        $("#startdate").text($.i18n.prop('startdate'));
        $("#enddate").text($.i18n.prop('enddate'));

        $("#myCoursesh2").text($.i18n.prop('mycourses'));
        $("#myCoursesh").text($.i18n.prop('mycourses'));
        $("#coursenamef").text($.i18n.prop('coursename'));
        $("#scoref").text($.i18n.prop('score'));
        $("#feedbackf").text($.i18n.prop('feedback'));
        $("#myfeed").text($.i18n.prop('myfeed'));

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


var currentUser;
getUser();

var num = 0;
var limit = 4;

document.getElementById('prev').addEventListener("click", function () {
    num--;
    getCourses(limit, num);
    getScore(limit, num);
});

document.getElementById('next').addEventListener("click", function () {
    num++;
    getCourses(limit, num);
    getScore(limit, num);
});

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
    refresh('myCoursesTbody');
    var tableBody = document.getElementById('myCoursesTbody');
    for (var i in courses) {
        tableBody.appendChild(createRow(courses[i]));
    }
}

function createButtonSub(course){
    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    button.setAttribute("class", "btn btn-sm btn-primary btn-block");
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
    refresh('feedbackTbody');
    var tableBody = document.getElementById('feedbackTbody');
    for (var i in scores) {
        tableBody.appendChild(createScoreRow(scores[i]));
    }
}

function refresh(id){
    var tableBody = document.getElementById(id);
    while (tableBody.hasChildNodes()) {
        tableBody.removeChild(tableBody.firstChild);
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
    }
];


function getCourses(limit, num){
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/courses/" + currentUser.id + " " + limit + " " + num,
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
        type: 'GET',
        url: "http://localhost:8080/elective/current",
        contentType: 'application/json',
        success: function(user){
            console.log(user);
            currentUser = user;
            getCourses(limit, num);
            getScore(limit, num);
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


function getScore(limit, num){
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/score/" + currentUser.id + " " + limit + " " + num,
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
             getCourses(limit, num);
             getScore(limit, num);
        },
        error: function (result)
        {
            console.log("not ok");
            console.log(result);
        }
    })
};
