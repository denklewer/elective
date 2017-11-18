package epam_team1.web.controllers;


import epam_team1.service.model.Course;
import epam_team1.service.model.StudentScore;
import epam_team1.service.model.User;
import epam_team1.service.services.CourseManager;
import epam_team1.service.services.StudentScoreManager;
import epam_team1.service.services.UserManager;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
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
    public ResponseEntity<HelloweenResponse> hello(Principal principal) {

        return new ResponseEntity<HelloweenResponse>(
                new HelloweenResponse("Happy Halloween, " + principal.getName() + "!"), HttpStatus.OK);
    }

    public static class HelloweenResponse {
        private String message;
        public HelloweenResponse(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }

    @GetMapping("/current")
    public User getCurrent(Principal principal){
        return userManager.readByLogin(principal.getName());
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        return userManager.list();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") long id) {//user
        User user = userManager.readById(id);
        if (user == null) {
            return new ResponseEntity<Object>("No user found for ID " + id, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }
  // Users
    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userManager.create(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        userManager.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        User userNew = userManager.update(user);
        if (user == null) {
            return new ResponseEntity<>("No user found for ID " + user.getId(), HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<Object>(userNew, HttpStatus.OK);
    }


    // Courses
    @GetMapping("/courses")
    public List<Course> getCourses() {
        List<Course> courses = courseManager.list();
        System.out.println("Controller: " + courses);
        return courses;
    }

    @GetMapping("/courses/{id}")
    public List<Course> getCourses(@PathVariable("id") long id) {
        return courseManager.listByStudentId(id);
    }

    @GetMapping("/allcourses/{id}")
    public Course getCourse(@PathVariable("id") long id) {
        return courseManager.readById(id);
    }

    @GetMapping("/courses/students/{id}")
    public List<User> getStudentsByCourseId(@PathVariable("id") long id) {
        return userManager.getStudentsByCourseId(id);
    }

    @GetMapping("/available_courses/{id}")
    public List<Course> getCoursesExceptMine(@PathVariable("id") long id) {
        return courseManager.listByStudentIdExceptMine(id);
    }

    @GetMapping("/i_teach/{id}")
    public List<Course> getCoursesWhichITeach(@PathVariable("id") long id) {
        return courseManager.listByInstructorId(id);
    }

    @PostMapping(value = "/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        courseManager.create(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable Long id) {
        courseManager.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
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
        return new ResponseEntity<>(studentScores, HttpStatus.OK);
    }

    @PostMapping(value = "/score")
    public ResponseEntity<StudentScore> subscribe(@RequestBody StudentScore score) {
        studentScoreManager.create(score);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @PutMapping(value = "/score")
    public ResponseEntity<StudentScore> setScore(@RequestBody StudentScore score) {
        System.out.println("Contr: " + score);
        studentScoreManager.update(score);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @DeleteMapping(value = "/score")
    public ResponseEntity<StudentScore> unsubscribe(@RequestBody StudentScore score) {
        studentScoreManager.delete(score.getStudent().getId(), score.getCourse().getId());
        return new ResponseEntity<>(score, HttpStatus.OK);
    }


}
