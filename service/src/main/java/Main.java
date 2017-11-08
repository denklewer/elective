import appconfig.AppConfig;
import dao.CourseJdbcDao;
import dao.UserJdbcDao;
import model.Course;
import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(AppConfig.class);
        configApplicationContext.refresh();

        UserJdbcDao userJdbcDao = configApplicationContext.getBean(UserJdbcDao.class);
        CourseJdbcDao courseJdbcDao = configApplicationContext.getBean(CourseJdbcDao.class);
        //List<User> users = userJdbcDao.list();
        List<Course> courses = courseJdbcDao.list();
        courses.forEach(System.out::println);
        Course course = courseJdbcDao.read(20);
        System.out.println(course);


        //users.forEach(user -> userJdbcDao.delete(user.getId()));
        courses.forEach(course1 -> courseJdbcDao.delete(course1.getId()));
    }
}
