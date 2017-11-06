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
        return null;
    }

    @Override
    public StudentJdbcDao getStudentDao() {
        return null;
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
