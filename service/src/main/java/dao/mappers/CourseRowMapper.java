package dao.mappers;

import model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CourseRowMapper implements RowMapper<Course> {

    @Autowired
    private UserRowMapper userRowMapper;

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getLong("course_id"));
        course.setName(rs.getString("course_name"));
        course.setStart(rs.getDate("start_date").toLocalDate());
        course.setEnd(rs.getDate("end_date").toLocalDate());
        course.setInstructor(userRowMapper.mapRow(rs, rowNum));
        return course;
    }
}
