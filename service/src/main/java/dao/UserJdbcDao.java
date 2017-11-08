package dao;

import model.User;
import dao.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRowMapper userRowMapper;

    public User read(long id) {
        String sql = "SELECT * FROM User WHERE user_id = ? ";
        User user = jdbcTemplate.queryForObject(sql,
                userRowMapper,
                id
        );

        return user;
    }

    public User update(User user) {
        String sql = "UPDATE User" +
                " SET" +
                " first_name = ?," +
                " last_name = ?," +
                " login = ?," +
                " email = ?," +
                " password = ?" +
                " WHERE  user_id= ?";
        jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getEmail(),
                user.getPassword(),
                user.getId());
        return user;
    }

    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = con -> {
            PreparedStatement statement =
                    con.prepareStatement("INSERT INTO " +
                                    "User(first_name, " +
                                    "last_name, " +
                                    "email, " +
                                    "login, " +
                                    "password) " +
                                    "VALUES (?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            return statement;
        };
        jdbcTemplate.update(creator, keyHolder);
        int id = keyHolder.getKey().intValue();
        user.setId(id);
        return user;
    }

    public User delete(long id) {
        User user = read(id);
        String sql = "DELETE FROM User WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
        return user;
    }


    public List<User> list() {
        String sql = "SELECT * from User";
        return  jdbcTemplate.query(sql, userRowMapper);
    }

    public List<User> getStudents(){
        throw  new NotImplementedException();
    }


}