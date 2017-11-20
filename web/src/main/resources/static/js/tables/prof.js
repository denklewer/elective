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
        $("#inputFirstName").text($.i18n.prop('firstname'));
        document.getElementById("inputFirstName").setAttribute("placeholder", $.i18n.prop('firstname'));
        $("#inputLastName").text($.i18n.prop('lastname'));
        document.getElementById("inputLastName").setAttribute("placeholder", $.i18n.prop('lastname'));
        $("#inputLogin").text($.i18n.prop('login'));
        document.getElementById("inputLogin").setAttribute("placeholder", $.i18n.prop('login'));
        $("#inputEmail").text($.i18n.prop('email'));
        document.getElementById("inputEmail").setAttribute("placeholder", $.i18n.prop('email'));
        $("#inputNewPassword").text($.i18n.prop('newpassword'));
        document.getElementById("inputNewPassword").setAttribute("placeholder", $.i18n.prop('newpassword'));
        $("#inputPassword").text($.i18n.prop('password'));
        document.getElementById("inputPassword").setAttribute("placeholder", $.i18n.prop('password'));
        $("#change").text($.i18n.prop('change'));
        $("#masgChange").text($.i18n.prop('msgcanchange'));

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



document.getElementById("change").addEventListener("click",function() {

    console.log(currentUser);
    changeRegisterInfo();
});


function changeRegisterInfo() {
    console.log("clicked");
    console.log(currentUser);
    var user1 = {
        id: currentUser.id, // change for current id
        firstName: $('#inputFirstName').val(),
        lastName: $('#inputLastName').val(),
        login: $('#inputLogin').val(),
        password: $('#inputNewPassword').val(),
        email: $('#inputEmail').val()
    };
    console.log(user1);
    if(!loginValidation(user1)) {
        return  false;
    }
    request(user1);
}

function loginValidation(user) {
//realisation needs
       return true;
}

function request(user2) {
    console.log(user2);
    $.ajax({
        url: "http://localhost:8080/elective/users",
        data: JSON.stringify(user2),
        type: "PUT",
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
    });
}

function getUser(){
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/current",
        contentType: 'application/json',
        success: function(user){
            console.log(user);
            currentUser = JSON.parse(user);

        },
        error: function(){
            deck[0].user;
        }
    })
};