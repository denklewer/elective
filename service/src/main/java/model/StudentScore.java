package model;

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
}
