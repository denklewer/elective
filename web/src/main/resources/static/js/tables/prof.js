document.getElementById("change").onclick = function() {changeRegisterInfo()};


function changeRegisterInfo(){
    console.log("clicked");
    window.location = "/pages/prof.html";

    var user = {
        id: 61, // change for current id
        firstName: $('#inputFirstName').val(),
        lastName: $('#inputLastName').val(),
        login: $('#inputLogin').val(),
        password: $('#inputNewPassword').val(),
        email: $('#inputEmail').val()
    }

    if(!loginValidation(user)){
        return  false;
    }
    request(user);
}

function loginValidation(user){
//realisation needs
       return true;
}

function request(user)
{
    $.ajax({
        url: "localhost:8080/elective/login",
        data: user,
        type: "Put",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            //loadData();
            //$('#myModal1').modal('hide');
             //alert('Login Successful');
             console.log(result);

        },

        error: function (result)
        {

            //$('#myModal1').modal('hide');
            //alert("Invalid Credentials");
            console.log(logininfo);
        }
    });
}