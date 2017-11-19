package epam_team1.service.services;

import epam_team1.service.dao.CourseDao;
import epam_team1.service.logger.EnableLogging;
import epam_team1.service.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseManagerImpl implements CourseManager {
    @Autowired
    private CourseDao courseDao;
    @Override
    @EnableLogging
    public Course readById(long id) {
        return courseDao.read(id);
    }

    @Override
    @EnableLogging
    public Course update(Course course) {
        return courseDao.update(course);
    }

    @Override
    @EnableLogging
    public Course create(Course course) {
        return courseDao.create(course);
    }

    @Override
    @EnableLogging
    public void deleteById(long id) {
        courseDao.delete(id);
    }
    @Override
    @EnableLogging
    public List<Course>  listByStudentId(long id, int limit , int page) {
       return courseDao.listByStudentId(id, limit, limit * page);
    }
    @Override
    @EnableLogging
    public List<Course>  listByStudentIdExceptMine(long id, int limit , int page) {
        return courseDao.listByStudentIdExceptMine(id, limit,limit * page);
    }


    @Override
    @EnableLogging
    public List<Course> list() {
        return courseDao.list();
    }

    @Override
    @EnableLogging
    public List<Course> listByInstructorId(long id, int limit , int page) {
        return courseDao.listByInstructorId(id, limit, limit * page);
    }
}
