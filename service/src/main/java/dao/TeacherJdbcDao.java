package dao;

import context.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;

public class TeacherJdbcDao implements JdbcDao<Teacher> {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher read(int id) {
        return null;
    }

    @Override
    public void update(Teacher teacher) {

    }

    @Override
    public Integer create(Teacher teacher) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
