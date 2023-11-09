/*
 *  Andrew Ward, ID: 15002106
 */
package quiz;

import administration.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Andrew Ward W15002106
 */
public class QuizReviewGUI extends javax.swing.JFrame {

    //Private Attributes
    //User's answers
    private ArrayList<UserAnswers> usersAnswers = new ArrayList<UserAnswers>();
    private List<Question> listOfQuestions = new ArrayList<Question>();
    private int userID;
    private int quizID;
    private int score;

    /**
     * Creates new form QuizReviewGUI
     *
     * @param questions Questions that the user has answered
     * @param userAnswers
     * @param userID
     * @param quizID
     * @param score
     */
    public QuizReviewGUI(ArrayList<Question> questions,
            ArrayList<UserAnswers> userAnswers, int userID, int quizID,
            int score) {
        this.userID = userID;
        this.score = score;
        this.quizID = quizID;
//        this.tableData = new Object[10][5];
        setUserAnswers(userAnswers);
        listOfQuestions = questions;

//        displayData();
//        jTable1.repaint();
        initComponents();
    }

    /**
     *
     * @param userAnswers
     */
    public void setUserAnswers(ArrayList<UserAnswers> userAnswers) {
        this.usersAnswers = userAnswers;
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

                lblUserDetails.setText(FName + " " + LName + " (" + getUserID() + ")");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     * get relevant data from each question and the user's answer and display
     */
    public void displayData() {
        gatherDetails();
        //number in userAnswers
        int i = 0;
        //grid layout for each panel
        GridLayout grid = new GridLayout(1, 3);
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        JPanel jPanelHeadings = new JPanel();
        jPanelHeadings.setLayout(new FlowLayout());
        JLabel question = new JLabel();
        JLabel userAns = new JLabel();
        JLabel answer = new JLabel();
        question.setText("Question");
        userAns.setText("Your Answer");
        answer.setText("Answer");
        jLabel5.setText(Integer.toString(score));

        //For each question answered, list question, user's answer, answer
        for (Question q : listOfQuestions) {
            JPanel jPanelInfo = new JPanel();
            jPanelInfo.setLayout(grid);
            JPanel jPanelQuestion = new JPanel();

            JPanel jPanelUserAns = new JPanel();
            JPanel jPanelAns = new JPanel();

            JLabel label1 = new JLabel();
//            label1.setSize(jPanelAns.getWidth()/3,jPanelAns.getHeight());
            label1.setText(q.getQuestion());
            JLabel label2 = new JLabel();
            JLabel label3 = new JLabel();
            //check question type
            if (q.getQuestionType() == 0 || q.getQuestionType() == 2) {

                label2.setText(usersAnswers.get(i).getAnswer1());
                label3.setText(q.getAnswer());
                //need question, user answer, correct?, answer, possibly explanation
            } else if (q.getQuestionType() == 1) {
                label2.setText(usersAnswers.get(i).answersToString());
                label3.setText(q.getAnswer() + " " + q.getAnswer2());
            }
//            label2.setSize(jPanelAns.getWidth()/3,jPanelAns.getHeight());
//            label3.setSize(jPanelAns.getWidth()/3,jPanelAns.getHeight());
            //add labels
            jPanelQuestion.add(label1);
            jPanelUserAns.add(label2);
            jPanelAns.add(label3);
            //add array to 2darray/list used to fill table with data

            jPanelInfo.add(jPanelQuestion);
            jPanelInfo.add(jPanelUserAns);
            jPanelInfo.add(jPanelAns);
            jPanel1.add(jPanelInfo);
            //increment i
            i++;
        }//end
        jScrollPane1.getViewport().add(jPanel1);
        jScrollPane1.validate();
        jScrollPane1.setVisible(true);
        addResults();
        adjustQuizBufferOrder();

    }

    public void adjustQuizBufferOrder() {
        /**
         * connect to db SQL=SELECT Buffer2,Buffer3 FROM QuizBuffer WHERE
         * student_ID=userID; int newBuffer1=Buffer2 int newBuffer2=buffer3
         * SQL="UPDATE QuizBuffer SET Buffer1=newBuffer1, Buffer2=newBuffer2,
         * Buffer3=null WHERE student_ID=userID;
         */
        //connect to db        

        try {
            Integer newBuffer1 = null;
            Integer newBuffer2 = null;
            Connection conn = Quiz.connectDB();
            //SQL statement to get buffer 2 and 3 for student_id
            String sqlGetBuffer = "SELECT buffer2, buffer3 FROM QuizBuffer WHERE student_id = " + userID;

            //prepare statement
            PreparedStatement pstmt = conn.prepareStatement(sqlGetBuffer);

            //execute query
            ResultSet getBufferResult = pstmt.executeQuery();
            //assign buffer2/3 to vars
            while (getBufferResult.next()) {
                newBuffer1 = getBufferResult.getInt("buffer2");
                newBuffer2 = getBufferResult.getInt("buffer3");
            }

//            conn.close();
            //SQL Statement to update buffer with new values
            String sqlUpdateBuffer = "UPDATE QuizBuffer SET buffer1= ?, buffer2= ? , buffer3=NULL WHERE student_id = " + userID;
            //prepare statement
            PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateBuffer);
            //set values
            if (newBuffer1 == null) {
                pstmtUpdate.setNull(1, Types.NULL);
                pstmtUpdate.setNull(2, Types.NULL);
            } else if (newBuffer2 == null) {
                pstmtUpdate.setInt(1, newBuffer1);
                pstmtUpdate.setNull(2, Types.NULL);
            } else {
                pstmtUpdate.setInt(1, newBuffer1);
                pstmtUpdate.setInt(2, newBuffer2);
            }
            //execute
            pstmtUpdate.execute();
            //close connection
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

//        Connection conn2=Quiz.connectDB();
    }

    /**
     * Adds result to database for student
     */
    public void addResults() {

        String date = new SimpleDateFormat("yyyy-M-d").format(new Date());
//        System.out.println(date);
        String sqlStmt = "INSERT INTO Results (quiz_id, student_ID, result, "
                + "completed_on) VALUES (?,?,?,?)";

        try {
            Connection conn = Quiz.connectDB();
            PreparedStatement pstmt = conn.prepareStatement(sqlStmt);
            pstmt.setInt(1, quizID);
            pstmt.setInt(2, userID);
            pstmt.setInt(3, score);
            pstmt.setString(4, date);
//            System.out.println(sqlStmt);
            pstmt.execute();
//            adjustQuizBufferOrder(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        /**
         * Connect to db
         *
         * SQL=INSERT INTO Results (QuizID, studentID, result, completed_on)
         * VALUES QuizID, userID, score, date
         */
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblUserDetails = new javax.swing.JLabel();
        lblUserLevel = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnCreate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(179, 218, 255));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Additional/logo.png"))); // NOI18N
        lblLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblUserDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserDetails.setForeground(new java.awt.Color(5, 24, 42));
        lblUserDetails.setText("FName LName (UserID)");

        lblUserLevel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserLevel.setForeground(new java.awt.Color(5, 24, 42));
        lblUserLevel.setText("STUDENT");

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(5, 24, 42));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(5, 24, 42));
        btnCreate.setText("Next");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jScrollPane1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Question");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Your Answer");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Answer");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Your Score:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserDetails)
                    .addComponent(lblUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(36, 36, 36)
                .addComponent(btnLogout)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreate)
                .addGap(73, 73, 73))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(265, 265, 265)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(44, 44, 44))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblUserDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserLevel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addComponent(btnLogout))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        for (Window w : java.awt.Window.getWindows()) {
            w.setVisible(false);
            w.dispose();
        }

        Login loginFrame = new Login();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void jScrollPane1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jScrollPane1AncestorAdded

        displayData();
    }//GEN-LAST:event_jScrollPane1AncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUserDetails;
    private javax.swing.JLabel lblUserLevel;
    // End of variables declaration//GEN-END:variables
}
