package dao;

import logger.EnableLogging;
import model.Course;
import dao.mappers.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseJdbcDaoImpl implements CourseDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_READ = "select " +
            " instructor_id," +
            " first_name instructor_first_name, " +
            " last_name  instructor_last_name, " +
            " login instructor_login, " +
            " password instructor_password, " +
            " email instructor_email, " +
            " course_id," +
            " course_name," +
            " start_date," +
            " end_date from " +
            " User join Course " +
            " on (user_id = instructor_id) " +
            " where course_id = :courseId;";

    private final String SQL_UPDATE = "update Course set " +
            "course_name = :courseName, " +
            "instructor_id = :instructorId, " +
            "start_date = :startDate, " +
            "end_date = :endDate " +
            "where course_id = :courseId";

    private final String SQL_CREATE = "INSERT INTO" +
            " Course(course_name, instructor_id, start_date, end_date)" +
            " VALUES (:courseName, :instructorId, :startDate, :endDate);";

    private final String SQL_DELETE = "DELETE FROM Course WHERE course_id = :courseId";

    private final String SQL_LIST = "select " +
            " instructor_id," +
            " first_name instructor_first_name, " +
            " last_name  instructor_last_name, " +
            " login instructor_login, " +
            " password instructor_password, " +
            " email instructor_email, " +
            " course_id," +
            " course_name," +
            " start_date," +
            " end_date from " +
            " User join Course " +
            " on (user_id = instructor_id); ";




    @Override
    @EnableLogging
    public Course read(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", id);
        Course course = namedParameterJdbcTemplate.queryForObject(SQL_READ,
                parameters,
                new CourseRowMapper());
        return course;
    }

    @Transactional("transactionManager")
    @Override
    @EnableLogging
    public Course update(Course course) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", course.getId())
                .addValue("courseName",course.getName())
                .addValue("instructorId", course.getInstructor().getId())
                .addValue("startDate", course.getStart())
                .addValue("endDate", course.getEnd());
       long result = namedParameterJdbcTemplate.update(SQL_UPDATE,parameters);
        return course;
    }

    @Transactional("transactionManager")
    @Override
    @EnableLogging
    public Course create(final Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseName",course.getName())
                .addValue("instructorId", course.getInstructor().getId())
                .addValue("startDate",course.getStart())
                .addValue("endDate",course.getEnd());
        long result = namedParameterJdbcTemplate.update(SQL_CREATE,
                parameters,
                keyHolder,
                new String[]{"course_id"});
        int id = keyHolder.getKey().intValue();
        Course returnCourse = Course.newBuilder()
                .setInstructor(course.getInstructor())
                .setStart(course.getStart())
                .setEnd(course.getEnd())
                .setName(course.getName())
                .setId(id)
                .build();
        return returnCourse;
    }

    @Transactional("transactionManager")
    @Override
    @EnableLogging
    public void delete(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", id);
        long result = namedParameterJdbcTemplate.update(SQL_DELETE,parameters);

    }

    @Override
    @EnableLogging
    public List<Course> list() {
      return namedParameterJdbcTemplate.query(SQL_LIST,new CourseRowMapper());
    }

}
