package dao;

import context.Course;
import context.CourseFactory;
import context.Teacher;
import context.TeacherFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class CourseJdbcDaoTest {
    private JdbcDaoFactory jdbcDaoFactory;
    private CourseJdbcDao courseJdbcDao;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(JdbcTemplate.class.getResourceAsStream("/databaseTest.properties"));

        // create data source
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String className = properties.getProperty("driverClassName");
        dataSource.setDriverClassName(className);
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setConnectionProperties(properties);

        jdbcDaoFactory = new JdbcDaoFactoryImpl(dataSource);
        courseJdbcDao = jdbcDaoFactory.getCourseDao();

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void createAndRead() throws Exception {
        CourseFactory courseFactory = new CourseFactory();
        TeacherJdbcDao teacherDao = jdbcDaoFactory.getTeacherDao();

        Teacher teacher = new TeacherFactory().newInstance(0, "Ronald", "McDonald");
        int teacher_id = teacherDao.create(teacher);

        Course target = courseFactory.newInstance(0,"JavaExpert", teacher, null);
        int id = courseJdbcDao.create(target);
        Course course = courseJdbcDao.read(id);

        assertTrue(target.equals(course));
    }

    @Test
    public void delete() throws Exception {
        CourseFactory courseFactory = new CourseFactory();
        TeacherJdbcDao teacherDao = jdbcDaoFactory.getTeacherDao();

        Teacher teacher = new TeacherFactory().newInstance(0, "Ronald", "McDonald");
        int teacher_id = teacherDao.create(teacher);

        Course target = courseFactory.newInstance(0,"JavaExpert", teacher, null);
        int id = courseJdbcDao.create(target);
        List<Course> courses = courseJdbcDao.list();

        assertTrue(courses.contains(target));
    }

    @Test
    public void list() throws Exception {
        CourseFactory courseFactory = new CourseFactory();
        TeacherJdbcDao teacherDao = jdbcDaoFactory.getTeacherDao();

        Teacher teacher = new TeacherFactory().newInstance(0, "Ronald", "McDonald");
        int teacher_id = teacherDao.create(teacher);

        Course target = courseFactory.newInstance(0,"JavaExpert", teacher, null);
        int id = courseJdbcDao.create(target);
        List<Course> courses = courseJdbcDao.list();

        assertTrue(courses.contains(target));
    }

    @Test
    public void getByTeacherId() throws Exception {

    }

}