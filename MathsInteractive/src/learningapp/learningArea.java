//Author: Jonathan Oliver
package learningapp;

import administration.Login;
import java.awt.Window;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import quiz.Quiz;
import javax.swing.JOptionPane;

public class learningArea extends javax.swing.JFrame {

    private List<String[]> learningData = new ArrayList<String[]>();
    private List<String[]> learningData1 = new ArrayList<String[]>();
    private List<String[]> learningData2 = new ArrayList<String[]>();
    private List<String[]> learningData3 = new ArrayList<String[]>();

    private int learnNumber = 0;
    private int progBar = 20;
    private int userID;
    private int packID;
    private int quizID;

    /**
     * constructor for learning area
     *
     * @param userID
     * @param packID
     */
    public learningArea(int userID, int packID) {
        initComponents();
        readLearningData();
        displayData();
        this.userID = userID;
        this.packID = packID;
        packID();
        userID();

    }

    private Connection connectToDB() {
        String url = "jdbx:sqlite:Database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "failed to connect to DB", "Connection Failed", JOptionPane.PLAIN_MESSAGE);
        }
        return conn;
    }

    private void packID() {

        String learningName = null;
        String packSql = "SELECT Name FROM LearningPackages WHERE pack_ID=?";

        try {
            Connection conn = dbConn.connectToDB();

            PreparedStatement pstmt = conn.prepareStatement(packSql);
            pstmt.setInt(1, packID);
            ResultSet packIDResults = pstmt.executeQuery();

            while (packIDResults.next()) {
                learningName = packIDResults.getString("Name");

            }

        } catch (SQLException e) {

        }

    }

    private void userID() {

        String userName = null;

        String userSql = "SELECT FName, LName FROM UserDetails WHERE user_ID = ?";

        try {
            Connection conn = dbConn.connectToDB();

            PreparedStatement pstmt = conn.prepareStatement(userSql);
            pstmt.setInt(1, userID);
            ResultSet userDetailsResults = pstmt.executeQuery();

            while (userDetailsResults.next()) {
                String Fname = userDetailsResults.getString("FName");
                String Lname = userDetailsResults.getString("LName");
                userName = (Fname + " " + Lname);
            }
            lblUserDetails.setText(userName + " (" + this.userID + ")");
        } catch (SQLException e) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
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
        prog1 = new javax.swing.JProgressBar();
        answerBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaAdd1 = new javax.swing.JTextArea();
        jCheckBox2 = new javax.swing.JCheckBox();
        exitBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        completionLbl = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        result = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        IDLbl = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        subLbl = new javax.swing.JLabel();
        packIDLbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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

        prog1.setStringPainted(true);

        answerBtn.setText("Check answer");
        answerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerBtnActionPerformed(evt);
            }
        });

        jTextAreaAdd1.setColumns(20);
        jTextAreaAdd1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextAreaAdd1.setLineWrap(true);
        jTextAreaAdd1.setRows(5);
        jTextAreaAdd1.setEditable(false);
        jScrollPane2.setViewportView(jTextAreaAdd1);
        jTextAreaAdd1.setText("");

        exitBtn.setText("Exit ");
        exitBtn.setToolTipText("");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        completionLbl.setText("Current Completion");

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("test");

        jLabel1.setText("Learning ID:");

        jLabel7.setText("Subject Area:");

        packIDLbl.setText("jLabel6");

        jButton1.setText("Take Quiz?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserDetails)
                    .addComponent(lblUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(completionLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prog1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(nextBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exitBtn))
                    .addComponent(jButton1))
                .addGap(15, 15, 15))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(answerBtn))))
                .addGap(38, 38, 38))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IDLbl)
                .addGap(39, 39, 39)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(subLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(packIDLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblUserDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserLevel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnLogout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(IDLbl)
                            .addComponent(jLabel7)
                            .addComponent(subLbl)
                            .addComponent(packIDLbl))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jCheckBox1))
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel3))
                                    .addComponent(jCheckBox2))
                                .addGap(34, 34, 34)
                                .addComponent(jLabel4))
                            .addComponent(jCheckBox3))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jCheckBox4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(answerBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(result)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nextBtn)
                            .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(completionLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(prog1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        //--Close all current windows
        for (Window w : java.awt.Window.getWindows()) {
            w.setVisible(false);
            w.dispose();
        }

        //--Open the Login window centre-screen
        Login loginFrame = new Login();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void answerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerBtnActionPerformed

        if (jCheckBox3.isSelected()) {
            if (jCheckBox4.isSelected()) {
                if (!jCheckBox2.isSelected()) {
                    if (!jCheckBox2.isSelected()) {
                        result.setText("Correct!");
                    }
                }
            }

        }
    }//GEN-LAST:event_answerBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        mainLearningGui mlg = new mainLearningGui(1);
        mlg.setVisible(true);
        dispose();
    }//GEN-LAST:event_exitBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        displayData();
        prog1.setValue(progBar);
        progBar += 20;

    }//GEN-LAST:event_nextBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed

        if (jCheckBox3.isSelected()) {
            if (jCheckBox4.isSelected()) {
                if (!jCheckBox2.isSelected()) {
                    if (!jCheckBox2.isSelected()) {
                        result.setText("Correct!");
                    }

                }
            }
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String sqlGetLinkedQuiz = "SELECT quiz_ID "
                    + "FROM LearningPackages "
                    + "WHERE pack_ID = ?";

            Connection conn = dbConn.connectToDB();

            PreparedStatement pstmt = conn.prepareStatement(sqlGetLinkedQuiz);
            pstmt.setInt(1, packID);
            ResultSet rs = pstmt.executeQuery();

            int quizID = 0;
            while (rs.next()) {
                quizID = rs.getInt("quiz_ID");
            }

            Quiz initQuiz = new Quiz(quizID, this.userID);
        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(learningArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold deftest1"collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(learningArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(learningArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(learningArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(learningArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new learningArea(1, 1).setVisible(true);
            }
        });
    }

    public void readLearningData() {

        if (packID == 1) {

            File file = new File("AdditionBasic.txt");
            try {
                Scanner scnr = new Scanner(file).useDelimiter("\n");
                while (scnr.hasNext()) {

                    String token = scnr.nextLine();
                    String[] addition = token.split(";");
                    learningData.add(addition);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(learningArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (packID == 2) {
            File file = new File("Subtraction.txt");
            try {
                Scanner scnr = new Scanner(file).useDelimiter("\n");
                while (scnr.hasNext()) {

                    String token = scnr.nextLine();
                    String[] subtraction = token.split(";");
                    learningData1.add(subtraction);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(learningArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (packID == 3) {
            File file = new File("Division.txt");
            try {
                Scanner scnr = new Scanner(file).useDelimiter("\n");
                while (scnr.hasNext()) {

                    String token = scnr.nextLine();
                    String[] division = token.split(";");
                    learningData2.add(division);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(learningArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (packID == 4) {
            File file = new File("Multiplication.txt");
            try {
                Scanner scnr = new Scanner(file).useDelimiter("\n");
                while (scnr.hasNext()) {

                    String token = scnr.nextLine();
                    String[] multiplication = token.split(";");
                    learningData3.add(multiplication);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(learningArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void displayData() {

        if (packID == 1) {

            IDLbl.setText(learningData.get(learnNumber)[0]);
            subLbl.setText(learningData.get(learnNumber)[1]);
            jTextAreaAdd1.setText(learningData.get(learnNumber)[2]);
            jLabel2.setText(learningData.get(learnNumber)[3]);
            jLabel3.setText(learningData.get(learnNumber)[4]);
            jLabel4.setText(learningData.get(learnNumber)[5]);
            jLabel5.setText(learningData.get(learnNumber)[6]);
            learnNumber++;
        }

        if (packID == 2) {

            IDLbl.setText(learningData1.get(learnNumber)[0]);
            subLbl.setText(learningData1.get(learnNumber)[1]);
            jTextAreaAdd1.setText(learningData1.get(learnNumber)[2]);
            jLabel2.setText(learningData1.get(learnNumber)[3]);
            jLabel3.setText(learningData1.get(learnNumber)[4]);
            jLabel4.setText(learningData1.get(learnNumber)[5]);
            jLabel5.setText(learningData1.get(learnNumber)[6]);
            learnNumber++;
        }

        if (packID == 3) {

            IDLbl.setText(learningData2.get(learnNumber)[0]);
            subLbl.setText(learningData2.get(learnNumber)[1]);
            jTextAreaAdd1.setText(learningData2.get(learnNumber)[2]);
            jLabel2.setText(learningData2.get(learnNumber)[3]);
            jLabel3.setText(learningData2.get(learnNumber)[4]);
            jLabel4.setText(learningData2.get(learnNumber)[5]);
            jLabel5.setText(learningData2.get(learnNumber)[6]);
            learnNumber++;
        }

        if (packID == 4) {

            IDLbl.setText(learningData3.get(learnNumber)[0]);
            subLbl.setText(learningData3.get(learnNumber)[1]);
            jTextAreaAdd1.setText(learningData3.get(learnNumber)[2]);
            jLabel2.setText(learningData3.get(learnNumber)[3]);
            jLabel3.setText(learningData3.get(learnNumber)[4]);
            jLabel4.setText(learningData3.get(learnNumber)[5]);
            jLabel5.setText(learningData3.get(learnNumber)[6]);
            learnNumber++;
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDLbl;
    private javax.swing.JButton answerBtn;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel completionLbl;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextAreaAdd1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUserDetails;
    private javax.swing.JLabel lblUserLevel;
    private javax.swing.JButton nextBtn;
    private javax.swing.JLabel packIDLbl;
    private javax.swing.JProgressBar prog1;
    private javax.swing.JLabel result;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel subLbl;
    // End of variables declaration//GEN-END:variables
}
