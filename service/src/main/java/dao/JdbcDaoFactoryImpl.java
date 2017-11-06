package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


public class JdbcDaoFactoryImpl implements JdbcDaoFactory {
    private JdbcTemplate jdbcTemplate;

    public JdbcDaoFactoryImpl(SimpleDriverDataSource dataSource) {
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
