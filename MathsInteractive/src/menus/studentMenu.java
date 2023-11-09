//Author: Andrew Southam
package menus;

import administration.Login;
import forum.ForumMain;
import java.awt.Window;
import java.sql.*;
import javax.swing.UIManager;

/**
 *
 * @author Andrew
 */
public class studentMenu extends javax.swing.JFrame {

    private int UserID = 1;

    /**
     * Creates new form studentMenu
     */
    public studentMenu(int userID) {
        this.UserID = userID;
        initComponents();
        userDetails();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        btnLogout2 = new javax.swing.JButton();
        lblLogo2 = new javax.swing.JLabel();
        UserDetails = new javax.swing.JLabel();
        lblUserLevel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblInformation = new javax.swing.JLabel();
        learningPacks = new javax.swing.JButton();
        leaderboard = new javax.swing.JButton();
        forum = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(179, 218, 255));

        btnLogout2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout2.setForeground(new java.awt.Color(5, 24, 42));
        btnLogout2.setText("Logout");
        btnLogout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogout2ActionPerformed(evt);
            }
        });

        lblLogo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Additional/logo.png"))); // NOI18N
        lblLogo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        UserDetails.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        UserDetails.setForeground(new java.awt.Color(5, 24, 42));
        UserDetails.setText("FName LName (UserID)");

        lblUserLevel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserLevel2.setForeground(new java.awt.Color(5, 24, 42));
        lblUserLevel2.setText("STUDENT");

        lblInformation.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblInformation.setForeground(new java.awt.Color(200, 0, 0));
        lblInformation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformation.setText(" ");
        lblInformation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        learningPacks.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        learningPacks.setForeground(new java.awt.Color(5, 24, 42));
        learningPacks.setText("Learning Packs");
        learningPacks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                learningPacksActionPerformed(evt);
            }
        });

        leaderboard.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leaderboard.setForeground(new java.awt.Color(5, 24, 42));
        leaderboard.setText("Student Leaderboard");
        leaderboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaderboardActionPerformed(evt);
            }
        });

        forum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        forum.setForeground(new java.awt.Color(5, 24, 42));
        forum.setText("Forum");
        forum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblLogo2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UserDetails)
                            .addComponent(lblUserLevel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout2)
                        .addGap(23, 23, 23))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(learningPacks, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leaderboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(forum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(99, 99, 99))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addComponent(jSeparator3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(UserDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserLevel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnLogout2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(learningPacks)
                .addGap(18, 18, 18)
                .addComponent(leaderboard)
                .addGap(18, 18, 18)
                .addComponent(forum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInformation)
                .addGap(65, 65, 65)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void forumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forumActionPerformed
        //Call Forum
        ForumMain forumFrame = new ForumMain(UserID);
        forumFrame.setLocationRelativeTo(null);
        forumFrame.setVisible(true);
    }//GEN-LAST:event_forumActionPerformed

    private void leaderboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaderboardActionPerformed
        studentLeaderboard leaderboard = new studentLeaderboard(UserID);
        leaderboard.setLocationRelativeTo(null);
        leaderboard.setVisible(true);
    }//GEN-LAST:event_leaderboardActionPerformed

    private void learningPacksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_learningPacksActionPerformed
        studentLearningPacks learningPackFrame = new studentLearningPacks(UserID);
        learningPackFrame.setLocationRelativeTo(null);
        learningPackFrame.setVisible(true);

    }//GEN-LAST:event_learningPacksActionPerformed

    private void btnLogout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout2ActionPerformed
        for (Window w : java.awt.Window.getWindows()) {
            w.setVisible(false);
            w.dispose();
        }
        Login loginFrame = new Login();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }//GEN-LAST:event_btnLogout2ActionPerformed

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
                studentMenu studentFrame = new studentMenu(1);
                studentFrame.setLocationRelativeTo(null);
                studentFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UserDetails;
    private javax.swing.JButton btnLogout2;
    private javax.swing.JButton forum;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblInformation;
    private javax.swing.JLabel lblLogo2;
    private javax.swing.JLabel lblUserLevel2;
    private javax.swing.JButton leaderboard;
    private javax.swing.JButton learningPacks;
    // End of variables declaration//GEN-END:variables
}