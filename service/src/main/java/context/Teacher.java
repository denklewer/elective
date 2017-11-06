package context;

import java.util.ArrayList;
import java.util.Collection;

public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private Collection<Course> courses;

    public Teacher() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) {
            return false;
        }
        if (firstName != null
                ? !firstName.equals(teacher.firstName)
                : teacher.firstName != null) {
            return false;
        }
        if (lastName != null
                ? !lastName.equals(teacher.lastName)
                : teacher.lastName != null) {
            return false;
        }
        return courses != null ? courses.equals(teacher.courses) : teacher.courses == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }

    /**
     * Teacher constructor.
     *
     * @param id        teacher's id
     * @param firstName teacher's first name
     * @param lastName  teacher's last name
     */
    public Teacher(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        courses = new ArrayList<>();
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
