package dao;

import model.StudentScore;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class StudentScoreJdbcDaoImplTest {

    @Autowired
    private StudentScoreDao studentScoreDao;

    @Test
    public void read() throws Exception {
        StudentScore studentScore = StudentScore.newBuilder()
                .setStudent(User.newBuilder()
                            .setEmail())
                .setScore(4)
                .setFeedback("good").build();

        StudentScore studentRead = studentScoreDao.read(24680, 1213);

        assertEquals(studentScore, studentRead);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void create() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void list() throws Exception {
    }

}