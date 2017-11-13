package epam_team1.service.services;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userManager")
public class UserManagerImpl implements  UserManager{
    @Autowired
    UserDao userDao;
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
}
