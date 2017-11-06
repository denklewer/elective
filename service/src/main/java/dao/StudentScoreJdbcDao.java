package dao;

import context.StudentScore;

import java.util.ArrayList;

/**
 * Created by Вера on 06.11.2017.
 */
public class StudentScoreJdbcDao implements JdbcDao<StudentScore> {
    @Override
    public StudentScore read(int id) {
        return null;
    }

    @Override
    public void update(StudentScore studentScore) {

    }

    @Override
    public Integer create(StudentScore studentScore) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArrayList<StudentScore> list() {
        return null;
    }
}
