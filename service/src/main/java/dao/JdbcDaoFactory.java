package dao;

public interface JdbcDaoFactory {

    /**
     * Create TeacherDao.
     *
     * @return new TeacherDao
     */
    TeacherJdbcDao getTeacherDao();

    /**
     * Create StudentDao.
     *
     * @return new StudentDao
     */
    StudentJdbcDao getStudentDao();

    /**
     * Create CourseDao.
     *
     * @return new CourseDao
     */
    CourseJdbcDao getCourseDao();

    /**
     * Create StudentScoreDao.
     *
     * @return new StudentScoreDao
     */
    StudentScoreJdbcDao getStudentScoreDao();
}
