package epam_team1.service.dao;

import epam_team1.service.dao.exceptions.CreateException;
import epam_team1.service.dao.exceptions.DeleteException;
import epam_team1.service.dao.exceptions.ReadException;
import epam_team1.service.dao.exceptions.UpdateException;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseJdbcDaoImpl implements CourseDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_READ = "SELECT " +
            " instructor_id," +
            " first_name instructor_first_name, " +
            " last_name  instructor_last_name, " +
            " login instructor_login, " +
            " password instructor_password, " +
            " email instructor_email, " +
            " course_id," +
            " course_name," +
            " start_date," +
            " end_date FROM " +
            " User JOIN Course " +
            " ON (user_id = instructor_id) " +
            " WHERE course_id = :courseId;";

    private final String SQL_UPDATE = "UPDATE Course SET " +
            "course_name = :courseName, " +
            "instructor_id = :instructorId, " +
            "start_date = :startDate, " +
            "end_date = :endDate " +
            "WHERE course_id = :courseId";

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

//    private final String SQL_COURSE_LIST_BY_STUDENT_ID = "SELECT * FROM " +
//            " Course c JOIN " +
//            " first_name instructor_first_name, " +
//            " last_name  instructor_last_name, " +
//            " login instructor_login, " +
//            " password instructor_password, " +
//            " email instructor_email, " +
//            " Course_participation cp ON c.course_id = cp.course_id " +
//            " WHERE student_id = :studentId;";

    private final String SQL_COURSE_LIST_BY_STUDENT_ID = "SELECT c.course_id, " +
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
            " ON (c.course_id = cp.course_id AND cp.student_id = :userId) " +
            " JOIN User u ON (u.user_id = c.instructor_id) " +
            " WHERE  cp.course_id IS NULL);";


    @Override
    @EnableLogging
    public Course read(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", id);
        try {
            Course course = namedParameterJdbcTemplate.queryForObject(SQL_READ,
                    parameters,
                    new CourseRowMapper());
            return course;
        } catch (Exception ex) {
            throw new ReadException(ex);
        }
    }

    @Transactional
    @Override
    @EnableLogging
    public Course update(Course course) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", course.getId())
                .addValue("courseName", course.getName())
                .addValue("instructorId", course.getInstructor().getId())
                .addValue("startDate", course.getStart())
                .addValue("endDate", course.getEnd());
        try {
            long result = namedParameterJdbcTemplate.update(SQL_UPDATE, parameters);
            return course;
        } catch (Exception ex) {
            throw new UpdateException(ex);
        }
    }

    @Transactional
    @Override
    @EnableLogging
    public Course create(final Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseName", course.getName())
                .addValue("instructorId", course.getInstructor().getId())
                .addValue("startDate", course.getStart())
                .addValue("endDate", course.getEnd());
        try {
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
        } catch (Exception ex) {
            throw new CreateException(ex);
        }
    }

    @Transactional
    @Override
    @EnableLogging
    public void delete(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", id);
        try {
            long result = namedParameterJdbcTemplate.update(SQL_DELETE, parameters);
        } catch (Exception ex) {
            throw new DeleteException(ex);
        }

    }

    @Override
    @EnableLogging
    public List<Course> list() {
        try {
            List<Course> courses = namedParameterJdbcTemplate.query(SQL_LIST, new CourseRowMapper());
            System.out.println("DAO: " + courses);
            return courses;
        } catch (Exception ex) {
            throw new ReadException(ex);
        }
    }

    @Override
    public List<Course> listByStudentId(long studentId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("studentId", studentId);
        try {

            List<Course> courseList = namedParameterJdbcTemplate
                    .query(SQL_COURSE_LIST_BY_STUDENT_ID, parameters, new SecureCourseRowMapper());
            return courseList;
        } catch (Exception ex) {
            throw new ReadException(ex);
        }
    }

    @Override
    @EnableLogging
    public List<Course> listByStudentIdExceptMine(long studentId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", studentId);
        try {
            return namedParameterJdbcTemplate.query(SQL_GET_COURSES_EXCEPT_MINE, parameters, new SecureCourseRowMapper());
        } catch (Exception ex) {
            throw new ReadException(ex);
        }
    }

}
