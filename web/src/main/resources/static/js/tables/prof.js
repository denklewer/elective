var currentUser;
getUser();

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