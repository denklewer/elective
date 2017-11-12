package epam_team1.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
//        configApplicationContext.register(AppConfig.class);
//        configApplicationContext.refresh();
//
//        UserJdbcDaoImpl userJdbcDaoImpl = configApplicationContext.getBean(UserJdbcDaoImpl.class);
//        CourseJdbcDaoImpl courseJdbcDaoImpl = configApplicationContext.getBean(CourseJdbcDaoImpl.class);
//        //List<User> users = userJdbcDaoImpl.list();
//        List<Course> courses = courseJdbcDaoImpl.list();
//        courses.forEach(System.out::println);
//        Course course = courseJdbcDaoImpl.read(20);
//        System.out.println(course);
//
//
//        //users.forEach(user -> userJdbcDaoImpl.delete(user.getId()));
//        courses.forEach(course1 -> courseJdbcDaoImpl.delete(course1.getId()));
//    }
}
