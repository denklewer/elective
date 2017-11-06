package dao;

import context.Course;

import java.util.ArrayList;

public class CourseJdbcDao implements JdbcDao<Course> {

    @Override
    public Course read(int id) {
        return null;
    }

    @Override
    public void update(Course course) {

    }

    @Override
    public Integer create(Course course) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArrayList<Course> list() {
        return null;
    }
}
