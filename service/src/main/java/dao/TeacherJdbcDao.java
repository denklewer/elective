package dao;

import context.Teacher;
import dao.mappers.TeacherMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

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

    @Override
    public ArrayList<Teacher> list() {
        String sql = "SELECT * from Teacher";
        ArrayList<Teacher> listUser =(ArrayList) jdbcTemplate.query(sql, new TeacherMapper());
        return listUser;
    }
}
