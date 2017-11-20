document.getElementById("register").onclick = function() {register()};

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


        $("#lang").text($.i18n.prop('lang'));
        $("#inputFirstName").text($.i18n.prop('firstname'));
        document.getElementById("inputFirstName").setAttribute("placeholder", $.i18n.prop('firstname'));
        $("#inputLastName").text($.i18n.prop('lastname'));
        document.getElementById("inputLastName").setAttribute("placeholder", $.i18n.prop('lastname'));
        $("#inputLogin").text($.i18n.prop('login'));
        document.getElementById("inputLogin").setAttribute("placeholder", $.i18n.prop('login'));
        $("#inputEmail").text($.i18n.prop('email'));
        document.getElementById("inputEmail").setAttribute("placeholder", $.i18n.prop('email'));
        $("#inputPassword").text($.i18n.prop('password'));
        document.getElementById("inputPassword").setAttribute("placeholder", $.i18n.prop('password'));
        $("#regWelcome").text($.i18n.prop('regWelcome'));
        $("#regBut").text($.i18n.prop('registerBut'));

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

function register() {
    console.log("clicked");
    var user = {
        id: 0,
        firstName: $('#inputFirstName').val(),
        lastName: $('#inputLastName').val(),
        login: $('#inputLogin').val(),
        password: $('#inputPassword').val(),
        email: $('#inputEmail').val()
    }

    if(!loginValidation(user)) {
        return  false;
    }
    request(user);
}

function loginValidation(user) {
//realisation needs
       return true;
}

function request(user) {
    console.log("request");
    $.ajax({
        url: "http://localhost:8080/elective/users",
        data: JSON.stringify(user),
        type: "Post",
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