package context;

public class StudentScore {
    private byte score;
    private String feedback;

    /**
     * StudentScore constructor.
     */
    public StudentScore() {
        this.score = 0;
        this.feedback = "";
    }

    /**
     * StudentScore constructor.
     *
     * @param score student's score
     * @param feedback feedback from teacher to this student
     */
    public StudentScore(byte score, String feedback) {
        this.score = score;
        this.feedback = feedback;
    }

    public byte getScore() {
        return score;
    }

    public void setScore(byte score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return  "StudentScore{"
                + "score = " + score
                + ", feedback = " + feedback
                + '}';
    }

}
