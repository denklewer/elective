package dao.mappers;

import context.Course;
import context.CourseFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseFactory factory = new CourseFactory();

        Course course = factory.newInstance(rs.getInt("course_id"),
                rs.getString("course_name"),
                null,
                null);

        return course;
    }
}
