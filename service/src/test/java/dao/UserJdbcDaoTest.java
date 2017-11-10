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
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class UserJdbcDaoTest {

    private static final String DROP_USER_TABLE_SCRIPT = "scripts/drop/user";

    @Autowired
    private UserJdbcDaoImpl userJdbcDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createAndReadTest(){
        User user = User.newBuilder()
                .setFirstName("Leonardo")
                .setLastName("DiCaprio")
                .setLogin("leoOscar")
                .setId(0)
                .setEmail("catchmeifyoucan@gmail.com")
                .setPassword("everyOneGoesToIt42")
                .build();
        User userWithId = userJdbcDao.create(user);

        User readedUser = userJdbcDao.read(userWithId.getId());

        assertEquals(userWithId, readedUser);

    }

    @Test
    public void updateTest() throws Exception{
        User user = User.newBuilder()
                .setFirstName("Laurence")
                .setLastName("Wachowski")
                .setLogin("larry")
                .setId(0)
                .setEmail("wehaveoneinterestingfilm@matrix.com")
                .setPassword("trytochangemyself42")
                .build();
        User userWithId = userJdbcDao.create(user);
        User updatedUser = User.newBuilder()
                .setFirstName("Lana")
                .setLastName("Wachowski")
                .setLogin("notLarryNow")
                .setId(userWithId.getId())
                .setEmail("wehaveoneinterestingfilm@matrix.com")
                .setPassword("ichangedmyself42")
                .build();
        userJdbcDao.update(updatedUser);

        assertEquals(updatedUser, userJdbcDao.read(updatedUser.getId()));
    }

    @Test
    public void listTest(){
        List<User> list = userJdbcDao.list();

        assertTrue(list.size() == 3);
    }

    @Test
    public void deleteTest(){
        List<User> usersBeforeDelete = userJdbcDao.list();
        userJdbcDao.delete(usersBeforeDelete.get(0).getId());
        List<User> usersAfterDelete = userJdbcDao.list();

        assertTrue(usersAfterDelete.size() + 1 == usersBeforeDelete.size());
    }

    @After
    public void tearDown() throws Exception {
        ScriptUtils.executeSqlScript(
                jdbcTemplate.getDataSource().getConnection(),
                new ClassPathResource(DROP_USER_TABLE_SCRIPT)
        );

    }

}