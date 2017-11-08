import appconfig.AppConfig;
import dao.UserJdbcDao;
import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(AppConfig.class);
        configApplicationContext.refresh();

        UserJdbcDao userJdbcDao = configApplicationContext.getBean(UserJdbcDao.class);

        List<User> users = userJdbcDao.list();

        users.forEach(user -> userJdbcDao.delete(user.getId()));

    }
}
