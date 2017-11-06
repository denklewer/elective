package dao;

import context.Student;

import java.util.ArrayList;

/**
 * Created by Вера on 06.11.2017.
 */
public class StudentJdbcDao implements JdbcDao<Student> {
    @Override
    public Student read(int id) {
        return null;
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public Integer create(Student student) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArrayList<Student> list() {
        return null;
    }
}
