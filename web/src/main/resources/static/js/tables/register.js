document.getElementById("register").onclick = function() {register()};

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