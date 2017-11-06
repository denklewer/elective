package dao;

import context.StudentScore;
import dao.support.Pair;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вера on 06.11.2017.
 */
public class StudentScoreJdbcDao implements JdbcDao<StudentScore,Pair> {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public StudentScore read(Pair id) {
        return null;
    }

    @Override
    public void update(StudentScore studentScore) {

    }

    @Override
    public Pair create(StudentScore studentScore) {
        return null;
    }

    @Override
    public void delete(Pair id) {

    }

    @Override
    public List<StudentScore> list() {
        return null;
    }
}
