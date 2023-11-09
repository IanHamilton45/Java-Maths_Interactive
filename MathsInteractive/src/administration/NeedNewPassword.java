//Author: Ian Hamilton - 15003706
package administration;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.security.crypto.bcrypt.*;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class NeedNewPassword extends javax.swing.JFrame {

    int userID;

    /**
     * Creates new form NeedNewPassword
     *
     * @param userID USer ID of the user that needs to set a new password
     */
    public NeedNewPassword(int userID) {
        this.userID = userID;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        inputConfirmPassword = new javax.swing.JPasswordField();
        lblExplanation = new javax.swing.JLabel();
        lblInformation = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        lblPassword = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        lblPassword1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Set New Password");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(179, 218, 255));

        inputConfirmPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputConfirmPassword.setForeground(new java.awt.Color(5, 24, 42));
        inputConfirmPassword.setMaximumSize(new java.awt.Dimension(6, 28));
        inputConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputConfirmPasswordKeyPressed(evt);
            }
        });

        lblExplanation.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblExplanation.setForeground(new java.awt.Color(5, 24, 42));
        lblExplanation.setText("You are required to set yourself a new password");

        lblInformation.setForeground(new java.awt.Color(200, 0, 0));
        lblInformation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformation.setText(" ");

        btnConfirm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(5, 24, 42));
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(5, 24, 42));
        lblPassword.setText("Password:");

        inputPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputPassword.setForeground(new java.awt.Color(5, 24, 42));
        inputPassword.setMaximumSize(new java.awt.Dimension(6, 28));
        inputPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputPasswordKeyPressed(evt);
            }
        });

        lblPassword1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPassword1.setForeground(new java.awt.Color(5, 24, 42));
        lblPassword1.setText("Confirm Password:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPassword1)
                            .addComponent(lblPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(lblExplanation)))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnConfirm)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExplanation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInformation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword1)
                    .addComponent(inputConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfirm)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Hashes the entered password
     *
     * @param textPassword
     * @return hashed password
     */
    private String hashPassword(char[] textPassword) {
        String stringPW = new String(textPassword).trim();
        return BCrypt.hashpw(stringPW, BCrypt.gensalt());
    }

    /**
     * Updates the database with the new hashed password
     */
    private void setPassword() {
        //-Hashes the entered password
        String password1Hash = hashPassword(inputPassword.getPassword());

        //--Check if the passwords match
        if (!Arrays.equals(inputPassword.getPassword(), inputConfirmPassword.getPassword())) {
            lblInformation.setText("Passwords do not match.");
            inputPassword.setText("");
            inputConfirmPassword.setText("");
            return;
        }

        //-SQL to update the password hash
        String updateSQL = "UPDATE LoginCredentials SET passwordHash = ?, NeedNewPassword = 0 "
                + "WHERE  user_ID = ?";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute update
            PreparedStatement updateStatement = conn.prepareStatement(updateSQL);
            updateStatement.setString(1, password1Hash);
            updateStatement.setInt(2, userID);

            updateStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Password Changed.", "Confirmation", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to Change Password", "Confirmation", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        //--Dispose of the current window
        this.setVisible(false);
        this.dispose();
    }

    /**
     * Action-event when the user clicks confirm
     *
     * @param evt
     */
    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        setPassword();
    }//GEN-LAST:event_btnConfirmActionPerformed

    /**
     * Action-event when the user hits enter on their keyboard instead of
     * clicking Confirm
     *
     * @param evt
     */
    private void inputPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPasswordKeyPressed
        //The user hit enter instead of clicking the button
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setPassword();
        }
    }//GEN-LAST:event_inputPasswordKeyPressed

    /**
     * Action-event when the user hits enter on their keyboard instead of
     * clicking Confirm
     *
     * @param evt
     */
    private void inputConfirmPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputConfirmPasswordKeyPressed
        //The user hit enter instead of clicking the button
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setPassword();
        }
    }//GEN-LAST:event_inputConfirmPasswordKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //--Set the SeaGlass look and feel
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JPasswordField inputConfirmPassword;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblExplanation;
    private javax.swing.JLabel lblInformation;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPassword1;
    // End of variables declaration//GEN-END:variables
}
