package dao;

import context.Course;
import context.Teacher;
import dao.mappers.CourseMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseJdbcDao implements JdbcDao<Course, Integer> {

    private JdbcTemplate jdbcTemplate;
    private JdbcDaoFactory factory;
    private TeacherJdbcDao teacherJdbcDao;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, JdbcDaoFactory factory) {
        this.jdbcTemplate = jdbcTemplate;
        this.factory = factory;
        this.teacherJdbcDao = factory.getTeacherDao();
    }

    @Override
    public Course read(Integer id) {
        String sql = "select * from Course where course_id = ?";
        Course course = jdbcTemplate.queryForObject(sql,
                new Object[]{id}, new CourseMapper());
        getTeacher(course);

        return course;
    }

    @Override
    public void update(Course course) {
        String sql = "update Course set course_name, teacher_id = ?, ? where course_id = ?)";
        jdbcTemplate.update(sql, course.getName(), course.getId());
    }

    @Override
    public Integer create(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = con -> {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO Course(course_name, teacher_id) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, course.getName());
            if(course.getTeacher() != null) {
                statement.setInt(2, course.getTeacher().getId());
            }else{
                statement.setInt(2, 0);
            }
            return statement;
        };
        jdbcTemplate.update(creator, keyHolder);
        int id = keyHolder.getKey().intValue();
        course.setId(id);
        return id;
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from Course where course_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Course> list() {
        String sql = "SELECT * from Course";
        ArrayList<Course> courses =
                (ArrayList<Course>) jdbcTemplate.query(sql, new CourseMapper());
        courses.forEach(this::getTeacher);
        return courses;
    }

    public List<Course> getByTeacherId(int id) {
        String sql = "select * from Course where teacher_id = ?";
        return jdbcTemplate.query(sql, new CourseMapper(), id);
    }

    private void getTeacher(Course course) {
        Teacher teacher = teacherJdbcDao.read(course.getTeacher().getId());
        course.setTeacher(teacher);
    }
}
