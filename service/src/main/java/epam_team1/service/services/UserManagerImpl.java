package epam_team1.service.services;

import epam_team1.service.dao.StudentScoreDao;
import epam_team1.service.dao.UserDao;
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
    public User readById(long id) {
        return userDao.read(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public void deleteById(long id) {
        userDao.delete(id);

    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public List<User> getStudents() {
        return userDao.getStudents();
    }

    @Override
    public List<User> getStudentsByCourseId(long id) {
        return userDao.getStudentsByCourseId(id);
    }
}
