package epam_team1.service.dao.mappers;

import epam_team1.service.model.StudentScore;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentScoreGradeAndFeedbackAndCourse
implements RowMapper<StudentScore> {
    @Override
    public StudentScore mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentScore studentScore = StudentScore.newBuilder()
                .setScore(rs.getInt("grade"))
                .setFeedback(rs.getString("feedback"))
                .setCourse(new CourseNameAndDateRowMapper().mapRow(rs, rowNum))
                .build();

        return studentScore;
    }
}
