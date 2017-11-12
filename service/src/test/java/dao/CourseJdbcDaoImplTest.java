package dao;

import model.Course;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class CourseJdbcDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private CourseDao courseDao;




    @Test
    public void read() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date parsingDateStart = ft.parse("2016-01-07");
        Date parsingDateEnd = ft.parse("2016-04-11");
        Course course = Course.newBuilder()
                .setId(32)
                .setName("Analitic dynamic")
                .setStart(parsingDateStart)
                .setEnd(parsingDateEnd)

                .setInstructor(User.newBuilder()
                        .setFirstName("Irina")
                        .setLastName("Pototskay")
                        .setLogin("mechanics")
                        .setId(45)
                        .setEmail("irina_71@google.com")
                        .setPassword("goodLecture")
                        .build())
                .build();

        Course read = courseDao.read(course.getId());

        assertEquals(course, read);
    }

    @Test
    public void updateTest() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date parsingDateStart = ft.parse("2016-02-07");
        Date parsingDateEnd = ft.parse("2016-05-15");

        Course updatedCourse = Course.newBuilder()
                .setId(14)
                .setName("Phylosophia")
                .setStart(parsingDateStart)
                .setEnd(parsingDateEnd)

                .setInstructor(User.newBuilder()
                        .setFirstName("Vladislav")
                        .setLastName("Nikitin")
                        .setLogin("philosophy")
                        .setId(67)
                        .setEmail("ImmanyilKant@gmail.com")
                        .setPassword("transient")
                        .build())
                .build();
        courseDao.update(updatedCourse);
        Course readedCourse = courseDao.read(updatedCourse.getId());
        System.out.println(updatedCourse);
        System.out.println(readedCourse);

        assertEquals(updatedCourse, readedCourse);
    }

    @Test
    public void createTest() throws Exception {
        int countRowsInTableBefore = countRowsInTable("Course");

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date parsingDateStart = ft.parse("2016-01-08");
        Date parsingDateEnd = ft.parse("2016-04-12");

        Course course = Course.newBuilder()
                .setId(128)
                .setName("Analitic dynamic")
                .setStart(parsingDateStart)
                .setEnd(parsingDateEnd)

                .setInstructor(User.newBuilder()
                        .setFirstName("Irina")
                        .setLastName("Pototskay")
                        .setLogin("mechanics")
                        .setId(45)
                        .setEmail("irina_71@google.com")
                        .setPassword("goodLecture")
                        .build())
                .build();
        Course courseForCreation = courseDao.create(course);
        int countRowsInTableAfter = countRowsInTable("Course");
        assertTrue(countRowsInTableAfter == countRowsInTableBefore + 1);
    }

    @Test
    public void delete() throws Exception {
        int countRowsInTableBefore = countRowsInTable("Course");
        courseDao.delete(127);
        int countRowsInTableAfter = countRowsInTable("Course");
        assertTrue(countRowsInTableAfter == countRowsInTableBefore - 1);
    }

    @Test
    public void list() throws Exception {
            int countRowsInTable = countRowsInTable("Course");
            List<Course> list = courseDao.list();
            assertTrue(list.size() == countRowsInTable);

    }

}