package epam_team1.service.services;

import epam_team1.service.dao.CourseDao;
import epam_team1.service.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseManagerImpl implements CourseManager {
    @Autowired
    private CourseDao courseDao;
    @Override
    public Course readById(long id) {
        return courseDao.read(id);
    }

    @Override
    public Course update(Course course) {
        return courseDao.update(course);
    }

    @Override
    public Course create(Course course) {
        return courseDao.create(course);
    }

    @Override
    public void deleteById(long id) {
        courseDao.delete(id);
    }

    @Override
    public List<Course> list() {
        return courseDao.list();
    }
}
