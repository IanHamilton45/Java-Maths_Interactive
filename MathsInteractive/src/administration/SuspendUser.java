//Author: Ian Hamilton - 15003706
package administration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ian Hamilton 15003706
 */
public class SuspendUser extends javax.swing.JFrame {

    private final int userID;
    private Date suspensionDate;

    public SuspendUser(int userID) {
        initComponents();
        this.userID = userID;
        this.suspensionDate = getSuspensionDate();
        inputSuspensionDate.setDate(suspensionDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnSuspend = new javax.swing.JButton();
        lblSuspended = new javax.swing.JLabel();
        inputSuspensionDate = new org.jdesktop.swingx.JXDatePicker();
        chkSuspendIndef = new javax.swing.JCheckBox();
        chkRemove = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suspend User");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel3.setBackground(new java.awt.Color(179, 218, 255));

        btnSuspend.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSuspend.setForeground(new java.awt.Color(5, 24, 42));
        btnSuspend.setText("Suspend");
        btnSuspend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuspendActionPerformed(evt);
            }
        });

        lblSuspended.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSuspended.setForeground(new java.awt.Color(5, 24, 42));
        lblSuspended.setText("SUSPENDED UNTIL: ");

        inputSuspensionDate.setForeground(new java.awt.Color(5, 24, 42));
        inputSuspensionDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputSuspensionDate.setFormats("dd-MM-yyyy");
        inputSuspensionDate.setPreferredSize(new java.awt.Dimension(170, 30));
        inputSuspensionDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSuspensionDateActionPerformed(evt);
            }
        });

        chkSuspendIndef.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chkSuspendIndef.setForeground(new java.awt.Color(5, 24, 42));
        chkSuspendIndef.setText("Indefinitely");
        chkSuspendIndef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSuspendIndefActionPerformed(evt);
            }
        });

        chkRemove.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chkRemove.setForeground(new java.awt.Color(5, 24, 42));
        chkRemove.setText("Remove");
        chkRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lblSuspended)
                .addGap(18, 18, 18)
                .addComponent(inputSuspensionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(chkSuspendIndef)
                .addGap(18, 18, 18)
                .addComponent(chkRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSuspend)
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(inputSuspensionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSuspended))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSuspendIndef)
                    .addComponent(btnSuspend)
                    .addComponent(chkRemove))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Update the database with the new suspension date
     */
    private void suspendUser() {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Format the date into yyyy-MM-dd (Database format)
        String suspendDate = dFormat.format(inputSuspensionDate.getDate());

        //-SQL to update the suspension date of the user
        String sqlUpdate = "UPDATE LoginCredentials SET SuspendedUntil = ? "
                + "WHERE user_ID = ?";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute update
            PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate);
            updateStmt.setString(1, suspendDate);
            updateStmt.setInt(2, userID);
            
            updateStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "User Suspension Updated", "UPDATE", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User Suspension Failed", "UPDATE", JOptionPane.PLAIN_MESSAGE);
        }
        
        //-Dispose of the current window
        this.dispose();
    }

    /**
     * Get the users current suspension date from the database
     * @return Date of current suspension
     */
    private Date getSuspensionDate() {
        Date date = null;
        //-SQL to get the current suspension date
        String sqlSelect = "SELECT SuspendedUntil FROM LoginCredentials WHERE user_ID = ?";
        
        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute query
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String suspendedUntilString = rs.getString("SuspendedUntil");
                date = new SimpleDateFormat("yyyy-MM-dd").parse(suspendedUntilString);
            }
        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Parse Error: " + e.getMessage());
        }

        return date;
    }

    /**
     * Action-event when the user clicks suspend option
     * @param evt 
     */
    private void btnSuspendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuspendActionPerformed
        suspendUser();
    }//GEN-LAST:event_btnSuspendActionPerformed

    /**
     * Action-event when the user clicks the Suspend Indefinitely check-box
     * @param evt 
     */
    private void chkSuspendIndefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSuspendIndefActionPerformed
        //--If checked, remove check from chkRemove
        if (chkSuspendIndef.isSelected()) {
            chkRemove.setSelected(false);

            //--Update the field with a date 20 years from now
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, 20);
            suspensionDate = c.getTime();
            inputSuspensionDate.setDate(suspensionDate);
        } else {
            inputSuspensionDate.setDate(new Date());
        }
    }//GEN-LAST:event_chkSuspendIndefActionPerformed

    /**
     * Action-event when the user clicks the Remove check-box
     * @param evt 
     */
    private void chkRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRemoveActionPerformed
        //--If checked, remove check from chkSuspendIndef
        if (chkRemove.isSelected()) {
            chkSuspendIndef.setSelected(false);
        }
        inputSuspensionDate.setDate(new Date());

    }//GEN-LAST:event_chkRemoveActionPerformed

    /**
     * Action-event when the user updates the suspension input selector
     * @param evt 
     */
    private void inputSuspensionDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSuspensionDateActionPerformed
        //--Remove check box selections
        chkSuspendIndef.setSelected(false);
        chkRemove.setSelected(false);    
    }//GEN-LAST:event_inputSuspensionDateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //--Set the SeaGlass look and feel
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.getMessage();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuspend;
    private javax.swing.JCheckBox chkRemove;
    private javax.swing.JCheckBox chkSuspendIndef;
    private org.jdesktop.swingx.JXDatePicker inputSuspensionDate;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblSuspended;
    // End of variables declaration//GEN-END:variables
}
