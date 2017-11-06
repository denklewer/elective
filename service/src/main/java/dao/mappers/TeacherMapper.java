package dao.mappers;

import context.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher>{
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new Teacher(rs.getInt("teacher_id"),
                rs.getString("first_name"),
                rs.getString("last_name")
        );
        return teacher;
    }
}
