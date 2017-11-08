package dao.mappers;

import model.Course;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = Course.newBuilder()
                .setId(rs.getLong("course_id"))
                .setName(rs.getString("course_name"))
                .setEnd(rs.getDate("end_date").toLocalDate())
                .setStart(rs.getDate("start_date").toLocalDate())
                .setInstructor(new UserRowMapper().mapRow(rs, rowNum))
                .build();
        return course;
    }
}
