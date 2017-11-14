package dao;

import epam_team1.service.dao.StudentScoreDao;
import epam_team1.service.model.Course;
import epam_team1.service.model.StudentScore;
import epam_team1.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class StudentScoreJdbcDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private StudentScoreDao studentScoreDao;

    @Test
    public void read() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date parsingDateStart = ft.parse("2016-01-07");
        Date parsingDateEnd = ft.parse("2016-04-11");
        //Date dateStart = new Date("08.02.2016");

            StudentScore studentScore = StudentScore.newBuilder()
                .setStudent(User.newBuilder()
                        .setFirstName("Anton")
                        .setLastName("Nechiporuk")
                        .setLogin("qwadracopter")
                        .setId(21)
                        .setEmail("antoshka@yandex.com")
                        .setPassword("tooEasyPassword")
                        .build())
                .setCourse(Course.newBuilder()
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
                        .build())
                .setScore(4)
                .setFeedback("you can better")
                .build();

        StudentScore studentRead = studentScoreDao.read(21, 32);

        System.out.println(studentScore);
        System.out.println(studentRead);

        assertEquals(studentScore, studentRead);
    }

    @Test
    public void update() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date parsingDateStart = ft.parse("2016-09-04");
        Date parsingDateEnd = ft.parse("2017-04-28");

        StudentScore updatedStudentScore = StudentScore.newBuilder()
                .setStudent(User.newBuilder()
                        .setFirstName("Yuirii")
                        .setLastName("Antipenko")
                        .setLogin("y_ant")
                        .setId(35)
                        .setEmail("y_antipin@gmail.com")
                        .setPassword("43568tdhdfgnjy")
                        .build())
                .setCourse(Course.newBuilder()
                        .setId(13)
                        .setName("Integrals")
                        .setStart(parsingDateStart)
                        .setEnd(parsingDateEnd)
                        .setInstructor(User.newBuilder()
                                .setFirstName("Igor")
                                .setLastName("Olemskoi")
                                .setLogin("iv_olemskoi")
                                .setId(65)
                                .setEmail("ivladimirovich58@gmail.com")
                                .setPassword("tRyToBrEAK67823645")
                                .build())
                        .build())
                .setScore(5)
                .setFeedback("You passed perfect. Congratulations. Hope to see you next year")
                .build();

        studentScoreDao.update(updatedStudentScore);

        StudentScore readedScore = studentScoreDao.read(updatedStudentScore.getStudent().getId(), updatedStudentScore.getCourse().getId());

        assertEquals(updatedStudentScore,readedScore);


    }

    @Test
    public void create() throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date parsingDateStart = ft.parse("2016-09-03");
        Date parsingDateEnd = ft.parse("2016-12-27");

        int countRowsInTableBefore = countRowsInTable("Course_participation");
        StudentScore studentScore = StudentScore.newBuilder()
                .setStudent(User.newBuilder()
                        .setFirstName("Vladimir")
                        .setLastName("Smirnov")
                        .setLogin("vsmirnov")
                        .setId(13)
                        .setEmail("vladsmirnov@gmail.com")
                        .setPassword("failedexam")
                        .build())
                .setCourse(Course.newBuilder()
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
                        .build())
                .setScore(4)
                .setFeedback("good, but you can better!")
                .build();

        StudentScore studentScore1 = studentScoreDao.create(studentScore);

        int countRowsInTableAfter = countRowsInTable("Course_participation");

        assertTrue(countRowsInTableAfter == countRowsInTableBefore + 1);


    }

    @Test
    public void delete() throws Exception {

        int countRowsInTableBefore = countRowsInTable("Course_participation");
        studentScoreDao.delete(3,6);
        int countRowsInTableAfter = countRowsInTable("Course_participation");

        assertTrue(countRowsInTableAfter == countRowsInTableBefore - 1);
    }

    @Test
    public void list() throws Exception {

    }
}