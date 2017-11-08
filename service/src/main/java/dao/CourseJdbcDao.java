package dao;

import model.Course;
import dao.mappers.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseJdbcDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CourseRowMapper courseRowMapper;

    public Course read(long id) {
        String sql = "select * from " +
                "User join Course " +
                "on user_id = instructor_id " +
                "where course_id = ?";
        Course course = jdbcTemplate.queryForObject(sql,
                new Object[]{id}, courseRowMapper);
        return course;
    }

    public Course update(Course course) {
        String sql = "update Course set " +
                "course_name = ?, " +
                "instructor_id = ? " +
                "instructor_id = ? " +
                "instructor_id = ? " +
                "where course_id = ?";
        jdbcTemplate.update(sql, course.getName(), course.getInstructor().getId(), course.getId());
        return course;
    }

    public Integer create(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = con -> {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO" +
                            " Course(" +
                            " course_name," +
                            " instructor_id," +
                            " start_date," +
                            " end_date)" +
                            " VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, course.getName());
            statement.setLong(2, course.getInstructor().getId());
            statement.setDate(3, Date.valueOf(course.getStart()));
            statement.setDate(4, Date.valueOf(course.getEnd()));

            return statement;
        };
        jdbcTemplate.update(creator, keyHolder);
        int id = keyHolder.getKey().intValue();
        course.setId(id);
        return id;
    }

    public Course delete(long id) {
        Course course = read(id);
        String sql = "delete from Course where course_id = ?";
        jdbcTemplate.update(sql, id);
        return course;
    }

    public List<Course> list() {
        String sql = "select * from " +
                "User join Course " +
                "on user_id = instructor_id";
        ArrayList<Course> courses =
                (ArrayList<Course>) jdbcTemplate.query(sql, new CourseRowMapper());

        return courses;
    }

}
