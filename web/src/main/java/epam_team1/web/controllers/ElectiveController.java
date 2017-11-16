package epam_team1.web.controllers;


import epam_team1.service.model.Course;
import epam_team1.service.model.StudentScore;
import epam_team1.service.model.User;
import epam_team1.service.services.CourseManager;
import epam_team1.service.services.StudentScoreManager;
import epam_team1.service.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import java.util.List;

@RestController
@RequestMapping(value = "/elective")
public class ElectiveController {

    @Autowired
    private UserManager userManager;
    @Autowired
    private CourseManager courseManager;
    @Autowired
    private StudentScoreManager studentScoreManager;

    private User current;

    @GetMapping("/ping")
    public ResponseEntity getPong() {
        return new ResponseEntity("pong",HttpStatus.OK);
    }

    @GetMapping("/current")
    public User getCurrent(){
        return userManager.readById(35);
    }


    @GetMapping("/users")
    public List getUsers() {
        return userManager.list();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") long id) {//user
        User user = userManager.readById(id);
        if (user == null) {
            return new ResponseEntity("No user found for ID " + id, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(user, HttpStatus.OK);
    }
  // Users
    @PostMapping(value = "/users")
    public ResponseEntity createUser(@RequestBody User user) {
        userManager.create(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userManager.deleteById(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity updateUser(@RequestBody User user) {
        User userNew = userManager.update(user);
        if (user == null) {
            return new ResponseEntity("No user found for ID " + user.getId(), HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity(userNew, HttpStatus.OK);
    }


    // Courses
    @GetMapping("/courses")
    public List getCourses() {
        List<Course> courses = courseManager.list();
        System.out.println("Controller: " + courses);
        return courses;
    }

    @GetMapping("/courses/{id}")
    public List getCourses(@PathVariable("id") long id) {
        return courseManager.listByStudentId(id);
    }

    @GetMapping("/allcourses/{id}")
    public Course getCourse(@PathVariable("id") long id) {
        return courseManager.readById(id);
    }

    @GetMapping("/courses/students/{id}")
    public List getStudentsByCourseId(@PathVariable("id") long id) {
        return userManager.getStudentsByCourseId(id);
    }

    @GetMapping("/available_courses/{id}")
    public List getCoursesExceptMine(@PathVariable("id") long id) {
        return courseManager.listByStudentIdExceptMine(id);
    }

    @GetMapping("/i_teach/{id}")
    public List getCoursesWhichITeach(@PathVariable("id") long id) {
        return courseManager.listByInstructorId(id);
    }

    @PostMapping(value = "/courses")
    public ResponseEntity createCourse(@RequestBody Course course) {
        courseManager.create(course);
        return new ResponseEntity(course, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        courseManager.deleteById(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity updateCourse(@PathVariable("id") long id, @RequestBody Course course) {
        course = courseManager.update(course);
        if (course == null) {
            return new ResponseEntity("No user found for ID " + id, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(course, HttpStatus.OK);
    }

    // StudentScores
    @GetMapping(value = "/score/{id}")
    public ResponseEntity<List<StudentScore>> getScore(@PathVariable("id") long id) {
        List<StudentScore> studentScores = studentScoreManager.list(id);
        return new ResponseEntity(studentScores, HttpStatus.OK);
    }

    @PostMapping(value = "/score")
    public ResponseEntity<StudentScore> subscribe(@RequestBody StudentScore score) {
        studentScoreManager.create(score);
        return new ResponseEntity(score, HttpStatus.OK);
    }

    @PutMapping(value = "/score")
    public ResponseEntity<StudentScore> setScore(@RequestBody StudentScore score) {
        System.out.println("Contr: " + score);
        studentScoreManager.update(score);
        return new ResponseEntity(score, HttpStatus.OK);
    }

    @DeleteMapping(value = "/score")
    public ResponseEntity<StudentScore> unsubscribe(@RequestBody StudentScore score) {
        studentScoreManager.delete(score.getStudent().getId(), score.getCourse().getId());
        return new ResponseEntity(score, HttpStatus.OK);
    }

}
