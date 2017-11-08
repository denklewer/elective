package model;

public class StudentScore {

    private User student;
    private Course course;
    private int score;
    private String feedback;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Builder newBuilder(){
        return new StudentScore().new Builder();
    }

    public class Builder{

        private Builder() {
        }

        public Builder setScore(int score) {
            StudentScore.this.score = score;
            return this;
        }

        public Builder setFeedback(String feedback) {
            StudentScore.this.feedback = feedback;
            return this;
        }

        public Builder setStudent(User student) {
            StudentScore.this.student = student;
            return this;
        }

        public Builder setCourse(Course course) {
            StudentScore.this.course = course;
            return this;
        }

        public StudentScore build(){
            return StudentScore.this;
        }
    }
}
