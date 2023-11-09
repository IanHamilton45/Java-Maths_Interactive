/*
 *  Andrew Ward, ID: 15002106
 */
package quiz;

/**
 *
 * @author Andrew Ward W15002106
 */
public class UserAnswers {

    private String answer1;
    private String answer2;

    /**
     * Constructor
     */
    public UserAnswers() {
    }

    /**
     *
     * @param answer
     */
    public void setAnswer1(String answer) {
        this.answer1 = answer;
    }

    /**
     *
     * @param answer
     */
    public void setAnswer2(String answer) {
        this.answer2 = answer;
    }

    /**
     *
     * @return answer1
     */
    public String getAnswer1() {
        return this.answer1;
    }

    /**
     *
     * @return answer2
     */
    public String getAnswer2() {
        return this.answer2;
    }

    /**
     *
     * @return answer1 and answer2 as an array
     */
    public String[] getAllAnswers() {
        String[] allAnswers = {this.getAnswer1(), this.getAnswer2()};
        return allAnswers;
    }

    /**
     * 
     * @return String of concatenated answers
     */
    public String answersToString() {
        String answers = "";
        if (this.getAnswer2() != null) {
            answers = this.getAnswer1() + " " + this.getAnswer2();
        } else {
            answers = this.getAnswer1();
        }
        return answers;
    }
}
