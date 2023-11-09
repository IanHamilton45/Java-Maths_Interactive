//Author: Ian Hamilton - 15003706
package administration;

import menus.*;
import learningapp.*;
import quiz.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.springframework.security.crypto.bcrypt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class Login extends javax.swing.JFrame {

    int userID, needNewPassword;
    String passwordHash, userLevel;
    Date suspendedUntil;

    /**
     * Constructor
     */
    public Login() {
        initComponents(); //Create and display components
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblInformation = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        btnForgotPassword = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(179, 218, 255));
        setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        setForeground(new java.awt.Color(179, 218, 255));
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(179, 218, 255));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Additional/LogoLogin.png"))); // NOI18N
        lblLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblInformation.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblInformation.setForeground(new java.awt.Color(200, 0, 0));
        lblInformation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformation.setText(" ");

        lblUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(5, 24, 42));
        lblUsername.setText("Username:");

        inputUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputUsername.setForeground(new java.awt.Color(5, 24, 42));
        inputUsername.setMaximumSize(new java.awt.Dimension(6, 28));
        inputUsername.setNextFocusableComponent(inputPassword);
        inputUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputUsernameKeyPressed(evt);
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

        btnForgotPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnForgotPassword.setForeground(new java.awt.Color(5, 24, 42));
        btnForgotPassword.setText("Forgot Password");
        btnForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnForgotPasswordMouseClicked(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(5, 24, 42));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(lblUsername))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnForgotPassword)
                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInformation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnForgotPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogin)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Checks the password entered matches the hashed password in the database
     *
     * @param textPassword
     * @param hashedPassword
     * @return Boolean: true if the passwords match
     */
    private boolean checkPassword(char[] textPassword, String hashedPassword) {
        String plainPassword = new String(textPassword).trim();
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    /**
     * Check if the user is suspended
     *
     * @param suspendedUntil
     * @return Boolean: true if the user is suspended
     */
    private boolean checkUserSuspension(Date suspendedUntil) {
        boolean isUserSupended = true;
        Date today = new Date();

        if (suspendedUntil.before(today)) { //Suspension date has passed
            isUserSupended = false;
        }

        return isUserSupended;
    }

    /**
     * Clears the input text boxes
     */
    private void clearFields() {
        inputUsername.setText("");
        inputPassword.setText("");
    }

    /**
     * Check to username and password against database and log the relevant user
     * into the system
     */
    private void tryLogin() {
        //-Trim the user input
        String StringInputUsername = inputUsername.getText().trim();
        int enteredUsername;

        //--Make sure they have entered a username
        if (StringInputUsername.equals("")) {
            lblInformation.setText("Please enter a username");
            return;
        }

        /*TODO: DEMO FUNCTIONALITY*/
        if (StringInputUsername.equals("quiz")) {
            try {
                Quiz quizFrame = new Quiz(1, 1);
                this.dispose();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //--Make sure they have entered a password
        if (this.inputPassword.getPassword().length == 0) {
            lblInformation.setText("Please enter a password");
            return;
        }

        //--Make sure username is an integer
        try {
            enteredUsername = Integer.parseInt(StringInputUsername);
            lblInformation.setText(" ");
        } catch (NumberFormatException e) {
            lblInformation.setText("Username Wrong Format");
            clearFields();
            return;
        }

        //-SQL to get user login information
        String query = ("SELECT user_ID, passwordHash, NeedNewPassword, SuspendedUntil, UserLevel FROM LoginCredentials WHERE user_ID = " + enteredUsername);
        int numberOfResults = 0;

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute query
            Statement statement = conn.createStatement();
            ResultSet selectResults = statement.executeQuery(query);

            while (selectResults.next()) {
                this.userID = selectResults.getInt("user_ID");
                this.passwordHash = selectResults.getString("passwordHash");
                this.needNewPassword = selectResults.getInt("NeedNewPassword");
                String suspendedUntilString = selectResults.getString("SuspendedUntil");
                //-Parse the string date into date format yyyy-MM-dd
                this.suspendedUntil = new SimpleDateFormat("yyyy-MM-dd").parse(suspendedUntilString);
                this.userLevel = selectResults.getString("UserLevel");

                numberOfResults = numberOfResults + 1;
            }
        } catch (SQLException e) {
            lblInformation.setText("Database Failed to Connect");
            System.out.println(e.getMessage());
            return;
        } catch (ParseException e) {
            lblInformation.setText("Database Failed to Connect");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //--No username found in the database
        if (numberOfResults == 0) {
            lblInformation.setText("Incorrect Username or Password.");
            clearFields();
            return;
        }

        //--Check if the entered password is correct
        boolean isCorrectPassword = checkPassword(inputPassword.getPassword(), passwordHash);
        if (isCorrectPassword == false) {
            lblInformation.setText("Incorrect Username or Password.");
            clearFields();
            return;
        }

        //--Entered username and password is correct
        //--Check if the user account is suspended
        boolean userSuspended = checkUserSuspension(suspendedUntil);
        if (userSuspended == true) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String StringSuspendedUntil = formatter.format(suspendedUntil);
            String msg = ("Account suspended until: " + StringSuspendedUntil);

            JOptionPane.showMessageDialog(null, msg, "Account Suspension", JOptionPane.PLAIN_MESSAGE);
            lblInformation.setText(" ");
            return;
        }

        //--Check if the user needs a new password
        if (needNewPassword == 1) {
            inputPassword.setText("");
            NeedNewPassword needNewPasswordFrame = new NeedNewPassword(userID);
            needNewPasswordFrame.setLocationRelativeTo(null);
            needNewPasswordFrame.setVisible(true);
            return;
        }

        // --The user can login. Direct user depending on their user level
        switch (userLevel) {
            case "STUDENT":
                //STUDENT               
                studentMenu studentMenuFrame = new studentMenu(userID);
                this.setVisible(false); //Set this window to invisible
                studentMenuFrame.setLocationRelativeTo(null);
                studentMenuFrame.setVisible(true);
                dispose(); //dispose of this window 

                break;
            case "TEACHER":
                //TEACHER                
                teacherMenu teacherMenuFrame = new teacherMenu(userID);
                this.setVisible(false); //Set this window to invisible
                teacherMenuFrame.setLocationRelativeTo(null);
                teacherMenuFrame.setVisible(true);
                dispose(); //dispose of this window 

                break;
            case "CREATOR":
                //CONTENT CREATOR
                mainLearningGui contentCreatorFrame = new mainLearningGui(userID);
                this.setVisible(false); //Set this window to invisible
                contentCreatorFrame.setLocationRelativeTo(null);
                contentCreatorFrame.setVisible(true);
                dispose(); //dispose of this window 

                break;
            case "ADMIN":
                //ADMIN
                AdminMenu AdminMenuFrame = new AdminMenu(userID);
                this.setVisible(false); //Set this window to invisible
                AdminMenuFrame.setLocationRelativeTo(null);
                AdminMenuFrame.setVisible(true);
                dispose(); //dispose of this window

                break;
        }
    }

    /**
     * Action-event when the user clicks login
     *
     * @param evt
     */
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        tryLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * Action-event when the user hits enter instead of clicking login
     *
     * @param evt
     */
    private void inputPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPasswordKeyPressed
        //--The user has hit enter instead of clicking the button
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tryLogin();
        }
    }//GEN-LAST:event_inputPasswordKeyPressed

    /**
     * Action-event when the user hits enter instead of clicking login
     *
     * @param evt
     */
    private void inputUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputUsernameKeyPressed
        //--The user has hit enter instead of clicking the button
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tryLogin();
        }
    }//GEN-LAST:event_inputUsernameKeyPressed

    /**
     * Action-event when the user clicks the Forgot Password label
     *
     * @param evt
     */
    private void btnForgotPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnForgotPasswordMouseClicked
        //--Create a forgot password window
        ForgotPassword forgotPasswordFrame = new ForgotPassword();
        forgotPasswordFrame.setLocationRelativeTo(null);
        forgotPasswordFrame.setVisible(true);
    }//GEN-LAST:event_btnForgotPasswordMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //--Set the SeaGlass look and feel if it is installed
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Login loginFrame = new Login();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnForgotPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblInformation;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    // End of variables declaration//GEN-END:variables
}
