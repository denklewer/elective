package dao.mappers;

import context.Course;
import context.CourseFactory;
import context.Teacher;
import context.TeacherFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseFactory factory = new CourseFactory();

        Teacher teacher = new TeacherFactory().newInstance(rs.getInt("teacher_id"), null ,null);

        Course course = factory.newInstance(rs.getInt("course_id"),
                rs.getString("course_name"),
                teacher,
                null);

        return course;
    }
}
