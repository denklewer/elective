package dao;

import appconfig.AppConfig;
import model.Course;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CourseJdbcDaoTest {
    private List<User> users;
    private List<Course> courses;
    private UserJdbcDaoImpl userJdbcDao;
    private CourseJdbcDaoImpl courseJdbcDao;

    @Before
    public void setUp() throws Exception {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(AppConfig.class);
        configApplicationContext.refresh();

        userJdbcDao = configApplicationContext.getBean(UserJdbcDaoImpl.class);
        courseJdbcDao = configApplicationContext.getBean(CourseJdbcDaoImpl.class);

        users = new ArrayList<>();
        Collections.addAll(users,
                User.newBuilder()
                        .setEmail("ikisele7@gmail.com")
                        .setFirstName("Ilya")
                        .setLastName("Kiselev")
                        .setId(0)
                        .setLogin("ikiselev7")
                        .setPassword("SuPeRsEcReTaSsWoRd")
                        .build(),
                User.newBuilder()
                        .setEmail("denklewer@gmail.com")
                        .setFirstName("Denis")
                        .setLastName("Klewerov")
                        .setId(0)
                        .setLogin("denKlewer")
                        .setPassword("SuPeRsEcReTaSsWoRd")
                        .build(),
                User.newBuilder()
                        .setEmail("veragrunina@gmail.com")
                        .setFirstName("Vera")
                        .setLastName("Grunina")
                        .setId(0)
                        .setLogin("veraGrunina")
                        .setPassword("SuPeRsEcReTaSsWoRd")
                        .build()
        );
        users.forEach(userJdbcDao::create);
        courses = new ArrayList<>();
        Collections.addAll(courses,
                Course.newBuilder()
                        .setId(0)
                        .setStart(LocalDate.of(2017, 8, 16))
                        .setEnd(LocalDate.of(2017, 10, 22))
                        .setName("JavaCore")
                        .setInstructor(users.get(0))
                        .build(),
                Course.newBuilder()
                        .setId(0)
                        .setStart(LocalDate.of(2017, 9, 16))
                        .setEnd(LocalDate.of(2017, 11, 22))
                        .setName("JavaAdvanced")
                        .setInstructor(users.get(1))
                        .build()
                );

        courses.forEach(courseJdbcDao::create);

    }

    @After
    public void tearDown() throws Exception {
        users.forEach(user -> userJdbcDao.delete(user.getId()));
        courses.forEach(course -> courseJdbcDao.delete(course.getId()));
    }

    @Test
    public void update() throws Exception {
        Course course = courses.get(0);
//        course.setName("JavaBabyDeveloper");
//        course.setStart(LocalDate.of(2017, 2, 10));
//        course.setEnd(LocalDate.of(2017, 10, 22));
//        course.setInstructor(users.get(2));

        courseJdbcDao.update(course);
        Course course1 = courseJdbcDao.read(course.getId());

        assertEquals(course, course1);
    }

    @Test
    public void list() throws Exception {
    }

}