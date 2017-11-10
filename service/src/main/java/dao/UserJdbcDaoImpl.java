package dao;

import dao.mappers.StudentScoreMapper;
import model.StudentScore;
import model.User;
import dao.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.Statement;
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


    @Override
    public User read(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", id);

        User user = namedParameterJdbcTemplate.queryForObject(SQL_READ,
                parameters, new UserRowMapper());

        return user;
    }

    @Transactional("transactionManager")
    @Override
    public User update(User user) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("userId", user.getId());

        namedParameterJdbcTemplate.update(SQL_UPDATE, parameters);
        return user;
    }

    @Transactional("transactionManager")
    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
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
    }

    @Transactional("transactionManager")
    @Override
    public void delete(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", id);
        namedParameterJdbcTemplate.update(SQL_DELETE, parameters);
    }


    @Override
    public List<User> list() {
        return  namedParameterJdbcTemplate.query(SQL_LIST, new UserRowMapper());
    }

    @Override
    public List<User> getStudents(){
        throw  new NotImplementedException();
    }


}
