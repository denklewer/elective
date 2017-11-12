
function createRow(course){
  console.log(course);
  var row = document.createElement("tr");
  row.appendChild(createCol(course.id));
  row.appendChild(createCol(course.name));
  row.appendChild(createCol(course.teacher.name));
  row.appendChild(createCol(course.start));
  row.appendChild(createCol(course.end));
  return row;
}

function createCol(text){
  var col = document.createElement("th");
  var content = document.createTextNode(text);
  col.appendChild(content);
  return col;
}

function createTableBody(courses){
    var tableBody = document.getElementById('myCoursesTbody');
    for (var i in courses) {
        tableBody.appendChild(createRow(courses[i]));
    }
}

var courses = [
  {
    id: 1,
    name: "JavaCore",
    teacher: {
      name: "Shipilev",
    },
    start: "10.02.2017",
    end: "12.05.2017"
  },
  {
    id: 1,
    name: "JavaCore",
    teacher: {
      name: "Shipilev",
    },
    start: "10.02.2017",
    end: "12.05.2017"
  },
  {
    id: 1,
    name: "JavaCore",
    teacher: {
      name: "Shipilev",
    },
    start: "10.02.2017",
    end: "12.05.2017"
  }
];


function getCourses(){
  $.ajax({
    type: 'GET',
    url: "http://localhost:8080/elective/courses",
    contentType: 'applcation/json',
    success: function(courses){

    },
    error: function(){
      createTableBody(courses);
    }
  })
};

getCourses();
