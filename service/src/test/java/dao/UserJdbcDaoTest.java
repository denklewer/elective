package dao;

import appconfig.AppConfig;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UserJdbcDaoTest {

    private List<User> users;
    private UserJdbcDaoImpl userJdbcDao;

    @Before
    public void setUp() throws Exception {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(AppConfig.class);
        configApplicationContext.refresh();

        userJdbcDao = configApplicationContext.getBean(UserJdbcDaoImpl.class);

        users = new ArrayList<>();
        Collections.addAll(users,
                User.newBuilder()
                        .setEmail("ikisele7@gmail.com")
                        .setFirstName("Ilya")
                        .setLastName("Kiselev")
                        .setId(0)
                        .setLogin("ikiselev7")
                        .setPassword("SuPeRsEcReTaSsWoRd")
                        .build(),
                User.newBuilder()
                        .setEmail("denklewer@gmail.com")
                        .setFirstName("Denis")
                        .setLastName("Klewerov")
                        .setId(0)
                        .setLogin("denKlewer")
                        .setPassword("SuPeRsEcReTaSsWoRd")
                        .build(),
                User.newBuilder()
                        .setEmail("veragrunina@gmail.com")
                        .setFirstName("Vera")
                        .setLastName("Grunina")
                        .setId(0)
                        .setLogin("veraGrunina")
                        .setPassword("SuPeRsEcReTaSsWoRd")
                        .build()
        );
        users.forEach(userJdbcDao::create);
    }

    @Test
    public void updateTest() throws Exception{
        User user = users.get(0);
//        user.setPassword("new");
//        user.setLogin("ikiselevnew");
//        user.setLastName("KiselevNew");
//        user.setFirstName("IlyaNew");
//        user.setEmail("ikiselevnew@gmail.com");

        userJdbcDao.update(user);
        User user1 = userJdbcDao.read(user.getId());

        assertEquals(user, user1);
    }

    @Test
    public void listTest(){
        List<User> list = userJdbcDao.list();

        assertTrue(list.size() == users.size());
    }

    @After
    public void tearDown() throws Exception {
        users.forEach(user -> userJdbcDao.delete(user.getId()));
    }

}