package context;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private Map<Course, StudentScore> mapCourse = new HashMap<>();

    /**
     * Student constructor.
     *
     * @param id student's id
     * @param firstName student's first name
     * @param lastName student's last name
     */
    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<Course, StudentScore> getMapCourse() {
        return mapCourse;
    }

    public void setMapCourse(Map<Course, StudentScore> mapCourse) {
        this.mapCourse = mapCourse;
    }

    public void addMapCourse(Course course) {
        mapCourse.put(course, new StudentScore());
    }

    @Override
    public String toString() {
        return "Student{"
                + "id = " + id
                + ", first name = " + firstName
                + ", last name = " + lastName
                + ", cources " + mapCourse
                + '}';
    }

}
