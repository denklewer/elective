document.getElementById("register").onclick = function() {register()};

function register(){
    console.log("clicked");
    window.location.href = "./pages/register.html";
}

var eng = {
    lang: "Eng",
    username: "Login",
    password: "Password",
    logWelc: "Please, sign-in",
    login: "Sign-in",
    reg: "Registration"
};
var ru = {
    lang: "Рус",
    username: "Логин",
    password: "Пароль",
    logWelc: "Пожалуйста, войдите",
    login: "Вход",
    reg: "Регистрация"
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
    //document.getElementById("inputLogin").setAttribute("placeholder", lib.login);
    $("#username2").text(lib.username);
    //document.getElementById("inputEmail").setAttribute("placeholder", lib.email);
    $("#password2").text(lib.password);
    //document.getElementById("inputPassword").setAttribute("placeholder", lib.password);
    $("#logWelc").text(lib.logWelc);
    $("#log").text(lib.login);
    $("#reg").text(lib.reg);


});