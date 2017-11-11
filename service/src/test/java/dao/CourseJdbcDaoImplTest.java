package dao;

import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class CourseJdbcDaoImplTest  {
    @Autowired
    private CourseDao courseDao;


    @Test
    public void read() throws Exception {


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