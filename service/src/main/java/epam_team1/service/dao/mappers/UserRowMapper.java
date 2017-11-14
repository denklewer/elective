package epam_team1.service.dao.mappers;

import epam_team1.service.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = User.newBuilder()
                .setId(rs.getLong("user_id"))
                .setEmail(rs.getString("email"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .setLogin(rs.getString("login"))
                .setPassword(rs.getString("password"))
                .build();
        return user;
    }
}
