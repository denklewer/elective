package dao;

import context.Teacher;
import dao.mappers.TeacherMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class TeacherJdbcDao implements JdbcDao<Teacher> {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher read(int id) {
        String sql = "SELECT * FROM Teacher WHERE  teacher_id= ? ";
        Teacher teacher = jdbcTemplate.queryForObject(sql, new TeacherMapper(),id);
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE Teacher SET first_name = ?, last_name = ? WHERE  teacher_id= ?";
        jdbcTemplate.update(sql, teacher.getFirstName(), teacher.getLastName(),teacher.getId());
    }

    @Override
    public Integer create(Teacher teacher) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO Teacher(first_name,last_name) VALUES (?,?)",
                                                                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, teacher.getFirstName());
            statement.setString(2,  teacher.getLastName());
            return statement;
        };
        jdbcTemplate.update(creator, keyHolder);
        int id = keyHolder.getKey().intValue();
        teacher.setId(id);
        return id;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Teacher WHERE teacher_id = ?";
        jdbcTemplate.update(sql,id);
    }


    @Override
    public ArrayList<Teacher> list() {
        String sql = "SELECT * from Teacher";
        ArrayList<Teacher> listUser = (ArrayList) jdbcTemplate.query(sql, new TeacherMapper());
        return listUser;
    }
}
