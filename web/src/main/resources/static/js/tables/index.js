document.getElementById("register").onclick = function() {register()};
document.getElementById("sign-in").onclick = function() {signIn()};

function register(){
    console.log("clicked");
    window.location = "/pages/prof.html";
}

function loginValidation(){
//realisation needs
       return true;
}

function signIn()
{

    var res = loginValidation()
    if (res == false)
    { return false; }
       var logininfo = {
        Username: $('#inputLogin').val(),
        PasswordHash: $('#inputPassword').val(),
     };
    $.ajax({
        url: "localhost:8080/elective/login",
        data: JSON.stringify(logininfo),
        type: "Post",
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