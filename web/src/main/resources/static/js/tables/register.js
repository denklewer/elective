document.getElementById("register").onclick = function() {register()};

var eng = {
    lang: "Eng",
    firstname: "First name",
    lastname: "Last name",
    login: "Login",
    email: "Email",
    password: "Password",
    regWelc: "Please, register",
    regBut: "Register"
};
var ru = {
    lang: "Рус",
    firstname: "Имя",
    lastname: "Фамилия",
    login: "Логин",
    email: "Почта",
    password: "Пароль",
    regWelc: "Пожалуйста, зарегистрируйтесь",
    regBut: "Регистрация"
};

document.getElementById("lang").addEventListener("click", function () {
    var lang = $.cookie('lang');
    console.log(lang);

    var lib;

    if(lang == null){
        lang = 'ru_RU';
        lib = ru;
    }else if(lang == 'ru_RU'){
        lang = 'en_EN';
        lib = eng;
    }else{
        lang = 'ru_RU';
        lib = ru;
    }
    $.cookie('lang', lang, {
        expires: 5,
        path: '/',
    });
    $("#lang").text(lib.lang);
    $("#inputFirstName").text(lib.firstname);
    document.getElementById("inputFirstName").setAttribute("placeholder", lib.firstname);
    $("#inputLastName").text(lib.lastname);
    document.getElementById("inputLastName").setAttribute("placeholder", lib.lastname);
    $("#inputLogin").text(lib.login);
    document.getElementById("inputLogin").setAttribute("placeholder", lib.login);
    $("#inputEmail").text(lib.email);
    document.getElementById("inputEmail").setAttribute("placeholder", lib.email);
    $("#inputPassword").text(lib.password);
    document.getElementById("inputPassword").setAttribute("placeholder", lib.password);
    $("#regWelcome").text(lib.regWelc);
    $("#regBut").text(lib.regBut);


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