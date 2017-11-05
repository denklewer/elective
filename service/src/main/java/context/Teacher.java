package context;

import java.util.Collection;

public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private Collection<Course> courses;

    /**
     * Teacher constructor.
     *
     * @param id teacher's id
     * @param firstName teacher's first name
     * @param lastName teacher's last name
     */
    public Teacher(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{"
                + "id=" + id
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", courses=" + courses
                + '}';
    }
}
