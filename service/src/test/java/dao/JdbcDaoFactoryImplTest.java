package dao;

import context.Course;
import context.CourseFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class JdbcDaoFactoryImplTest {
    private JdbcDaoFactory jdbcDaoFactory;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(JdbcTemplate.class.getResourceAsStream("/database.properties"));

        // create data source
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String className = properties.getProperty("driverClassName");
        dataSource.setDriverClassName(className);
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setConnectionProperties(properties);
        jdbcDaoFactory = new JdbcDaoFactoryImpl(dataSource);


    }

    @Test
    public void getTeacherDao() throws Exception {

    }

    @Test
    public void getStudentDao() throws Exception {
    }

    @Test
    public void getCourseDao() throws Exception {
        CourseJdbcDao courseJdbcDao = jdbcDaoFactory.getCourseDao();
        CourseFactory courseFactory = new CourseFactory();

        Course target = courseFactory.newInstance(1,"JavaCore", null, null);
        target.setId(
                courseJdbcDao.create(target)
        );
        List<Course> courses = courseJdbcDao.list();

        assertTrue(courses.contains(target));

    }

    @Test
    public void getStudentScoreDao() throws Exception {
    }

}