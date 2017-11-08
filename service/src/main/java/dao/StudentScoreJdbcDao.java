package dao;

import model.StudentScore;
import dao.mappers.StudentScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class StudentScoreJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public StudentScore read(long userId, long courseId) {
        String sql = "SELECT * FROM" +
                " Course_participation" +
                " student_id = ?," +
                " course_id = ?";
        StudentScore studentScore = jdbcTemplate.queryForObject(sql,
                new StudentScoreMapper(),
                userId,
                courseId
        );

        return studentScore;
    }

    public StudentScore update(StudentScore studentScore) {
        String sql = "UPDATE Course_participation" +
                " grade = ?," +
                " feedback = ?" +
                "WHERE student_id = ?," +
                " course_id = ?";

        jdbcTemplate.update(sql,
                studentScore.getScore(),
                studentScore.getFeedback(),
                studentScore.getStudent().getId(),
                studentScore.getCourse().getId());
        return studentScore;
    }

    public StudentScore create(StudentScore studentScore) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = con -> {
            PreparedStatement statement =
                    con.prepareStatement("INSERT INTO " +
                                    "Course_participation(student_id, " +
                                    "course_id, " +
                                    "grade, " +
                                    "feedback, " +
                                    "VALUES (?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, studentScore.getCourse().getId());
            statement.setLong(2, studentScore.getStudent().getId());
            statement.setInt(3, studentScore.getScore());
            statement.setString(4, studentScore.getFeedback());
            return statement;
        };
        jdbcTemplate.update(creator, keyHolder);
        return studentScore;
    }

    public StudentScore delete(long userId, long courseId) {
        StudentScore studentScore = read(userId, courseId);
        String sql = "DELETE FROM Course_participation WHERE student_id = ? and course_id = ?";
        jdbcTemplate.update(sql, userId, courseId);
        return studentScore;
    }

    public ArrayList<StudentScore> list() {
        return null;
    }
}
