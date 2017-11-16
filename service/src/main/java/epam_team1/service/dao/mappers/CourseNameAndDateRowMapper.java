package epam_team1.service.dao.mappers;
import epam_team1.service.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseNameAndDateRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = Course.newBuilder()
                .setName(rs.getString("course_name"))
                .setStart(rs.getDate("start_date"))
                .setEnd(rs.getDate("end_date"))
                .build();

        return course;
    }
}
