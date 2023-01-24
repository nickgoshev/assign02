package assign02;

public class CS2420Student extends UofUStudent {

    private EmailAddress contactInfo;

    private class CourseWork {
        private double score;
        private int count;

        private double computeAvg() {
            return score/(count * 100);
        }
    }

    private CourseWork assignments = new CourseWork();
    private CourseWork quizzes = new CourseWork();
    private CourseWork labs = new CourseWork();
    private CourseWork exams = new CourseWork();


    /**
     * Creates a student from the given first name, last name, and uNID.
     *
     * @param firstName
     * @param lastName
     * @param uNID
     * @param contactInfo
     */
    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;
    }

    public EmailAddress getContactInfo() {
      return contactInfo;
    }

    public void addScore(double score, String category) {
        switch(category) {
            case "assigment":
                assignments.score += score;
                assignments.count++;
                break;
            case "exam":
                exams.score += score;
                exams.count++;
                break;
            case "lab":
                labs.score += score;
                labs.count++;
                break;
            case "quiz":
                quizzes.score += score;
                quizzes.count++;
                break;
        }
    }

    public double computeFinalScore() {
        // if exam average is below 65%, return exam average as final score;
        if (exams.computeAvg() < 0.65) {
            return exams.computeAvg() * 100;
        }

        System.out.println(assignments.score);
        System.out.println(quizzes.score);
        System.out.println(labs.score);
        System.out.println(exams.score);
        // Create array to loop over and check for any 0s
        CourseWork[] scores = new CourseWork[] {assignments, exams, quizzes, labs};
        double studentScore = 0;
        for (CourseWork category : scores) {
            System.out.println(category.score);
            if(category.score == 0) {
                return 0;
            } else {

                studentScore += category.computeAvg();
            }
        }


        return (studentScore / scores.length) ;
    }

    public String computeFinalGrade() {
        return "";
    }

}
