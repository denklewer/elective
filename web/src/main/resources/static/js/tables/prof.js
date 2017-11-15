document.getElementById("change").onclick = function() {changeRegisterInfo()};


function changeRegisterInfo() {
    console.log("clicked");
    var user = {
        id: 61, // change for current id
        firstName: $('#inputFirstName').val(),
        lastName: $('#inputLastName').val(),
        login: $('#inputLogin').val(),
        password: $('#inputNewPassword').val(),
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
        type: "Put",
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