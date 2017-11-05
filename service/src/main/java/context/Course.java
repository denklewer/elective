package context;

import java.util.Collection;

public class Course {
    private int id;
    private String name;
    private Teacher teacher;
    private Collection<Student> students;

    /**
     * Course constructor.
     *
     * @param id Course id
     * @param name Course name
     * @param teacher Course teacher
     */
    public Course(int id, String name, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", teacher=" + teacher
                + ", students=" + students
                + '}';
    }
}
