package dao.mappers;

import context.StudentScore;
import context.StudentScoreFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentScoreMapper implements RowMapper<StudentScore> {
    @Override
    public StudentScore mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentScoreFactory factory = new StudentScoreFactory();

        StudentScore studentScore = factory.newInstance(
                rs.getByte("assesment_grade"),
                rs.getString("teacher_feedback")
        );

        return studentScore;
    }
}
