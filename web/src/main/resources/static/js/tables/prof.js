var currentUser;
getUser();

document.getElementById("change").onclick = function() {changeRegisterInfo()};


function changeRegisterInfo() {
    console.log("clicked");
    var user1 = {
        id: currentUser.id, // change for current id
        firstName: $('#inputFirstName').val(),
        lastName: $('#inputLastName').val(),
        login: $('#inputLogin').val(),
        password: $('#inputNewPassword').val(),
        email: $('#inputEmail').val()
    };

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