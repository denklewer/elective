var currentUser;
getUser();
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
        $("#allCoursesH").text($.i18n.prop('allcourses'));
        $("#allCoursesH2").text($.i18n.prop('allcourses'));
        $("#coursename").text($.i18n.prop('coursename'));
        $("#trainer").text($.i18n.prop('trainer'));
        $("#startdate").text($.i18n.prop('startdate'));
        $("#enddate").text($.i18n.prop('enddate'));


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
    var button = document.createElement("button");
    button.setAttribute("class", "btn btn-sm btn-primary btn-block");
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
    refresh('courses')
    var tableBody = document.getElementById('courses');
    for (var i in courses) {
        tableBody.appendChild(createRow(courses[i]));
    }
    //paging();
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


function getCourses(limit, num){
    console.log("getCourses");
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/available_courses/" + currentUser.id + " " + limit + " " + num,
        contentType: 'application/json',
        success: function(courses){
            console.log("ok");
            createTableBody(courses);
            return true;
        },
        error: function(){
            console.log("not so ok");
            return false;
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
             getCourses(limit, num);
        },
        error: function (result)
        {
            console.log("not ok");
            console.log(result);
        }
    })
};

// $.fn.pageMe = function(opts){
//     var $this = this,
//         defaults = {
//             perPage: 7,
//             showPrevNext: false,
//             hidePageNumbers: false
//         },
//         settings = $.extend(defaults, opts);
//
//     var listElement = $this;
//     var perPage = settings.perPage;
//     var children = listElement.children();
//     var pager = $('.pager');
//
//     if (typeof settings.childSelector!="undefined") {
//         children = listElement.find(settings.childSelector);
//     }
//
//     if (typeof settings.pagerSelector!="undefined") {
//         pager = $(settings.pagerSelector);
//     }
//
//     var numItems = children.size();
//     var numPages = Math.ceil(numItems/perPage);
//
//     pager.data("curr",0);
//
//     if (settings.showPrevNext){
//         $('<li class="page-item"><a href="#" class="page-link prev">«</a></li>').appendTo(pager);
//     }
//
//     var curr = 0;
//     while(numPages > curr && (settings.hidePageNumbers==false)){
//         $('<li class="page-item"><a href="#" class="page-link curr">'+(curr+1)+'</a></li>').appendTo(pager);
//         curr++;
//     }
//
//     if (settings.showPrevNext){
//         $('<li class="page-item"><a href="#" class="page-link next">»</a></li>').appendTo(pager);
//     }
//
//     pager.find('.page_link:first').addClass('active');
//     pager.find('.prev_link').hide();
//     if (numPages<=1) {
//         pager.find('.next_link').hide();
//     }
//     pager.children().eq(1).addClass("active");
//
//     children.hide();
//     children.slice(0, perPage).show();
//
//     pager.find('li .curr').click(function(){
//         var clickedPage = $(this).html().valueOf()-1;
//         goTo(clickedPage,perPage);
//         return false;
//     });
//     pager.find('li .prev').click(function(){
//         previous();
//         return false;
//     });
//     pager.find('li .next').click(function(){
//         next();
//         return false;
//     });
//
//     function previous(){
//         var goToPage = parseInt(pager.data("curr")) - 1;
//         goTo(goToPage);
//     }
//
//     function next(){
//         goToPage = parseInt(pager.data("curr")) + 1;
//         goTo(goToPage);
//     }
//
//     function goTo(page){
//         var startAt = page * perPage,
//             endOn = startAt + perPage;
//
//         children.css('display','none').slice(startAt, endOn).show();
//
//         if (page>=1) {
//             pager.find('.prev').show();
//         }
//         else {
//             pager.find('.prev').hide();
//         }
//
//         if (page<(numPages-1)) {
//             pager.find('.next').show();
//         }
//         else {
//             pager.find('.next').hide();
//         }
//
//         pager.data("curr",page);
//         pager.children().removeClass("active");
//         pager.children().eq(page+1).addClass("active");
//
//     }
// };

var num = 0;
var limit = 10;

document.getElementById('prev').addEventListener("click", function () {
    num--;
    getCourses(limit, num);
});

document.getElementById('next').addEventListener("click", function () {
    num++;
    getCourses(limit, num);
});
