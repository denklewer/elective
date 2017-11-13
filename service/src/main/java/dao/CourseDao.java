package dao;

import model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    /**
     * Get Course from source by id.
     *
     * @param id Course id in database
     * @return Course from database, where key is id
     */
    Course read(long id);

    /**
     * Update Course in source.
     *
     * @param course for update
     * @return new update Course
     */
    Course update(Course course);

    /**
     * Insert Course in data source.
     *
     * @param course for insertion in database
     * @return new Course, which was just now wrote in database
     */
    Course create(Course course);

    /**
     * Remove Course from table by id.
     *
     * @param id Course id
     */
    void delete(long id);

    /**
     * Get list of available Courses from database.
     *
     * @return list of available Courses
     */
    List<Course> list();

     List<Course> listByStudentIdExeptMine( long studentId);
}
