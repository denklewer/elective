package epam_team1.service.dao.mappers;

import epam_team1.service.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsListByCourseRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = User.newBuilder()
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .build();

        return user;
    }
}
