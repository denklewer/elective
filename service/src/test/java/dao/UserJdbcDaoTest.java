package dao;

import epam_team1.service.dao.UserDao;
import epam_team1.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class UserJdbcDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserDao userJdbcDao;

    @Test
    public void readTest() {
        User user = User.newBuilder()
                .setFirstName("Ilya")
                .setLastName("Kiselev")
                .setLogin("ikiselev7")
                .setId(1)
                .setEmail("ikisele7@gmail.com")
                .setPassword("SuPeRsEcReTaSsWoRd")
                .build();
        User read = userJdbcDao.read(user.getId());

        assertEquals(user, read);
    }

    @Test
    public void createTest(){
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