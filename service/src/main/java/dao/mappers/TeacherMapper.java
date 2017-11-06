package dao.mappers;

import context.Teacher;
import context.TeacherFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeacherFactory factory = new TeacherFactory();
        Teacher teacher = factory.newInstance(
                rs.getInt("teacher_id"),
                rs.getString("first_name"),
                rs.getString("last_name")
        );
        return teacher;
    }
}
