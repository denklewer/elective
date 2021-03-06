package epam_team1.service.services;

import epam_team1.service.model.User;

import java.util.List;

public interface UserManager {
    /**
     * Get User from source by id.
     *
     * @param id User id in database
     * @return User from database, where key is id
     */
    User readById(long id);
    /**
     * Get User from source by id.
     *
     * @param login User id in database
     * @return User from database, where key is id
     */
    User readByLogin(String login);

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
    void deleteById(long id);

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

    /**
     * Get list of available Users from database.
     *
     * @return list of Users which are Students
     */
    List<User> getStudentsByCourseId(long id, int limit , int page);
}
