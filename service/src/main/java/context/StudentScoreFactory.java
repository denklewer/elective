package context;

import dao.Dao;

/**
 * Created by Вера on 06.11.2017.
 */
public class StudentScoreFactory implements Factory<StudentScore> {

    @Override
    public StudentScore getById(int id, Dao<StudentScore, Integer> dao) {
        return dao.read(id);
    }

    /**
     * Get new StudentScore instance.
     * @param score student's score
     * @param feedback feedback from Teacher
     * @return new instance of class StudentScore
     */
    public StudentScore newInstance(byte score, String feedback) {
        return new StudentScore(score, feedback);
    }

}
