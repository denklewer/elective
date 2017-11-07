package dao;

import context.Course;
import context.Student;
import context.StudentScore;
import context.Teacher;
import dao.mappers.StudentMapper;
import dao.mappers.TeacherMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class StudentJdbcDao implements JdbcDao<Student, Integer> {

    private JdbcTemplate jdbcTemplate;
    private JdbcDaoFactory daoFactory;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, JdbcDaoFactory factory) {
        this.jdbcTemplate = jdbcTemplate;
        this.daoFactory = factory;
    }

    @Override
    public Student read(Integer id) {
        String sql = "SELECT * FROM Student WHERE student_id = ?";

        Student student = jdbcTemplate.queryForObject(sql, new StudentMapper(), id);


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
