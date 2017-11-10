package dao;

import model.StudentScore;
import dao.mappers.StudentScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentScoreJdbcDaoImpl implements StudentScoreDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    String sqlRead = "SELECT * FROM" +
            " Course_participation" +
            " student_id : studentId," +
            " course_id : courseId";

    @Override
    public StudentScore read(long userId, long courseId) {

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("studentId", userId);
        parameters.put("courseId", courseId);

        StudentScore studentScore = jdbcTemplate.queryForObject(sqlRead,
                new StudentScoreMapper(),
                userId,
                courseId
        );

        StudentScore studentScore1 = namedParameterJdbcTemplate.query(sqlRead,
                parameters, new StudentScoreMapper());

        return studentScore;
    }

    @Override
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

    @Override
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

    @Override
    public void delete(long userId, long courseId) {
        StudentScore studentScore = read(userId, courseId);
        String sql = "DELETE FROM Course_participation WHERE student_id = ? and course_id = ?";
        jdbcTemplate.update(sql, userId, courseId);
    }

    @Override
    public ArrayList<StudentScore> list() {
        return null;
    }
}
