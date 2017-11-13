package epam_team1.service.services;

import epam_team1.service.dao.StudentScoreDao;
import epam_team1.service.model.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentScoreManagerImpl implements StudentScoreManager {
    @Autowired
    StudentScoreDao studentScoreDao;
    @Override
    public StudentScore read(long userId, long courseId) {
        return studentScoreDao.read(userId,courseId);

    }

    @Override
    public StudentScore update(StudentScore studentScore) {
        return studentScoreDao.update(studentScore);
    }

    @Override
    public StudentScore create(StudentScore studentScore) {
        return studentScoreDao.create(studentScore);
    }

    @Override
    public void delete(long userId, long courseId) {
        studentScoreDao.delete(userId,courseId);

    }

    @Override
    public List<StudentScore> list() {
        return null;
    }
}
