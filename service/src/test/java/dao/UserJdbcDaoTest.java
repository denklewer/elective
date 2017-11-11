package dao;

import appconfig.AppConfig;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class UserJdbcDaoTest/* extends AbstractTransactionalJUnit4SpringContextTests*/ {

    @Autowired
    private UserDao userJdbcDao;

    @Test
    public void createAndReadTest(){
        int countRowsInTableBefore = countRowsInTable("User");

        User user = User.newBuilder()
                .setFirstName("Leonardo")
                .setLastName("DiCaprio")
                .setLogin("leoOscar")
                .setId(0)
                .setEmail("catchmeifyoucan@gmail.com")
                .setPassword("everyOneGoesToIt42")
                .build();
        User userWithId = userJdbcDao.create(user);

        int countRowsInTableAfter = countRowsInTable("User");

        assertTrue(countRowsInTableAfter == countRowsInTableBefore + 1);
    }

    @Test
    public void updateTest() throws Exception{
        User updatedUser = User.newBuilder()
                .setFirstName("Lana")
                .setLastName("Wachowski")
                .setLogin("notLarryNow")
                .setId(4)
                .setEmail("wehaveoneinterestingfilm@matrix.com")
                .setPassword("ichangedmyself42")
                .build();
        userJdbcDao.update(updatedUser);

        assertEquals(updatedUser, userJdbcDao.read(updatedUser.getId()));
    }

    @Test
    public void listTest(){
        int countRowsInTable = countRowsInTable("User");
        List<User> list = userJdbcDao.list();

        assertTrue(list.size() == countRowsInTable);
    }

    @Test
    public void deleteTest(){
        int countRowsInTableBefore = countRowsInTable("User");

        userJdbcDao.delete(1);

        int countRowsInTableAfter = countRowsInTable("User");

        assertTrue(countRowsInTableAfter == countRowsInTableBefore - 1);

    }

}