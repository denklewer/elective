package context;

import dao.Dao;
import dao.JdbcDao;

import java.util.List;

public class CourseFactory implements Factory<Course> {

    @Override
    public Course getById(int id, Dao<Course,Integer> dao) {
        return dao.read(id);
    }

    /**
     * Create new Course.
     *
     * @param name course name
     * @return created Course
     */
    public Course newInstance(int id, String name, Teacher teacher, List<Student> students) {
        Course course = new Course(name);
        course.setId(id);
        course.setTeacher(teacher);
        course.setStudents(students);
        return course;
    }

}
