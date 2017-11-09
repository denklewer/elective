package dao;

import model.Course;
import java.util.List;

public interface CourseJdbcDao {
    /**
     * Get Course from source by id.
     *
     * @param id Course id in database
     * @return Course from database, where key is id
     */
    public Course read(long id);

    /**
     * Update Course in source.
     *
     * @param course for update
     * @return new update Course
     */
    public Course update(Course course);

    /**
     * Insert Course in data source.
     *
     * @param course for insertion in database
     * @return new Course, which was just now wrote in database
     */
    public Course create(Course course);

    /**
     * Remove Course from table by id.
     *
     * @param id Course id
     * @return Course before delete
     */
    public Course delete(long id);

    /**
     * Get list of available Courses from database.
     *
     * @return list of available Courses
     */
    public List<Course> list();

}
