package epam_team1.web.controllers;

import epam_team1.service.model.Course;
import epam_team1.service.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    public List<Course> courses(){
        User user = User.newBuilder()
                .setFirstName("Leonardo")
                .setLastName("DiCaprio")
                .setLogin("leoOscar")
                .setId(0)
                .setEmail("catchmeifyoucan@gmail.com")
                .setPassword("everyOneGoesToIt42")
                .build();
        Course javaCore = Course.newBuilder()
                .setName("JavaCore")
                .setId(0)
                .setInstructor(user)
                .setStart(LocalDate.of(2017, 2, 16))
                .setEnd(LocalDate.of(2017, 5, 15))
                .build();
        Course javaAdvanced = Course.newBuilder()
                .setName("JavaAdvanced")
                .setId(0)
                .setInstructor(user)
                .setStart(LocalDate.of(2017, 6, 16))
                .setEnd(LocalDate.of(2017, 8, 15))
                .build();
        List<Course> courses = new ArrayList<>();
        Collections.addAll(courses, javaCore, javaAdvanced);

        return courses;

    }
}
