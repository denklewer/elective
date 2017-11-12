package dao.mappers;
import model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = User.newBuilder()
                .setId(rs.getLong("instructor_id"))
                .setEmail(rs.getString("instructor_email"))
                .setFirstName(rs.getString("instructor_first_name"))
                .setLastName(rs.getString("instructor_last_name"))
                .setLogin(rs.getString("instructor_login"))
                .setPassword(rs.getString("instructor_password"))
                .build();
        return user;
    }
}
