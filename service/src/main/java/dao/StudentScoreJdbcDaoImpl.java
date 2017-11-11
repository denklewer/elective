package dao;

import com.sun.org.apache.regexp.internal.RE;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import model.StudentScore;
import dao.mappers.StudentScoreMapper;
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
            " WHERE (student_id = :studentId AND" +
            " course_id = :courseId)";

    private final String SQL_UPDATE = "UPDATE Course_participation SET" +
            " grade = :grade," +
            " feedback = :feedback" +
            " WHERE student_id = :studentId AND" +
            " course_id = :courseId";

    private final String SQL_CREATE = "INSERT INTO Course_participation" +
            " (student_id, course_id, grade, feedback)" +
            " VALUES (:studentId, :courseId, :grade, feedback)";

    private final String SQL_DELETE = "DELETE FROM Course_participation" +
            " WHERE student_id = :studentId AND course_id = :courseId";

    private final String SQL_LIST = "SELECT * FROM StudentScore";

    @Override
    public StudentScore read(long userId, long courseId) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", courseId)
                .addValue("studentId", userId);
        try {
            StudentScore studentScore = namedParameterJdbcTemplate.queryForObject(SQL_READ,
                    parameters, new StudentScoreMapper());

            return studentScore;
        } catch (Exception ex) {
            throw new ReadException(ex);
        }
    }

    @Transactional("transactionManager")
    @Override
    public StudentScore update(StudentScore studentScore) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("grade", studentScore.getCourse())
                .addValue("feedback", studentScore.getFeedback())
                .addValue("studentId", studentScore.getStudent().getId())
                .addValue("courseId", studentScore.getCourse().getId());
        try {
            namedParameterJdbcTemplate.update(SQL_UPDATE, parameters);
            return studentScore;
        } catch (Exception ex) {
            throw new UpdateException(ex);
        }
    }

    @Transactional("transactionManager")
    @Override
    public StudentScore create(StudentScore studentScore) {

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("grade", studentScore.getCourse())
                .addValue("feedback", studentScore.getFeedback())
                .addValue("studentId", studentScore.getStudent().getId())
                .addValue("courseId", studentScore.getCourse().getId());
        try {
            namedParameterJdbcTemplate.update(SQL_CREATE, parameters);
            return studentScore;
        } catch (Exception ex) {
            throw new CreateException(ex);
        }
    }

    @Transactional("transactionManager")
    @Override
    public void delete(long userId, long courseId) {
        //StudentScore studentScore = read(userId, courseId);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("studentId", userId)
                .addValue("courseId", courseId);
        try {
            namedParameterJdbcTemplate.update(SQL_DELETE, parameters);
        } catch (Exception ex) {
            throw new DeleteException(ex);
        }
    }

    @Override
    public List<StudentScore> list() {
        try {
            return namedParameterJdbcTemplate.query(SQL_LIST, new StudentScoreMapper());
        } catch (Exception ex) {
            throw new ReadException(ex);
        }
    }
}
