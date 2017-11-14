package model;

import java.io.Serializable;

public class StudentScore {

    private final User student;
    private final Course course;
    private final int score;
    private final String feedback;

    public StudentScore(User student, Course course, int score, String feedback) {
        this.student = student;
        this.course = course;
        this.score = score;
        this.feedback = feedback;
    }

    public User getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder{

        private User student;
        private Course course;
        private int score;
        private String feedback;

        private Builder() {
        }

        public Builder setScore(int score) {
            this.score = score;
            return this;
        }

        public Builder setFeedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public Builder setStudent(User student) {
            this.student = student;
            return this;
        }

        public Builder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public StudentScore build(){
            return new StudentScore(student, course, score, feedback);
        }
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "student=" + student +
                ", course=" + course +
                ", score=" + score +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentScore that = (StudentScore) o;

        if (score != that.score) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        return feedback != null ? feedback.equals(that.feedback) : that.feedback == null;
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        return result;
    }
}
