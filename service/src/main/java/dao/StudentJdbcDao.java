package dao;

import context.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вера on 06.11.2017.
 */
public class StudentJdbcDao implements JdbcDao<Student,Integer> {

    private JdbcTemplate jdbcTemplate;
    private JdbcDaoFactory factory;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, JdbcDaoFactory factory) {
        this.jdbcTemplate = jdbcTemplate;
        this.factory = factory;
    }

    @Override
    public Student read(Integer id) {
        return null;
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public Integer create(Student student) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Student> list() {
        return null;
    }
}
