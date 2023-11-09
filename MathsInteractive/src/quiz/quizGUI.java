/*
 *  Andrew Ward, ID: 15002106
 */
package quiz;

import administration.*;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Andrew Ward W15002106
 */
public class quizGUI extends javax.swing.JFrame {

    //Attributes
    private List<String[]> questionFile;
    private int numQuestionsAnswered;
    private String selected;//selected answer    
    private ArrayList<String> selected2 = new ArrayList<String>();//arraylist of
    //strings for storing multiple answers (checkboxes)
    private int score = 0;
    private int finalScore = 0;
    private int secondsPassed = 0;//used fpr timer and change of difficulty
    private int start;//start time
    private int finish;//end time
    private int questionTimeTaken = 0;//time taken per question
    private ArrayList<UserAnswers> userAnswers;//arraylist of user's answers
    private int userID;
    //List of answered questions    
    private ArrayList<Question> questionsAns = new ArrayList<Question>();
    //list of questions
    private ArrayList<Question> listOfQuestions = new ArrayList<Question>();
    private Question currentQuestion = new Question();//current question
    private int numCorrectAns;//number of correct answers
    private int quizID;

    /**
     * Creates new form quizGUI
     *
     * @param questionFile
     * @param userID
     * @param quizID
     */
    public quizGUI(List<String[]> questionFile, int userID, int quizID) {
        this.userAnswers = new ArrayList<UserAnswers>();
        this.userID = userID;
        this.quizID = quizID;
//        System.out.println("UserID: "+userID);
        this.numCorrectAns = 0;
        this.setQuestionFile(questionFile);
        process();

    }

    /**
     * method to start guiz
     */
    public void process() {
        numQuestionsAnswered = 0;
        toQuestion();
        //loads first question
        loadQuestion(numQuestionsAnswered);
        initComponents();
        gatherDetails();

        /**
         * code for the timer. Creates new timer and timertask. updates label
         * every second with time passed.
         */
        Timer quizTimer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                secondsPassed++;
                jLabel2.setText("Quiz Time: " + Integer.toString(secondsPassed)
                        + " Seconds.");
            }
        };
        quizTimer.scheduleAtFixedRate(task, 1000, 1000);

    }

    /**
     *
     * @param i
     */
    public void loadQuestion(int i) {
        //gets question from listOfQuestions
        Question question = listOfQuestions.get(i);
        //sets current question attributes to question
        currentQuestion.setQuestion(question.getQuestion());
//        System.out.println(this.getQuestion());
        currentQuestion.setQuestionType(question.getQuestionType());
        currentQuestion.setQuestionDifficulty(question.getQuestionDifficulty());
        currentQuestion.setAnswer(question.getAnswer());
        if (currentQuestion.getQuestionType() == 0) {
            String[] options = {questionFile.get(i)[4], questionFile.get(i)[5],
                questionFile.get(i)[6]};
            currentQuestion.setOptions(options);

        } else if (question.getQuestionType() == 1) {
            currentQuestion.setAnswer2(questionFile.get(i)[4]);
            String[] options = {questionFile.get(i)[5],
                questionFile.get(i)[6]};
            currentQuestion.setOptions(options);
        }
    }

    /**
     *
     * @param questionFile
     */
    public void setQuestionFile(List<String[]> questionFile) {
        this.questionFile = questionFile;
    }

    /**
     *
     * @return
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     *
     * @return
     */
    public List<String[]> getQuestionFile() {
        return this.questionFile;
    }

    /**
     *
     * @return
     */
    public String getSelectedAns() {
        return this.selected;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getSelectedAnswers() {
        return this.selected2;
    }

    /**
     *
     * @param selected
     */
    public void addSelected(String selected) {
        this.selected2.add(selected);
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score += score;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return this.score;
    }

    /**
     * clears all select boxes, removes text from jTextField1
     */
    public void clearAllBoxes() {
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);

        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);

        jTextField1.setText("");
    }

    /**
     * checks answer
     */
    public void checkAnswer() {
        //creates new userAnswers object
        UserAnswers userAnsw = new UserAnswers();
        int correctAns = 0;
        //check questiontype, checks answer is equal to answer
        switch (currentQuestion.getQuestionType()) {
            case 0:
                if (this.getSelectedAns().equals(currentQuestion.getAnswer())) {
                    JOptionPane.showMessageDialog(null, "Correct Answer",
                            "Correct Answer", JOptionPane.PLAIN_MESSAGE);
                    correctAns += 1;
                } else {
                    numCorrectAns = 0;
                    JOptionPane.showMessageDialog(null, "Incorrect Answer",
                            "Incorrect Answer", JOptionPane.PLAIN_MESSAGE);
                }
                userAnsw.setAnswer1(this.getSelectedAns());
                userAnswers.add(userAnsw);
                break;
            case 1:
                int count = 0;
                for (String ans : this.getSelectedAnswers()) {
                    if (currentQuestion.getAnswer().equals(ans)
                            || currentQuestion.getAnswer2().equals(ans)) {
                        count += 1;
                    }
                }
                if (count == 0) {
                    numCorrectAns = 0;
                }
                JOptionPane.showMessageDialog(null, "Number of answers correct "
                        + count,
                        "Number of answers correct " + count,
                        JOptionPane.PLAIN_MESSAGE);
                //user needs both correct to increase score
                if (count == 1) {
                    count = 0;
                } else if (count == 2) {
                    count = 1;
                }
                correctAns += count;
                count = 0;
                //sets answer 1 and 2 to userAnswers 1 and 2
                userAnsw.setAnswer1(this.getSelectedAnswers().get(0));
                userAnsw.setAnswer2(this.getSelectedAnswers().get(1));
                userAnswers.add(userAnsw);

                this.selected2.clear();

                break;
            case 2:
                if (currentQuestion.getAnswer().equalsIgnoreCase(this.jTextField1.getText())) {
                    JOptionPane.showMessageDialog(null, "Correct Answer",
                            "Correct Answer", JOptionPane.PLAIN_MESSAGE);
                    correctAns += 1;
                } else {
                    numCorrectAns = 0;
                    JOptionPane.showMessageDialog(null, "Incorrect Answer",
                            "Incorrect Answer", JOptionPane.PLAIN_MESSAGE);
                }
                userAnsw.setAnswer1(this.jTextField1.getText());
                userAnswers.add(userAnsw);
                break;
        }
        collectQuestions();

        finish = secondsPassed;
        //calculate seconds taken to answer;
        calculateTime();

        //add to score
        numCorrectAns += correctAns;
        setScore(correctAns);
        jLabel1.setText("Score: " + Integer.toString(this.getScore()));
        clearAllBoxes();
    }

    /**
     * calculates time taken
     */
    public void calculateTime() {
        questionTimeTaken = (finish - start);
    }

    /**
     *
     * @return boolean whether boxes have been selected
     */
    public boolean isSelected() {
        //check question type
        switch (currentQuestion.getQuestionType()) {//check if selected
            case 0:
                String selectedAns;
                if (jRadioButton1.isSelected()) {
                    selectedAns = jRadioButton1.getText();
                } else if (jRadioButton2.isSelected()) {
                    selectedAns = jRadioButton2.getText();
                } else if (jRadioButton3.isSelected()) {
                    selectedAns = jRadioButton3.getText();
                } else if (jRadioButton4.isSelected()) {
                    selectedAns = jRadioButton4.getText();

                } else {
                    JOptionPane.showMessageDialog(null, "Please select 1 answer",
                            "Please select 1 answer",
                            JOptionPane.PLAIN_MESSAGE);

                    return false;
                }
                this.selected = selectedAns;

                break;
            case 1:
                JCheckBox boxes[] = {jCheckBox1, jCheckBox2, jCheckBox3,
                    jCheckBox4};
                int numBoxSelected = 0;
                for (int i = 0; i < boxes.length; i++) {
                    if (boxes[i].isSelected()) {

                        if (numBoxSelected < 2) {
                            String selected = boxes[i].getText();
                            addSelected(selected);
                            numBoxSelected += 1;
                        } else {
                            //empty arraylist
                            this.selected2.clear();
                            //display dialog
                            JOptionPane.showMessageDialog(null, "Please select "
                                    + "up to 2 answers",
                                    "Please select up to 2 answers",
                                    JOptionPane.PLAIN_MESSAGE);
                            //return
                            return false;
                        }
                    }
                }
                if (numBoxSelected == 0) {
                    JOptionPane.showMessageDialog(null, "Please select "
                            + "up to 2 answers",
                            "Please select up to 2 answers",
                            JOptionPane.PLAIN_MESSAGE);

                }

                String[] selectedAnswers = new String[2];
                if (jCheckBox1.isSelected() && jCheckBox2.isSelected()
                        && jCheckBox3.isSelected() && jCheckBox4.isSelected()) {
                    //Throw dialog box message
                    JOptionPane.showMessageDialog(null, "Please select "
                            + "up to 2 answers",
                            "Please select up to 2 answers",
                            JOptionPane.PLAIN_MESSAGE);
                } else if (jCheckBox1.isSelected() && jCheckBox2.isSelected()
                        && jCheckBox3.isSelected() && jCheckBox4.isSelected()) {
                    //throw dialogbox message
                }
                if (jCheckBox1.isSelected()) {
                    selectedAnswers[0] = jCheckBox1.getText();
                }
                break;
            case 2:
                if (jTextField1.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Please input an answer","Please input an answer"
//                            JOptionPane.PLAIN_MESSAGE);
                }
                break;
        }

        return true;
    }

    /**
     * displays difficulty
     */
    public void displayDifficulty() {
        String diff = "";
        switch (currentQuestion.getQuestionDifficulty()) {
            case 1:
                diff = "Easy";
                break;
            case 2:
                diff = "Medium";
                break;
            case 3:
                diff = "Hard";
                break;
        }
        jLabel3.setText("Difficulty: " + diff);
    }

    public int[] shuffleArray(int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int r = rand.nextInt(arr.length);
            int k = arr[i];
            arr[i] = arr[r];
            arr[r] = k;
        }
        return arr;
    }

    /**
     * displays question
     */
    public void displayQuestionOptions() {
        displayDifficulty();

        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        arr = shuffleArray(arr);
//        for(int j:arr){
//            System.out.println(j);
//        }
        String[] randOptions = new String[4];
        switch (currentQuestion.getQuestionType()) {
            case 0:
                jTextField1.setVisible(false);

                jCheckBox1.setVisible(false);
                jCheckBox2.setVisible(false);
                jCheckBox3.setVisible(false);
                jCheckBox4.setVisible(false);

                randOptions[arr[0]] = currentQuestion.getAnswer();
                randOptions[arr[1]] = currentQuestion.getOptions()[0];
                randOptions[arr[2]] = currentQuestion.getOptions()[1];
                randOptions[arr[3]] = currentQuestion.getOptions()[2];

                jRadioButton1.setText(randOptions[0]);
                jRadioButton2.setText(randOptions[1]);
                jRadioButton3.setText(randOptions[2]);
                jRadioButton4.setText(randOptions[3]);

                jRadioButton1.setVisible(true);
                jRadioButton2.setVisible(true);
                jRadioButton3.setVisible(true);
                jRadioButton4.setVisible(true);

                break;
            //display multi choice
            case 1:
                jTextField1.setVisible(false);

                jRadioButton1.setVisible(false);
                jRadioButton2.setVisible(false);
                jRadioButton3.setVisible(false);
                jRadioButton4.setVisible(false);

                randOptions[arr[0]] = currentQuestion.getAnswer();
                randOptions[arr[1]] = currentQuestion.getAnswer2();
                randOptions[arr[2]] = currentQuestion.getOptions()[0];
                randOptions[arr[3]] = currentQuestion.getOptions()[1];

                jCheckBox1.setText(randOptions[0]);
                jCheckBox2.setText(randOptions[1]);
                jCheckBox3.setText(randOptions[2]);
                jCheckBox4.setText(randOptions[3]);

                jCheckBox1.setVisible(true);
                jCheckBox2.setVisible(true);
                jCheckBox3.setVisible(true);
                jCheckBox4.setVisible(true);
                break;
            //display fill in blank
            case 2:
                jTextField1.setText("");
                jTextField1.setColumns(10);
                jTextField1.setVisible(true);

                jRadioButton1.setVisible(false);
                jRadioButton2.setVisible(false);
                jRadioButton3.setVisible(false);
                jRadioButton4.setVisible(false);

                jCheckBox1.setVisible(false);
                jCheckBox2.setVisible(false);
                jCheckBox3.setVisible(false);
                jCheckBox4.setVisible(false);

                break;
            default:
                break;
        }
        start = secondsPassed;
    }

    /**
     * method to get different question based on difficulty
     *
     * @return difficulty
     */
    public int increaseDifficulty() {
        //get current difficulty
        int curDifficulty = currentQuestion.getQuestionDifficulty();
        //increment difficulty
        if (curDifficulty < 3) {
            curDifficulty += 1;
        }
        //search for question with new difficulty
        return curDifficulty;
    }

    /**
     *
     * @param q
     * @return if a question has been answered
     */
    public Boolean questionAnswered(Question q) {
        //loops through each question in questionsAns, checks if equuals Question q
        for (Question qListed : questionsAns) {
            if (q.getQuestion().equals(qListed.getQuestion())) {
                return true;
            }
        }
        return false;

    }

    /**
     *
     * @param currDifficulty currDifficulty will be increased before calling
     * method
     */
    public int searchQuestions(int currDifficulty) {
        int questionNum = 0;
        //check if empty
        if (questionsAns.isEmpty() == false) {
            //loops through list of questions
            for (Question q : listOfQuestions) {
                //check if question has been answered
                if (questionAnswered(q) == false) {
                    //check if question is correct difficulty
                    if (q.getQuestionDifficulty() == currDifficulty) {
                        //gets index of question in listofquestions
                        questionNum = listOfQuestions.indexOf(q);
                        break;
                    }
                }
            }
        }
        return questionNum;
    }

    /**
     * Converts array of strings to Question type, adds to ArrayList of
     * questions
     */
    public void toQuestion() {
        for (String[] q : this.questionFile) {
            Question question1 = new Question();
            question1.setQuestion(q[0]);
            question1.setQuestionType(Integer.parseInt(q[1]));
            question1.setQuestionDifficulty(Integer.parseInt(q[2]));
            question1.setAnswer(q[3]);
            if (question1.getQuestionType() == 0) {
                String[] optionsToAdd = {q[4], q[5], q[6]};
                question1.setOptions(optionsToAdd);
            } else if (question1.getQuestionType() == 1) {
                question1.setAnswer2(q[4]);
                String[] optionsToAdd = {q[5], q[6]};
                question1.setOptions(optionsToAdd);
            }
            listOfQuestions.add(question1);
        }
    }

    /**
     * Adds current question to the list of answered questions
     */
    public void collectQuestions() {
        Question questionAns = new Question();
//        questionAns=currentQuestion;
        questionAns.setQuestion(currentQuestion.getQuestion());
        questionAns.setAnswer(currentQuestion.getAnswer());
        questionAns.setQuestionDifficulty(
                currentQuestion.getQuestionDifficulty());
        questionAns.setQuestionType(currentQuestion.getQuestionType());
        if (questionAns.getQuestionType() == 1) {
            questionAns.setAnswer2(currentQuestion.getAnswer2());
        }
        questionsAns.add(questionAns);
    }

    /**
     * Get student details from database, display details in GUI header
     */
    public void gatherDetails() {
        String sqlUserDetails
                = "SELECT FName, LName FROM UserDetails WHERE user_ID = ?";
        try {
            Connection conn = Quiz.connectDB();
            PreparedStatement pstmt = conn.prepareStatement(sqlUserDetails);
            pstmt.setInt(1, userID);

            ResultSet userDetailsResults = pstmt.executeQuery();

            while (userDetailsResults.next()) {
                String FName = userDetailsResults.getString("FName");
                String LName = userDetailsResults.getString("LName");
                System.out.println(FName);
                System.out.println(LName);
                System.out.println(userID);

                lblUserDetails3.setText(FName + " " + LName + " (" + getUserID() + ")");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        lblLogo3 = new javax.swing.JLabel();
        lblUserDetails3 = new javax.swing.JLabel();
        lblUserLevel3 = new javax.swing.JLabel();
        btnLogout3 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        btnCreate3 = new javax.swing.JButton();
        questionPanel = new javax.swing.JPanel();
        questionArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quiz");
        setBackground(new java.awt.Color(153, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        setMaximumSize(new java.awt.Dimension(603, 590));
        setMinimumSize(new java.awt.Dimension(603, 573));
        setPreferredSize(new java.awt.Dimension(603, 615));
        setResizable(false);
        setSize(new java.awt.Dimension(603, 615));

        jPanel6.setBackground(new java.awt.Color(179, 218, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(603, 573));
        jPanel6.setMinimumSize(new java.awt.Dimension(603, 573));

        lblLogo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Additional/logo.png"))); // NOI18N
        lblLogo3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblUserDetails3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserDetails3.setForeground(new java.awt.Color(5, 24, 42));
        lblUserDetails3.setText("FName LName (UserID)");

        lblUserLevel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserLevel3.setForeground(new java.awt.Color(5, 24, 42));
        lblUserLevel3.setText("STUDENT");

        btnLogout3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout3.setForeground(new java.awt.Color(5, 24, 42));
        btnLogout3.setText("Logout");
        btnLogout3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogout3ActionPerformed(evt);
            }
        });

        btnCreate3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCreate3.setForeground(new java.awt.Color(5, 24, 42));
        btnCreate3.setText("Next");
        btnCreate3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate3ActionPerformed(evt);
            }
        });

        questionPanel.setBackground(new java.awt.Color(179, 218, 255));
        questionPanel.setMaximumSize(new java.awt.Dimension(556, 249));
        questionPanel.setMinimumSize(new java.awt.Dimension(556, 249));

        questionArea.setEditable(false);
        questionArea.setColumns(20);
        questionArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        questionArea.setRows(5);
        questionArea.setMaximumSize(new java.awt.Dimension(536, 96));
        questionArea.setMinimumSize(new java.awt.Dimension(536, 96));
        questionArea.setPreferredSize(new java.awt.Dimension(536, 96));
        questionArea.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                questionAreaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(179, 218, 255));

        jRadioButton4.setBackground(new java.awt.Color(179, 218, 255));
        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton4.setText("jRadioButton4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton1.setBackground(new java.awt.Color(179, 218, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jCheckBox3.setBackground(new java.awt.Color(179, 218, 255));
        jCheckBox3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox3.setText("jCheckBox3");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(179, 218, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton2.setText("jRadioButton2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(179, 218, 255));
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton3.setText("jRadioButton3");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jCheckBox4.setBackground(new java.awt.Color(179, 218, 255));
        jCheckBox4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox4.setText("jCheckBox4");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox2.setBackground(new java.awt.Color(179, 218, 255));
        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox2.setText("jCheckBox2");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jTextField1.setColumns(10);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(179, 218, 255));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBox4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox2))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3)
                        .addGap(0, 0, 0)
                        .addComponent(jCheckBox4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addGap(0, 0, 0)
                        .addComponent(jRadioButton4)))
                .addContainerGap())
        );

        javax.swing.GroupLayout questionPanelLayout = new javax.swing.GroupLayout(questionPanel);
        questionPanel.setLayout(questionPanelLayout);
        questionPanelLayout.setHorizontalGroup(
            questionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(questionArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, questionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );
        questionPanelLayout.setVerticalGroup(
            questionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(questionArea, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator10)
            .addComponent(jSeparator11)
            .addComponent(jSeparator12)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreate3)
                .addGap(73, 73, 73))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(questionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblLogo3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserDetails3)
                            .addComponent(lblUserLevel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout3)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(183, 183, 183)
                        .addComponent(jLabel1)
                        .addGap(54, 54, 54))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblUserDetails3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserLevel3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnLogout3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(36, 36, 36)
                .addComponent(questionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreate3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 533, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void questionAreaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_questionAreaAncestorAdded

        /**
         * Before answers and options to areas, should add to array and randomly
         * pull out answer and options and assign to radio buttons to prevent
         * answers being in the same spot.
         */
        questionArea.setText(currentQuestion.getQuestion());
        jLabel1.setText("Score: " + Integer.toString(this.getScore()));
        displayQuestionOptions();

    }//GEN-LAST:event_questionAreaAncestorAdded

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed

    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed

    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed

    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed

    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed

    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed

    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnLogout3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout3ActionPerformed
        for (Window w : java.awt.Window.getWindows()) {
            w.setVisible(false);
            w.dispose();
        }

        Login loginFrame = new Login();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }//GEN-LAST:event_btnLogout3ActionPerformed

    private void btnCreate3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate3ActionPerformed
        boolean canMoveOn = true;
        //check if any options are selected
        canMoveOn = isSelected();
        if (canMoveOn == true) {
            //check if correct answer
            checkAnswer();
            this.numQuestionsAnswered += 1;
            //increment questionNumber
            if (this.numQuestionsAnswered < 10) {
                int questionNumber = 0;
                //if num correct=2 or timeTaken<10sec
                if ((this.numCorrectAns > 3) || ((questionTimeTaken < 30)
                        && (this.numCorrectAns > 2))) {
                    questionNumber = searchQuestions(increaseDifficulty());
                    this.numCorrectAns = 0;
                } else {
                    questionNumber = searchQuestions(
                            currentQuestion.getQuestionDifficulty());
                }
                //Load question from array
                loadQuestion(questionNumber);
                questionArea.setText(currentQuestion.getQuestion());
                //display answers
                displayQuestionOptions();
                jProgressBar1.setValue(numQuestionsAnswered * 10);
                jProgressBar1.setStringPainted(true);
                String percentComplete = Integer.toString(numQuestionsAnswered * 10) + "%";
                jProgressBar1.setString(percentComplete);
            } else {

                QuizReviewGUI qReviewGUI = new QuizReviewGUI(questionsAns, userAnswers,
                        userID, quizID, score);
                qReviewGUI.setLocationRelativeTo(null);
                qReviewGUI.setVisible(true);

                this.dispose();
            }
        } else {
            clearAllBoxes();
        }
    }//GEN-LAST:event_btnCreate3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate3;
    private javax.swing.JButton btnLogout3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblLogo3;
    private javax.swing.JLabel lblUserDetails3;
    private javax.swing.JLabel lblUserLevel3;
    private javax.swing.JTextArea questionArea;
    private javax.swing.JPanel questionPanel;
    // End of variables declaration//GEN-END:variables
}
