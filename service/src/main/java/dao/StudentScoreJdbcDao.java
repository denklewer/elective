package dao;

import context.StudentScore;
import context.Teacher;
import dao.mappers.StudentScoreMapper;
import dao.mappers.TeacherMapper;
import dao.support.Pair;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class StudentScoreJdbcDao implements JdbcDao<StudentScore, Pair> {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public StudentScore read(Pair pair) {
        String sql = "SELECT * FROM Course_participation student_id = ?," +
                "course_id = ?";

        StudentScore studentScore = jdbcTemplate.queryForObject(sql, new StudentScoreMapper(),
                pair.getStudentId(), pair.getCourseId());

        return studentScore;
    }

    @Override
    public void update(StudentScore studentScore) {
        String sql = "UPDATE Course_participation assesment_grade = ?, teacher_feedback = ?" +
                "WHERE student_id = ?, course_id = ?";

        jdbcTemplate.update(sql, studentScore.getScore(), studentScore.getFeedback(),
                );

    }

    @Override
    public Pair create(StudentScore studentScore) {

        return null;
    }

    @Override
    public void delete(Pair id) {

    }

    @Override
    public ArrayList<StudentScore> list() {
        return null;
    }
}
