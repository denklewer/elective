package context;

import dao.Dao;
import dao.JdbcDao;

public class CourseFactory implements Factory<Course> {

    @Override
    public Course getById(int id, Dao<Course,Integer> dao) {
        return dao.read(id);
    }

    /**
     * Create new Course.
     *
     * @param id course id
     * @param name course name
     * @param teacher teacher name
     * @return created Course
     */
    public Course newInstance(int id, String name, Teacher teacher) {
        return new Course(id, name, teacher);
    }

}
