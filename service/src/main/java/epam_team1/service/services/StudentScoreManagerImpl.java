package epam_team1.service.services;

import epam_team1.service.dao.StudentScoreDao;
import epam_team1.service.logger.EnableLogging;
import epam_team1.service.model.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class StudentScoreManagerImpl implements StudentScoreManager {
    @Autowired
    StudentScoreDao studentScoreDao;

    @Override
    @EnableLogging
    public StudentScore read(long userId, long courseId) {
        return studentScoreDao.read(userId,courseId);
    }

    @Override
    @EnableLogging
    public List<StudentScore> list(long userId) {
        return studentScoreDao.list(userId);
    }

    @Override
    @EnableLogging
    public StudentScore update(StudentScore studentScore) {
        return studentScoreDao.update(studentScore);
    }

    @Override
    @EnableLogging
    public StudentScore create(StudentScore studentScore) {
        return studentScoreDao.create(studentScore);
    }

    @Override
    @EnableLogging
    public void delete(long userId, long courseId) {
        studentScoreDao.delete(userId,courseId);

    }
}
