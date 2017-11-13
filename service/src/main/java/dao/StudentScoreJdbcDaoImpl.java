package epam_team1.service.dao;

import epam_team1.service.model.StudentScore;
import epam_team1.service.dao.mappers.StudentScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class StudentScoreJdbcDaoImpl implements StudentScoreDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private final String SQL_READ = "SELECT * FROM" +
            " Course_participation" +
            " student_id = :studentId," +
            " course_id = :courseId";

    private final String SQL_UPDATE = "UPDATE Course_participation" +
            " grade = :grade" +
            " feedback = :feedback" +
            "WHERE student_id = :studentId," +
            " course_id = :courseId";

    private final String SQL_CREATE = "INSERT INTO Course_participation" +
            " (student_id, course_id, grade, feedback)" +
            " VALUES (:studentId, :courseId, :grade, feedback)";

    private final String SQL_DELETE = "DELETE FROM Course_participation" +
            " WHERE student_id = :studentId and course_id = :courseId";

    private final String SQL_LIST = "SELECT * FROM StudentScore";

    @Override
    public StudentScore read(long userId, long courseId) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", courseId)
                .addValue("studentId", userId);

        StudentScore studentScore = namedParameterJdbcTemplate.queryForObject(SQL_READ,
                parameters, new StudentScoreMapper());

        return studentScore;
    }

    @Transactional
    @Override
    public StudentScore update(StudentScore studentScore) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("grade", studentScore.getCourse())
                .addValue("feedback", studentScore.getFeedback())
                .addValue("studentId", studentScore.getStudent().getId())
                .addValue("courseId", studentScore.getCourse().getId());

        namedParameterJdbcTemplate.update(SQL_UPDATE, parameters);

        return studentScore;
    }

    @Transactional
    @Override
    public StudentScore create(StudentScore studentScore) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("grade", studentScore.getCourse())
                .addValue("feedback", studentScore.getFeedback())
                .addValue("studentId", studentScore.getStudent().getId())
                .addValue("courseId", studentScore.getCourse().getId());

        namedParameterJdbcTemplate.update(SQL_CREATE, parameters);

        return studentScore;
    }

    @Transactional
    @Override
    public void delete(long userId, long courseId) {
        StudentScore studentScore = read(userId, courseId);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("studentId", studentScore.getStudent().getId())
                .addValue("courseId", studentScore.getCourse().getId());

        namedParameterJdbcTemplate.update(SQL_DELETE, parameters);
    }

    @Override
    public List<StudentScore> list() {
       return namedParameterJdbcTemplate.query(SQL_LIST, new StudentScoreMapper());
    }
}
