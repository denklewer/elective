package context;

import dao.Dao;

public class CourseFactory implements Factory<Course> {

    @Override
    public Course getById(int id, Dao<Course> dao) {
        return dao.loadById(id);
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
