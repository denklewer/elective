package dao;

import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import model.Course;
import dao.mappers.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public class CourseJdbcDaoImpl implements CourseDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_READ = "select * " +
            "from User join Course " +
            "on (user_id = instructor_id) " +
            "where course_id = :courseId";

    private final String SQL_UPDATE = "update Course " +
            "set course_name = :courseName, " +
            "instructor_id = :instructorId, " +
            "start_date = :startDate, " +
            "end_date = :endDate " +
            "where course_id = :courseId";

    private final String SQL_CREATE = "INSERT INTO" +
            " Course(course_name, instructor_id, start_date, end_date)" +
            " VALUES (:courseName, :instructorId, :startDate, :endDate)";

    private final String SQL_DELETE = "DELETE FROM Course WHERE course_id = :courseId";

    private final String SQL_LIST = "SELECT * FROM Course JOIN User " +
            "ON (user_id = instructor_id)";


    @Override
    public Course read(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", id);
        try {
            Course course = namedParameterJdbcTemplate.queryForObject(SQL_READ,
                    parameters,
                    new CourseRowMapper());
            return course;
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }

    }

    @Transactional("transactionManager")
    @Override
    public Course update(Course course) {
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("courseId", course.getId())
                    .addValue("courseName", course.getName())
                    .addValue("instructorId", course.getInstructor().getId())
                    .addValue("startDate", Date.valueOf(course.getStart()))
                    .addValue("endDate", Date.valueOf(course.getEnd()));
            long result = namedParameterJdbcTemplate.update(SQL_UPDATE, parameters);
            return course;
        } catch (Exception ex) {
            throw new UpdateException(ex);

        }
    }

    @Transactional("transactionManager")
    @Override
    public Course create(final Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseName", course.getName())
                .addValue("instructorId", course.getInstructor().getId())
                .addValue("startDate", Date.valueOf(course.getStart()))
                .addValue("endDate", Date.valueOf(course.getEnd()));
        try {
            long result = namedParameterJdbcTemplate.update(SQL_CREATE,
                    parameters,
                    keyHolder,
                    new String[]{"course_id"});
            long id = keyHolder.getKey().intValue();
            Course returnCourse = Course.newBuilder()
                    .setInstructor(course.getInstructor())
                    .setStart(course.getStart())
                    .setEnd(course.getEnd())
                    .setName(course.getName())
                    .setId(id)
                    .build();
            return returnCourse;
        }
        catch (DataAccessException ex){
            throw  new UpdateException(ex);
        }
        catch (Exception ex) {
            throw  new RuntimeException("ID extraction error", ex);
        }

    }

    @Transactional("transactionManager")
    @Override
    public void delete(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", id);
        try {
            long result = namedParameterJdbcTemplate.update(SQL_DELETE, parameters);
        }
        catch (Exception ex) {
            throw  new DeleteException(ex);
        }
    }

    @Override
    public List<Course> list() {
        try {
            List<Course> list = namedParameterJdbcTemplate.query(SQL_LIST, new CourseRowMapper());
            return list;
        }
        catch (Exception ex){
            throw  new ReadException(ex);
        }
    }

}
