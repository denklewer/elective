package model;

import java.time.LocalDate;

public class Course {
    private final long id;
    private final String name;
    private final User instructor;
    private final LocalDate start;
    private final LocalDate end;

    private Course(long id, String name, User instructor, LocalDate start, LocalDate end) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getInstructor() {
        return instructor;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructor=" + instructor +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (instructor != null ? !instructor.equals(course.instructor) : course.instructor != null) return false;
        if (start != null ? !start.equals(course.start) : course.start != null) return false;
        return end != null ? end.equals(course.end) : course.end == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (instructor != null ? instructor.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    public static class Builder {

        private long id;
        private String name;
        private User instructor;
        private LocalDate start;
        private LocalDate end;

        private Builder() {

        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setInstructor(User instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder setStart(LocalDate start) {
            this.start = start;
            return this;
        }

        public Builder setEnd(LocalDate end) {
            this.end = end;
            return this;
        }

        public Course build(){
            return new Course(id, name, instructor, start, end);
        }
    }
}
