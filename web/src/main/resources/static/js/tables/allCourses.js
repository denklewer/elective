
function createRow(course){
    console.log(course);
    var row = document.createElement("tr");
    row.appendChild(createCol(course.id));
    row.appendChild(createCol(course.name));
    row.appendChild(createCol(course.teacher.firstName + " " + course.teacher.lastName));
    row.appendChild(createCol(course.start));
    row.appendChild(createCol(course.end));
    row.appendChild(createButton(course.id));

    return row;
}

function createCol(text){
    var col = document.createElement("th");
    var content = document.createTextNode(text);
    col.appendChild(content);
    return col;
}

function createButton(id){
    var element = document.createElement("th");
    var button = document.createElement("BUTTON");
    var text = document.createTextNode("Subscribe");
    button.appendChild(text);
    element.appendChild(button);

    return element;
}

function createTableBody(courses){
    var tableBody = document.getElementById('courses');
    for (var i in courses) {
        tableBody.appendChild(createRow(courses[i]));
    }
}


//var courses = [
//    {
//        id: 1,
//        name: "JavaCore",
//        teacher: {
//              id: 0,
//              firstName: "Shipilev",
//              lastName: "Alexey",
//              login: "shipilev",
//              password: "123456",
//              email: "email"
//        },
//        start: "10.02.2017",
//        end: "12.05.2017"
//    },
//    {
//        id: 1,
//        name: "JavaCore",
//        teacher: {
//              id: 0,
//              firstName: "Shipilev",
//              lastName: "Alexey",
//              login: "shipilev",
//              password: "123456",
//              email: "email"
//        },
//        start: "10.02.2017",
//        end: "12.05.2017"
//    },
//    {
//        id: 1,
//        name: "JavaCore",
//        teacher: {
//              id: 0,
//              firstName: "Shipilev",
//              lastName: "Alexey",
//              login: "shipilev",
//              password: "123456",
//              email: "email"
//        },
//        start: "10.02.2017",
//        end: "12.05.2017"
//    }
//];


function getCourses(){
    console.log("getCourses");
    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/elective/courses",
        contentType: 'application/json',
        success: function(courses){
            console.log("ok");
            createTableBody(courses);
        },
        error: function(){
            console.log("not so ok");
            //createTableBody(courses);
        }
    })
};

getCourses();
