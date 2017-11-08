package dao.mappers;

import model.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentScoreMapper implements RowMapper<StudentScore> {

    @Autowired
    private UserRowMapper userRowMapper;
    @Autowired
    private CourseRowMapper courseRowMapper;

    @Override
    public StudentScore mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentScore studentScore = new StudentScore();
        studentScore.setScore(rs.getInt("grade"));
        studentScore.setFeedback(rs.getString("feedback"));
        studentScore.setStudent(userRowMapper.mapRow(rs, rowNum));
        studentScore.setCourse(courseRowMapper.mapRow(rs, rowNum));
        return studentScore;
    }
}
