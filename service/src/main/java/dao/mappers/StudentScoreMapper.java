package dao.mappers;

import model.StudentScore;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentScoreMapper implements RowMapper<StudentScore> {

    @Override
    public StudentScore mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentScore studentScore = StudentScore.newBuilder()
                .setScore(rs.getInt("grade"))
                .setFeedback(rs.getString("feedback"))
                .setStudent(new UserRowMapper().mapRow(rs, rowNum))
                .setCourse(new CourseRowMapper().mapRow(rs, rowNum))
                .build();
        return studentScore;
    }
}
