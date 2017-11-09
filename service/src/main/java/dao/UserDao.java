package dao;

import model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("transactionManager")
public interface UserDao {

    /**
     * Get User from source by id.
     *
     * @param id User id in database
     * @return User from database, where key is id
     */
    User read(long id);

    /**
     * Update User in source.
     *
     * @param user for update
     * @return new update User
     */
    User update(User user);

    /**
     * Insert User in data source.
     *
     * @param user for insertion in database
     * @return new User, which was just now wrote in database
     */
    User create(User user);

    /**
     * Remove User from table by id.
     *
     * @param id User id
     */
    void delete(long id);

    /**
     * Get list of available Users from database.
     *
     * @return list of available Users
     */
    List<User> list();

    /**
     * Get list of available Users from database.
     *
     * @return list of Users which are Students
     */
    List<User> getStudents();

}