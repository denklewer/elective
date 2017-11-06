package dao;

import context.Course;
import org.springframework.jdbc.core.JdbcTemplate;

public class CourseJdbcDao implements JdbcDao<Course> {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course read(int id) {
        return null;
    }

    @Override
    public void update(Course course) {

    }

    @Override
    public Integer create(Course course) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
