package epam_team1.service.dao.mappers;


import epam_team1.service.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SecureInsturtorRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
         User user = User.newBuilder()
                .setFirstName(rs.getString("instructor_first_name"))
                .setLastName(rs.getString("instructor_last_name"))
                .build();
        return user;
    }
}
