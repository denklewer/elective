package epam_team1.web.controllers;


import epam_team1.service.model.User;
import epam_team1.service.services.CourseManager;
import epam_team1.service.services.StudentScoreManager;
import epam_team1.service.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/elective")

public class ElectiveController {

    @Autowired
    @Qualifier("userManager")
    private UserManager userManager;
    @Autowired
    @Qualifier("courseManager")
    private CourseManager courseManager;
    @Autowired
    @Qualifier("studentScoreManager")
    private StudentScoreManager studentScoreManager;

    @GetMapping("/users")
    public List getUsers() {
        return userManager.list();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") long id) {
        User user = userManager.readById(id);
        if (user == null) {
            return new ResponseEntity("No user found for ID " + id, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity createUser(@RequestBody User user) {
        userManager.create(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        userManager.deleteById(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") long id, @RequestBody User user) {
        user = userManager.update(user);
        if (user == null) {
            return new ResponseEntity("No user found for ID " + id, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(user, HttpStatus.OK);
    }


}