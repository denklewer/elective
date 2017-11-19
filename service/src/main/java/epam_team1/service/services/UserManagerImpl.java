package epam_team1.service.services;

import epam_team1.service.dao.StudentScoreDao;
import epam_team1.service.dao.UserDao;
import epam_team1.service.logger.EnableLogging;
import epam_team1.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserManagerImpl implements  UserManager{

    @Autowired
    UserDao userDao;
    @Autowired
    StudentScoreDao studentScoreDao;

    @Override
    @EnableLogging
    public User readById(long id) {
        return userDao.read(id);
    }

    @Override
    @EnableLogging
    public User readByLogin(String login) {
        return userDao.readByLogin(login);
    }

    @Override
    @EnableLogging
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    @EnableLogging
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    @EnableLogging
    public void deleteById(long id) {
        userDao.delete(id);

    }

    @Override
    @EnableLogging
    public List<User> list() {
        return userDao.list();
    }

    @Override
    @EnableLogging
    public List<User> getStudents() {
        return userDao.getStudents();
    }

    @Override
    @EnableLogging
    public List<User> getStudentsByCourseId(long id) {
        return userDao.getStudentsByCourseId(id);
    }
}
