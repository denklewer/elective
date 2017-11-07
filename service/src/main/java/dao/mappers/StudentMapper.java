package dao.mappers;

import context.Student;
import context.StudentFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentFactory factory = new StudentFactory();

        Student student = factory.newInstance(
                rs.getInt("student_id"),
                rs.getString("first_name"),
                rs.getString("last_name")
        );

        return student;
    }
}
