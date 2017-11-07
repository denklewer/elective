package dao;

import context.Course;
import context.Teacher;
import dao.mappers.TeacherMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class TeacherJdbcDao implements JdbcDao<Teacher,Integer> {

    private JdbcTemplate jdbcTemplate;
    private JdbcDaoFactory  daoFactory;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, JdbcDaoFactory factory) {
        this.jdbcTemplate = jdbcTemplate;
        this.daoFactory = factory;
    }

    @Override
    public Teacher read(Integer id) {
        String sql = "SELECT * FROM Teacher WHERE  teacher_id= ? ";
        Teacher teacher = jdbcTemplate.queryForObject(sql, new TeacherMapper(), id);
        CourseJdbcDao courseDao = daoFactory.getCourseDao();
        List<Course> courseList = courseDao.getByTeacherId(teacher.getId());
        teacher.setCourses(courseList);


        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE Teacher SET first_name = ?, last_name = ? WHERE  teacher_id= ?";
        jdbcTemplate.update(sql, teacher.getFirstName(), teacher.getLastName(), teacher.getId());
        CourseJdbcDao courseDao = daoFactory.getCourseDao();
    }

    @Override
    public Integer create(Teacher teacher) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = con -> {
            PreparedStatement statement =
                    con.prepareStatement("INSERT INTO Teacher(first_name,last_name) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            return statement;
        };
        jdbcTemplate.update(creator, keyHolder);
        int id = keyHolder.getKey().intValue();
        teacher.setId(id);
        return id;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Teacher WHERE teacher_id = ?";
        jdbcTemplate.update(sql, id);
    }


    @Override
    public List<Teacher> list() {
        String sql = "SELECT * from Teacher";
        return  jdbcTemplate.query(sql, new TeacherMapper());
    }


}
