/*
 *  Andrew Ward, ID: 15002106
 */
package quiz;

import java.util.List;

/**
 *
 * @author Andrew Ward W15002106
 */
public class Question {

    private String question;
    private int questionType;
    private String[] options;
    private String answer;
    private String answer2;
    private int questionDifficulty;
    private List<String[]> questionFile;

    /**
     * Constructor
     */
    public Question() {
    }

    /**
     * Constructor
     *
     * @param question
     * @param questionType
     * @param questionDifficulty
     * @param answer
     */
    public Question(String question, String questionType, String questionDifficulty,
            String answer) {
        //Set Question attributes
        this.setQuestion(question);
        this.setQuestionType(Integer.parseInt(questionType));
        this.setQuestionDifficulty(Integer.parseInt(questionDifficulty));
        this.setAnswer(answer);

    }

    /**
     *
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 
     * @param answer 
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 
     * @param questionType 
     */
    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    /**
     * 
     * @param options 
     */
    public void setOptions(String[] options) {
        this.options = options;
    }

    /**
     * 
     * @param questionDifficulty 
     */
    public void setQuestionDifficulty(int questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    /**
     *
     * @return String question
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     *
     * @return int question difficulty
     */
    public int getQuestionDifficulty() {
        return this.questionDifficulty;
    }

    /**
     *
     * @return String answer
     */
    public String getAnswer() {
        return this.answer;
    }

    /**
     *
     * @return int question type
     */
    public int getQuestionType() {
        return this.questionType;
    }

    /**
     *
     * @return List<String[]> question file
     */
    public List<String[]> getQuestionFile() {
        return this.questionFile;
    }

    /**
     * 
     * @return String[] options array
     */
    public String[] getOptions() {
        return this.options;
    }

    /**
     * 
     * @param answer2 
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * 
     * @return String answer2
     */
    public String getAnswer2() {
        return this.answer2;
    }
}
