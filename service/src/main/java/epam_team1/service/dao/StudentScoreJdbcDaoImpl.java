package epam_team1.service.dao;


import epam_team1.service.dao.mappers.StudentScoreMapper;
import epam_team1.service.logger.EnableLogging;
import epam_team1.service.model.StudentScore;

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


    private final String SQL_READ =
            "SELECT * FROM " +

                    "( SELECT  *" +
                    " FROM" +
                    " Course_participation cp JOIN User u ON (cp.student_id = u.user_id)" +
                    " WHERE cp.student_id = :studentId AND" +
                    " cp.course_id = :courseId) t1 " +
                            " JOIN " +
                    " ( SELECT " +
                    " u.user_id instructor_id," +
                    " u.first_name instructor_first_name," +
                    " u.last_name instructor_last_name," +
                    " u.email instructor_email," +
                    " u.Password instructor_password," +
                    " u.Login instructor_login," +
                    " cp.course_id course_id," +
                    " cp.student_id student_id," +
                    " c.course_name, " +
                    " c.start_date, " +
                    " c.end_date" +
                    " FROM " +
                    " Course_participation cp JOIN Course c ON (c.course_id = cp.course_id) JOIN User u ON (c.instructor_id = u.user_id) " +
                    " WHERE cp.student_id = :studentId AND " +
                    " cp.course_id = :courseId) t2 " +
                    " ON t1.course_id = t2.course_id; ";


    private final String SQL_UPDATE = "UPDATE Course_participation" +
            " SET" +
            " grade = :grade, " +
            " feedback = :feedback " +
            " WHERE student_id = :studentId AND " +
            " course_id = :courseId;";

    private final String SQL_CREATE = "INSERT INTO Course_participation" +
            " (student_id, course_id, grade, feedback)" +
            " VALUES (:studentId, :courseId, :grade, :feedback)";

    private final String SQL_DELETE = "DELETE FROM Course_participation" +
            " WHERE student_id = :studentId and course_id = :courseId";

    private final String SQL_LIST = "SELECT * FROM StudentScore";

    @Override
    @EnableLogging
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
    @EnableLogging
    public StudentScore update(StudentScore studentScore) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("grade", studentScore.getScore())
                .addValue("feedback", studentScore.getFeedback())
                .addValue("studentId", studentScore.getStudent().getId())
                .addValue("courseId", studentScore.getCourse().getId());

        namedParameterJdbcTemplate.update(SQL_UPDATE, parameters);

        return studentScore;
    }

    @Transactional
    @Override
    @EnableLogging
    public StudentScore create(StudentScore studentScore) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("grade", studentScore.getScore())
                .addValue("feedback", studentScore.getFeedback())
                .addValue("studentId", studentScore.getStudent().getId())
                .addValue("courseId", studentScore.getCourse().getId());

        namedParameterJdbcTemplate.update(SQL_CREATE, parameters);

        return studentScore;
    }

    @Transactional
    @Override
    @EnableLogging
    public void delete(long userId, long courseId) {
        StudentScore studentScore = read(userId, courseId);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("studentId", studentScore.getStudent().getId())
                .addValue("courseId", studentScore.getCourse().getId());

        namedParameterJdbcTemplate.update(SQL_DELETE, parameters);
    }

    @Override
    @EnableLogging
    public List<StudentScore> list() {
        return namedParameterJdbcTemplate.query(SQL_LIST, new StudentScoreMapper());
    }
}
