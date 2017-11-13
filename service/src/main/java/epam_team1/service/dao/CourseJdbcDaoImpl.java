package epam_team1.service.dao;

import epam_team1.service.dao.mappers.SecureCourseRowMapper;
import epam_team1.service.dao.mappers.CourseRowMapper;
import epam_team1.service.logger.EnableLogging;
import epam_team1.service.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


    private final String SQL_COURSE_LIST_BY_STUDENT_ID =  "SELECT c.course_id, " +
            " c.course_name, " +
            " c.start_date, " +
            " c.end_date , " +
            " u.last_name instructor_last_name, " +
            " u.first_name instructor_first_name " +
            " FROM Course c JOIN Course_participation cp " +
            " ON (c.course_id = cp.course_id AND cp.student_id = :studentId) " +
            " JOIN User u ON (u.user_id = c.instructor_id);";


    private final String SQL_GET_COURSES_EXCEPT_MINE = "(SELECT c.course_id, " +
            " course_name, " +
            " start_date, " +
            " end_date , " +
            " u.last_name instructor_last_name, " +
            " u.first_name instructor_first_name " +
            " FROM Course c LEFT JOIN Course_participation cp " +
            " ON (c.course_id = cp.course_id and cp.student_id = :userId) " +
            " JOIN User u ON (u.user_id = c.instructor_id) " +
            " WHERE  cp.course_id IS NULL);";


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

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Override
    public List<Course> listByStudentId(long studentId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("studentId", studentId);

        List<Course> courseList = namedParameterJdbcTemplate
                .query(SQL_COURSE_LIST_BY_STUDENT_ID, parameters, new SecureCourseRowMapper());
        return courseList;
    }
    @Override
    @EnableLogging
    public List<Course> listByStudentIdExeptMine( long studentId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", studentId);
        return namedParameterJdbcTemplate.query(SQL_GET_COURSES_EXCEPT_MINE,parameters,new SecureCourseRowMapper());
    }

}
