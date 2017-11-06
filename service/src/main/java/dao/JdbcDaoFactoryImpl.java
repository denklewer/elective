package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class JdbcDaoFactoryImpl implements JdbcDaoFactory {
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor for factory.
     *
     * @param dataSource you're DataSource for use.
     */
    public JdbcDaoFactoryImpl(DriverManagerDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TeacherJdbcDao getTeacherDao() {
        TeacherJdbcDao teacherJdbcDao = new TeacherJdbcDao();
        teacherJdbcDao.setJdbcTemplate(jdbcTemplate);
        return teacherJdbcDao;
    }

    @Override
    public StudentJdbcDao getStudentDao() {
        StudentJdbcDao studentJdbcDao = new StudentJdbcDao();
        studentJdbcDao.setJdbcTemplate(jdbcTemplate);
        return studentJdbcDao;
    }

    @Override
    public CourseJdbcDao getCourseDao() {
        CourseJdbcDao courseJdbcDao = new CourseJdbcDao();
        courseJdbcDao.setJdbcTemplate(jdbcTemplate);
        return courseJdbcDao;
    }

    @Override
    public StudentScoreJdbcDao getStudentScoreDao() {
        return null;
    }
}
