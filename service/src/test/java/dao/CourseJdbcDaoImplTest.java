package dao;

import epam_team1.service.dao.CourseDao;
import epam_team1.service.model.Course;
import epam_team1.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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



    @Test
    public void listByStudentIdExeptMine() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date parsingDateStart = ft.parse("2016-02-07");
        Date parsingDateEnd = ft.parse("2016-05-15");

        List<Course> list = courseDao.listByStudentIdExceptMine(1);


        List<Course> expectedList = new ArrayList<Course>();
        User instructor = User.newBuilder()
                .setFirstName("Ilya")
                .setLastName("Kiselev").build();
        Course course = Course.newBuilder().setName("course1")
                .setId(4)
                .setStart(parsingDateStart)
                .setEnd(parsingDateEnd)
                .setInstructor(instructor).build();
        expectedList.add(course);

        assertTrue(  list.get(0).equals(expectedList.get(0)));





    }


    @Test
    public void listByStudentId() throws Exception {

        List<Course> courseList = new ArrayList<>();

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date parsingDateStart32 = ft.parse("2016-01-07");
        Date parsingDateEnd32 = ft.parse("2016-04-11");
        Course course32 = Course.newBuilder()
                .setId(32)
                .setName("Analitic dynamic")
                .setStart(parsingDateStart32)
                .setEnd(parsingDateEnd32)

                .setInstructor(User.newBuilder()
                        .setFirstName("Irina")
                        .setLastName("Pototskay")
                        .build())
                .build();

        courseList.add(course32);

        Date parsingDateStart13 = ft.parse("2016-09-04");
        Date parsingDateEnd13 = ft.parse("2017-04-28");
        Course course13 = Course.newBuilder()
                .setId(13)
                .setName("Integrals")
                .setStart(parsingDateStart13)
                .setEnd(parsingDateEnd13)

                .setInstructor(User.newBuilder()
                        .setFirstName("Igor")
                        .setLastName("Olemskoi")
                        .build())
                .build();

        courseList.add(course13);

        List<Course> downloadCourseList = courseDao.listByStudentId(35);

        for (Course item: downloadCourseList) {
            assertTrue(courseList.contains(item));
        }
    }

}