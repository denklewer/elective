package dao;

import context.Course;
import dao.mappers.CourseMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class CourseJdbcDao implements JdbcDao<Course> {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course read(int id) {
        String sql = "select * from Course where course_id = ?";
        Course course = jdbcTemplate.queryForObject(sql,
                new Object[]{id}, new CourseMapper());

        return course;
    }

    @Override
    public void update(Course course) {
        String sql = "update Course set course_name = ? where course_id = ?)";
        jdbcTemplate.update(sql, course.getName(), course.getId());
    }

    @Override
    public Integer create(Course course) {
        String sql = "insert into Course (course_id, course_name) values (?,?)";
        return jdbcTemplate.update(sql,course.getId(), course.getName());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from Course where course_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public ArrayList<Course> list() {
        String sql = "SELECT * from Course";
        ArrayList<Course> listUser =
                (ArrayList<Course>) jdbcTemplate.query(sql, new CourseMapper());
        return listUser;
    }
}
