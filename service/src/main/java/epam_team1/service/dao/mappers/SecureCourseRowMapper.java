package epam_team1.service.dao.mappers;


import epam_team1.service.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecureCourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = Course.newBuilder()
                .setId(rs.getLong("course_id"))
                .setName(rs.getString("course_name"))
                .setEnd(rs.getDate("end_date"))
                .setStart(rs.getDate("start_date"))
                .setInstructor(new SecureInsturtorRowMapper().mapRow(rs, rowNum))
                .build();
        return course;
    }
}
