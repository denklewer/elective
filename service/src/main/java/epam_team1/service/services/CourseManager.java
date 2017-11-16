package epam_team1.service.services;

import epam_team1.service.model.Course;

import java.util.List;

public interface CourseManager {
    /**
     * Get Course from source by id.
     *
     * @param id Course id in database
     * @return Course from database, where key is id
     */
    Course readById(long id);

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
    void deleteById(long id);

    /**
     * Get list of available Courses from database.
     *
     * @return list of available Courses
     */
    List<Course> list();

    /**
     * get list of student's courses.
     * @param id student's id
     * @return list of student's courses.
     */
    List<Course> listByStudentId(long id);
    /**
     * get list of student's courses.
     * @param id student's id
     * @return list of student's courses.
     */
    List<Course> listByStudentIdExceptMine(long id);

    /**
     * Get list of courses, which this instructor teach.
     *
     * @param id instructor's id
     * @return list of courses, which person teach
     */
    List<Course> listByInstructorId(long id);
}
