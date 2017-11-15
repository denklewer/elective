package epam_team1.service.dao;

import epam_team1.service.dao.mappers.StudentsListByCourseRowMapper;
import epam_team1.service.logger.EnableLogging;
import epam_team1.service.dao.exceptions.ReadException;
import epam_team1.service.dao.exceptions.UpdateException;
import epam_team1.service.model.User;
import epam_team1.service.dao.mappers.UserRowMapper;

import epam_team1.service.dao.exceptions.ReadException;
import epam_team1.service.logger.EnableLogging;
import epam_team1.service.dao.exceptions.UpdateException;
import epam_team1.service.model.User;

import epam_team1.service.dao.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import java.util.List;

@Repository
public class UserJdbcDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_READ = "SELECT * FROM" +
            " User" +
            " WHERE user_id = :userId";

    private final String SQL_UPDATE = "UPDATE User SET" +
            " first_name = :firstName," +
            " last_name = :lastName," +
            " Login = :login," +
            " email = :email," +
            " password = :password" +
            " WHERE user_id = :userId";

    private final String SQL_CREATE = "INSERT INTO User" +
            " (first_name, last_name, Login, Password, email)" +
            " VALUES (:firstName, :lastName, :login, :password, :email)";

    private final String SQL_DELETE = "DELETE FROM User" +
            " WHERE user_id = :userId";

    private final String SQL_LIST = "SELECT * FROM User";

    private final String SQL_STUDENTS_BY_COURSE_ID = "SELECT " +
            " first_name, " +
            " last_name " +
            " FROM Course_Participation " +
            " JOIN User ON user_id = student_id " +
            " WHERE course_id = :courseId";


    @Override
    @EnableLogging
    public User read(long id) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", id);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(SQL_READ,
                    parameters, new UserRowMapper());
            return user;

        } catch (Exception ex) {
            throw new ReadException(ex);
        }

    }

    @Transactional
    @Override
    @EnableLogging
    public User update(User user) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("userId", user.getId());
        try {
            namedParameterJdbcTemplate.update(SQL_UPDATE, parameters);
            return user;
        } catch (Exception ex) {
            throw new UpdateException(ex);
        }
    }

    @Transactional
    @Override
    @EnableLogging
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
        try {
            long result = namedParameterJdbcTemplate.update(SQL_CREATE,
                    parameters,
                    keyHolder,
                    new String[]{"user_id"});
            long id = keyHolder.getKey().longValue();

            User returnUser = User.newBuilder()
                    .setEmail(user.getEmail())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setLogin(user.getLogin())
                    .setPassword(user.getPassword())
                    .setId(id)
                    .build();
            return returnUser;
        } catch (DataAccessException ex){
            throw  new UpdateException(ex);
        }
        catch (Exception ex) {
            throw  new RuntimeException("ID extraction error", ex);
        }
    }

    @Transactional
    @Override
    @EnableLogging
    public void delete(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", id);
        namedParameterJdbcTemplate.update(SQL_DELETE, parameters);
    }


    @Override
    @EnableLogging
    public List<User> list() {
        try {
            return namedParameterJdbcTemplate.query(SQL_LIST, new UserRowMapper());
        } catch (Exception ex) {
            throw new ReadException(ex);
        }

    }

    @Override
    @EnableLogging
    public List<User> getStudents() {
        throw new NotImplementedException();
    }

    @Override
    public List<User> getStudentsByCourseId(long courseId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", courseId);

        List<User> userList = namedParameterJdbcTemplate
                .query(SQL_STUDENTS_BY_COURSE_ID, parameters,
                        new StudentsListByCourseRowMapper());

        return userList;
    }
}
