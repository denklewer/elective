package dao;

import context.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherJdbcDao implements JdbcDao<Teacher> {
    private DataSource dataSource;



    public TeacherJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
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
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * from Teachers";

        List<Teacher> listUser = jdbcTemplate.query(sql, new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("teacher_id"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                return teacher;
            }

        });
        return null;
    }
}
