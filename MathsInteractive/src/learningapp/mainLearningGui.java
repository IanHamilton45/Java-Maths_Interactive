//Author: Jonathan Oliver
package learningapp;

import administration.Login;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;



public class mainLearningGui extends javax.swing.JFrame {
    
    private int userID;

    /**
     * Creates new form contentCreation
     */
    public mainLearningGui(int userID) {
        this.userID = userID;
        initComponents();
        userID();
    }
    
     private Connection connectToDB(){
    String url = "jdbx:sqlite:Database.db";
    Connection conn = null;
    try{
        conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "failed to connect to DB", "Connection Failed", JOptionPane.PLAIN_MESSAGE);
    }
    return conn;
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
        lblInformation = new javax.swing.JLabel();
        createContentBtn = new javax.swing.JButton();
        editContentBtn = new javax.swing.JButton();

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
        lblUserLevel.setText("CONTENT CREATOR");

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(5, 24, 42));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblInformation.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblInformation.setForeground(new java.awt.Color(200, 0, 0));
        lblInformation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformation.setText(" ");
        lblInformation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        createContentBtn.setText("Create");
        createContentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createContentBtnActionPerformed(evt);
            }
        });

        editContentBtn.setText("Edit/Delete");
        editContentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editContentBtnActionPerformed(evt);
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUserDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUserLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(169, Short.MAX_VALUE)
                .addComponent(lblInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(227, 227, 227)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(editContentBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createContentBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(228, Short.MAX_VALUE)))
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
                        .addComponent(btnLogout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addComponent(lblInformation)
                .addGap(82, 82, 82)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(218, 218, 218)
                    .addComponent(createContentBtn)
                    .addGap(18, 18, 18)
                    .addComponent(editContentBtn)
                    .addContainerGap(282, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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

    private void createContentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createContentBtnActionPerformed
        contentCreation cbtn = new contentCreation(1);
        cbtn.setLocationRelativeTo(null);
        cbtn.setVisible(true);
        dispose();
    }//GEN-LAST:event_createContentBtnActionPerformed

    private void editContentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editContentBtnActionPerformed
        editContent eBtn = new editContent(1);
        eBtn.setLocationRelativeTo(null);
        eBtn.setVisible(true);
        dispose();
    }//GEN-LAST:event_editContentBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
            java.util.logging.Logger.getLogger(mainLearningGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainLearningGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainLearningGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainLearningGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new mainLearningGui(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton createContentBtn;
    private javax.swing.JButton editContentBtn;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblInformation;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUserDetails;
    private javax.swing.JLabel lblUserLevel;
    // End of variables declaration//GEN-END:variables
}
