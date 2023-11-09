//Author: Andrew Southam
package menus;

import administration.*;
import learningapp.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.*;
import javax.swing.UIManager;
import quiz.Quiz;

/**
 *
 * @author Andrew
 */
public class studentLearningPacks extends javax.swing.JFrame {

    private int UserID = 0;

    /**
     * Creates new form studentLearningPacks
     */
    public studentLearningPacks(int userID) {
        this.UserID = userID;
        initComponents();
        userDetails();
        getCurrentPack();
        nextPack();
    }

    private void userDetails() {

        String name = null;

        String sql = "SELECT FName, LName FROM UserDetails WHERE user_ID = ?";

        try {
            Connection conn = databaseconn.connectToDB();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, UserID);
            ResultSet userDetailsResults = pstmt.executeQuery();

            while (userDetailsResults.next()) {
                String Fname = userDetailsResults.getString("FName");
                String Lname = userDetailsResults.getString("LName");
                name = (Fname + " " + Lname);
            }
            UserDetails.setText(name + " (" + this.UserID + ")");
        } catch (SQLException e) {

        }
    }

    private void getCurrentPack() {
        int packID = 0;

        String sql = "SELECT Buffer1 FROM QuizBuffer WHERE student_ID = ?";

        try {
            Connection conn = databaseconn.connectToDB();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, UserID);
            ResultSet packDetailsResults = pstmt.executeQuery();

            while (packDetailsResults.next()) {
                packID = packDetailsResults.getInt("Buffer1");
            }

        } catch (SQLException e) {

        }

        if (packID == 0) {
            pack.setText("You curretly have no Learning Packs Assigned");
            jButton1.setVisible(false);
            current.setVisible(false);

        } else {
            String packTitel = null;

            sql = "SELECT name FROM LearningPackages WHERE pack_ID = ?";
            try {
                Connection conn = databaseconn.connectToDB();

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, packID);
                ResultSet packDetailsResults = pstmt.executeQuery();

                while (packDetailsResults.next()) {
                    packTitel = packDetailsResults.getString("name");
                }
                pack.setText(packTitel);
            } catch (SQLException e) {

            }
            sql = "SELECT PackProgress FROM StudentDetails WHERE student_ID = ?";
            int packProgress = 0;
            try {
                Connection conn = databaseconn.connectToDB();

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, UserID);
                ResultSet packDetailsResults = pstmt.executeQuery();

                while (packDetailsResults.next()) {
                    packProgress = packDetailsResults.getInt("PackProgress");
                }
                packProgress = packProgress * 10;
                current.setText("You are currently " + packProgress + "% Through:");
            } catch (SQLException e) {

            }

        }

    }

    private void nextPack() {
        int packID = 0;
        String sql = "SELECT Buffer2 FROM QuizBuffer WHERE student_ID = ?";

        try {
            Connection conn = databaseconn.connectToDB();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, UserID);
            ResultSet packDetailsResults = pstmt.executeQuery();

            while (packDetailsResults.next()) {
                packID = packDetailsResults.getInt("Buffer2");
            }

        } catch (SQLException e) {

        }
        if (packID == 0) {
            lpack.setText("You have no more Learning packs set!");
            pack1.setText(" ");
            pack2.setText(" ");

        } else {
            String packTitel = null;

            sql = "SELECT name FROM LearningPackages WHERE pack_ID = ?";
            try {
                Connection conn = databaseconn.connectToDB();

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, packID);
                ResultSet packDetailsResults = pstmt.executeQuery();

                while (packDetailsResults.next()) {
                    packTitel = packDetailsResults.getString("name");
                }
                pack1.setText("First: " + packTitel);
            } catch (SQLException e) {

            }

            packID = 0;
            sql = "SELECT Buffer3 FROM QuizBuffer WHERE student_ID = ?";

            try {
                Connection conn = databaseconn.connectToDB();

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, UserID);
                ResultSet packDetailsResults = pstmt.executeQuery();

                while (packDetailsResults.next()) {
                    packID = packDetailsResults.getInt("Buffer3");
                }

            } catch (SQLException e) {

            }
            if (packID == 0) {
                pack1.setText("You only have:  " + packTitel + ", Left!");
                pack2.setText(" ");

            } else {
                sql = "SELECT name FROM LearningPackages WHERE pack_ID = ?";
                try {
                    Connection conn = databaseconn.connectToDB();

                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, packID);
                    ResultSet packDetailsResults = pstmt.executeQuery();

                    while (packDetailsResults.next()) {
                        packTitel = packDetailsResults.getString("name");
                    }
                    pack2.setText("Second: " + packTitel);
                } catch (SQLException e) {

                }
            }

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

        jPanel4 = new javax.swing.JPanel();
        lblLogo2 = new javax.swing.JLabel();
        UserDetails = new javax.swing.JLabel();
        lblUserLevel2 = new javax.swing.JLabel();
        btnLogout2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        back = new javax.swing.JButton();
        current = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pack = new javax.swing.JLabel();
        lpack = new javax.swing.JLabel();
        pack1 = new javax.swing.JLabel();
        pack2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(179, 218, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(608, 553));
        jPanel4.setMinimumSize(new java.awt.Dimension(608, 553));

        lblLogo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Additional/logo.png"))); // NOI18N
        lblLogo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        UserDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        UserDetails.setForeground(new java.awt.Color(5, 24, 42));
        UserDetails.setText("FName LName (UserID)");

        lblUserLevel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserLevel2.setForeground(new java.awt.Color(5, 24, 42));
        lblUserLevel2.setText("STUDENT");

        btnLogout2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout2.setForeground(new java.awt.Color(5, 24, 42));
        btnLogout2.setText("Logout");
        btnLogout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogout2ActionPerformed(evt);
            }
        });

        back.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        back.setForeground(new java.awt.Color(5, 24, 42));
        back.setText("Back");
        back.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                backComponentHidden(evt);
            }
        });
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        current.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        current.setText("Your Current Pack is:");
        current.setPreferredSize(new java.awt.Dimension(372, 22));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Continue");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pack.setText("Pack");
        pack.setMaximumSize(new java.awt.Dimension(281, 22));
        pack.setMinimumSize(new java.awt.Dimension(281, 22));
        pack.setPreferredSize(new java.awt.Dimension(281, 22));

        lpack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lpack.setText("Your Next Learning Packs will be:");
        lpack.setPreferredSize(new java.awt.Dimension(349, 22));

        pack1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pack1.setText("PACK 1");
        pack1.setPreferredSize(new java.awt.Dimension(190, 22));

        pack2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pack2.setText("PACK 2");
        pack2.setPreferredSize(new java.awt.Dimension(190, 22));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(current, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblLogo2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(UserDetails)
                                    .addComponent(lblUserLevel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLogout2))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(back)))
                        .addGap(30, 30, 30))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pack2, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pack1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lpack, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 112, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnLogout2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(UserDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserLevel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lpack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pack1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188)
                .addComponent(current, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout2ActionPerformed
        for (Window w : java.awt.Window.getWindows()) {
            w.setVisible(false);
            w.dispose();
        }

        Login loginFrame = new Login();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }//GEN-LAST:event_btnLogout2ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_backActionPerformed

    private void backComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_backComponentHidden

    }//GEN-LAST:event_backComponentHidden

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int packID = 0;
        int packProgress = 0;

        String sql = "SELECT Buffer1 FROM QuizBuffer WHERE student_ID = ?";

        try {
            Connection conn = databaseconn.connectToDB();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, UserID);
            ResultSet packDetailsResults = pstmt.executeQuery();

            while (packDetailsResults.next()) {
                packID = packDetailsResults.getInt("Buffer1");
            }

        } catch (SQLException e) {

        }

        sql = "SELECT PackProgress FROM StudentDetails WHERE student_ID = ?";

        try {
            Connection conn = databaseconn.connectToDB();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, UserID);
            ResultSet packProgressResults = pstmt.executeQuery();

            while (packProgressResults.next()) {
                packProgress = packProgressResults.getInt("PackProgress");
            }

        } catch (SQLException e) {

        }
        if (packProgress == 10) {
            try {
                String sqlGetLinkedQuiz = "SELECT quiz_ID "
                        + "FROM LearningPackages "
                        + "WHERE pack_ID = ?";
                
                Connection conn = databaseconn.connectToDB();

                PreparedStatement pstmt = conn.prepareStatement(sqlGetLinkedQuiz);
                pstmt.setInt(1, packID);
                ResultSet rs = pstmt.executeQuery();

                int quizID = 0;
                while (rs.next()) {
                    quizID = rs.getInt("quiz_ID");
                }

                Quiz initQuiz = new Quiz(quizID, this.UserID);
            } catch (FileNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
            }

        } else {
            learningArea learningPackFrame = new learningArea(this.UserID, packID);
            learningPackFrame.setLocationRelativeTo(null);
            learningPackFrame.setVisible(true);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                studentLearningPacks learningPackFrame = new studentLearningPacks(1);
                learningPackFrame.setLocationRelativeTo(null);
                learningPackFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UserDetails;
    private javax.swing.JButton back;
    private javax.swing.JButton btnLogout2;
    private javax.swing.JLabel current;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblLogo2;
    private javax.swing.JLabel lblUserLevel2;
    private javax.swing.JLabel lpack;
    private javax.swing.JLabel pack;
    private javax.swing.JLabel pack1;
    private javax.swing.JLabel pack2;
    // End of variables declaration//GEN-END:variables
}
