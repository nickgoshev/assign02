package assign02;

public class CS2420Student extends UofUStudent {

    private EmailAddress contactInfo;

    // nested class to be used for each category of assignments
    private class CourseWork {
        private double score;
        private int count;

        private double computeAvg() {
            return score/count;
        }
    }

    // CourseWork objects for each assignment
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
            case "assignment":
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
        // check if student has turned in at least one assignment per category, otherwise return 0;
        if (exams.count == 0 || quizzes.count == 0 || labs.count == 0||assignments.count==0) {
            return 0;
        }

        // if exam average is below 65%, return exam average as final score;
        if (exams.computeAvg() < 65) {
            return exams.computeAvg();
        }

        // compute weighted average;
        double finalScore =(exams.computeAvg()*.4)+
                (assignments.computeAvg()*.4)+
                (labs.computeAvg()*.1)+
                (quizzes.computeAvg()*.1);


        return finalScore;
    }

    public String computeFinalGrade() {
        if (exams.count == 0 || quizzes.count == 0 || labs.count == 0||assignments.count==0) {
            return "N/A";
        }

        double fScore = computeFinalScore();

        double [] threshold = new double[] {93,90,87,83,80,77,73,70,67,63,60};
        String [] letterGrade = new String[] {"A","A-","B+","B","B-","C+","C","C-","D+","D","D-"};

        for (int i = 0; i < threshold.length; i++) {
            if (fScore >= threshold[i]) {
                return letterGrade[i];
            }
        }

        return "E";
    }

}
