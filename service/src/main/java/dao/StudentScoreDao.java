package epam_team1.service.dao;

import epam_team1.service.model.StudentScore;

import java.util.List;

public interface StudentScoreDao {

    /**
     * Get StudentScore from source by key : userId and courseId.
     *
     * @param userId   StudentScore userId in database
     * @param courseId StudentScore courseId in database
     * @return StudentScore from database
     */
    StudentScore read(long userId, long courseId);

    /**
     * Update StudentScore in source.
     *
     * @param studentScore for update
     * @return new update StudentScore
     */
    StudentScore update(StudentScore studentScore);

    /**
     * Insert StudentScore in data source.
     *
     * @param studentScore for insertion in database
     * @return new StudentScore, which was just now wrote in database
     */
    StudentScore create(StudentScore studentScore);

    /**
     * Remove StudentScore from table by id.
     *
     * @param userId   StudentScore userId
     * @param courseId StudentScore courseId
     */
    void delete(long userId, long courseId);

    /**
     * Get list of available StudentScore from database.
     *
     * @return List of available StudentScore
     */
    List<StudentScore> list();
}
