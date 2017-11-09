import appconfig.AppConfig;
import dao.CourseJdbcDaoImpl;
import dao.UserJdbcDaoImpl;
import model.Course;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(AppConfig.class);
        configApplicationContext.refresh();

        UserJdbcDaoImpl userJdbcDaoImpl = configApplicationContext.getBean(UserJdbcDaoImpl.class);
        CourseJdbcDaoImpl courseJdbcDaoImpl = configApplicationContext.getBean(CourseJdbcDaoImpl.class);
        //List<User> users = userJdbcDaoImpl.list();
        List<Course> courses = courseJdbcDaoImpl.list();
        courses.forEach(System.out::println);
        Course course = courseJdbcDaoImpl.read(20);
        System.out.println(course);


        //users.forEach(user -> userJdbcDaoImpl.delete(user.getId()));
        courses.forEach(course1 -> courseJdbcDaoImpl.delete(course1.getId()));
    }
}
