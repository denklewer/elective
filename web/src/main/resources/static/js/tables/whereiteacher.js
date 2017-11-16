var currentUser;
getUser();
function createRow(course){
    console.log(course);
    var row = document.createElement("tr");
    row.appendChild(createCol(course.id));
    row.appendChild(createCol(course.name));
    row.appendChild(createCol(course.start));
    row.appendChild(createCol(course.end));
    row.appendChild(createLink(course.id));
    row.appendChild(createButton(course));
    return row;
}

function createCol(text){
    var col = document.createElement("th");
    var content = document.createTextNode(text);
    col.appendChild(content);
    return col;
}

function createLink(id) {
    var col = document.createElement("th");
    var element = document.createElement("a");
    element.href = "http://localhost:8080/pages/students.html?id="+id;
    element.innerHTML = "Students";
    col.appendChild(element);
    return col;
}

function createTableBody(courses){
    var tableBody = document.getElementById('courses');
    for (var i in courses) {
        tableBody.appendChild(createRow(courses[i]));
    }
}

function refresh(){
    var tableBody = document.getElementById('courses');
    while (tableBody.hasChildNodes()) {
        tableBody.removeChild(tableBody.firstChild);
    }
}

function createButton(course){
    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Delete");
    button.appendChild(text);
    button.addEventListener("click", function(){
        deleteCourse(course);
    });
    element.appendChild(button);

    return element;
}



var deck = [
    {
        id: 1,
        name: "JavaCore",
        instructor: {
              id: 0,
              firstName: "Shipilev",
              lastName: "Alexey",
              login: "shipilev",
              password: "123456",
              email: "email"
        },
        start: "10.02.2017",
        end: "12.05.2017"
    },
    {
        id: 1,
        name: "JavaCore",
        instructor: {
              id: 0,
              firstName: "Shipilev",
              lastName: "Alexey",
              login: "shipilev",
              password: "123456",
              email: "email"
        },
        start: "10.02.2017",
        end: "12.05.2017"
    },
    {
        id: 1,
        name: "JavaCore",
        instructor: {
              id: 0,
              firstName: "Shipilev",
              lastName: "Alexey",
              login: "shipilev",
              password: "123456",
              email: "email"
        },
        start: "10.02.2017",
        end: "12.05.2017"
    }
];


function getCourses(){
console.log("get courses");
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/courses/" + currentUser.id,
        contentType: 'application/json',
        success: function(courses){
            createTableBody(courses);
            console.log("ok");
        },
        error: function(){
            createTableBody(deck);
            console.log("not ok");
        }
    })
};
function deleteCourse(course){
    $.ajax({
        type: 'Delete',
        url: "http://localhost:8080/elective/courses/" + course.id,
        data: JSON.stringify(course),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
             console.log("ok");
             console.log(result);
             refresh();
             getCourses();
        },
        error: function (result)
        {
            console.log("not ok");
            console.log(result);
            refresh();
            getCourses();
        }
    })
};
function addCourse(course){
    $.ajax({
        type: 'Post',
        url: "http://localhost:8080/elective/courses/",
        data: JSON.stringify(course),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
             console.log("ok");
             console.log(result);
             refresh();
             getCourses();
        },
        error: function (result)
        {
            console.log("not ok");
            console.log(result);
            refresh();
            getCourses();
        }
    })
};

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

getCourses();
document.getElementById("add")
        .addEventListener("click", function(){
               var options = {
                   "backdrop" : "static"
               }
               $('#basicModal').modal(options);
               document.getElementById("save").addEventListener("click", function(){
                    var course = {
                        id: 0,
                        name: $('#name').val(),
                        start: $('#startDate').val(),
                        end: $('#endDate').val(),
                        user: currentUser
                    };
                    addCourse(course);
                    console.log(course);
               });
        });
