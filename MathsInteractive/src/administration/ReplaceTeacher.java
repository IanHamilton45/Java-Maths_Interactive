//Author: Ian Hamilton - 15003706
package administration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class ReplaceTeacher extends javax.swing.JFrame {

    private final int oldTeacherID;
    private int newTeacherID;

    /**
     * Creates new form ReplaceTeacher
     *
     * @param teacher_ID
     */
    public ReplaceTeacher(int teacher_ID) {
        initComponents();
        this.oldTeacherID = teacher_ID;
        populateTeacherDropdown();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnConfirm = new javax.swing.JButton();
        lblReplace = new javax.swing.JLabel();
        cmbNewTeacher = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Replace Teacher");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel3.setBackground(new java.awt.Color(179, 218, 255));

        btnConfirm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(5, 24, 42));
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        lblReplace.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblReplace.setForeground(new java.awt.Color(5, 24, 42));
        lblReplace.setText("Replace With:");

        cmbNewTeacher.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbNewTeacher.setForeground(new java.awt.Color(5, 24, 42));
        cmbNewTeacher.setPreferredSize(new java.awt.Dimension(190, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lblReplace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbNewTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirm)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReplace)
                    .addComponent(cmbNewTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfirm)
                .addContainerGap(19, Short.MAX_VALUE))
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
     * Delete the relevant 3 classes the teacher has
     *
     * @param conn
     * @param selectedUserID
     * @throws SQLException
     */
    private void deleteThreeClasses(Connection conn, int selectedUserID) throws SQLException {
        //-SQL to delete the classes of the teacher
        String sqlDelete = "DELETE FROM ClassTeacher WHERE teacher_ID = ?";
        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setInt(1, selectedUserID);

        pstmt.executeUpdate();
    }

    /**
     * Populate the drop down menu with the other teachers
     */
    private void populateTeacherDropdown() {
        //-SQL to get the teachers
        String sqlSelectTeachers = "SELECT UserDetails.user_ID, FName, LName "
                + "FROM UserDetails JOIN LoginCredentials ON (LoginCredentials.user_ID = UserDetails.user_ID) "
                + "WHERE LoginCredentials.UserLevel = 'TEACHER'";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute query
            Statement stmt = conn.createStatement();
            ResultSet selectResults = stmt.executeQuery(sqlSelectTeachers);

            while (selectResults.next()) {
                int teacherID = selectResults.getInt("user_ID");
                String FName = selectResults.getString("FName");
                String LName = selectResults.getString("LName");
                String teacherName = (FName + " " + LName);

                //--If the teacherID is the deleted teacher
                if (teacherID == this.oldTeacherID) {
                    continue; //Don't add it to the dropdown
                }

                //-Add the teacher to the dropdown list
                cmbNewTeacher.addItem(new DropdownItem(teacherID, teacherName));
            }
        } catch (SQLException e) {
            System.out.println("Populate dropdown error: " + e.getMessage());
        }
    }

    /**
     * Delete the database rows relating to the teacher
     */
    private void processTeacher() {
        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();

            updateClasses(conn);

            ManageUsers.deleteLoginCredentials(conn, this.oldTeacherID);
            ManageUsers.deleteUserDetails(conn, this.oldTeacherID);
            ManageUsers.deleteForumThreads(conn, this.oldTeacherID);
            ManageUsers.deleteForumPosts(conn, this.oldTeacherID);

            deleteThreeClasses(conn, this.oldTeacherID);

            //-Tell the user the delete and update was successful
            JOptionPane.showMessageDialog(null, "Teacher Deleted and replacement set", "Confirmation", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete update three classes to replacement classes
     *
     * @param conn
     */
    private void updateClasses(Connection conn) {
        int oldID1, oldID2, oldID3, newID1, newID2, newID3;

        try {
            //--Get the relating classIDs
            oldID1 = getClass(conn, this.oldTeacherID, 0);
            oldID2 = getClass(conn, this.oldTeacherID, 1);
            oldID3 = getClass(conn, this.oldTeacherID, 2);

            newID1 = getClass(conn, this.newTeacherID, 0);
            newID2 = getClass(conn, this.newTeacherID, 1);
            newID3 = getClass(conn, this.newTeacherID, 2);

            //--Update the classes
            updateClass(conn, oldID1, newID1);
            updateClass(conn, oldID2, newID2);
            updateClass(conn, oldID3, newID3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update the class of the student to be the replacement class
     *
     * @param conn
     * @param oldID
     * @param newID
     * @throws SQLException
     */
    private void updateClass(Connection conn, int oldID, int newID) throws SQLException {
        //-SQL to update the class
        String sqlUpdate = "UPDATE StudentDetails "
                + "SET class_ID = ? "
                + "WHERE class_ID = ?";

        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setInt(1, newID);
        pstmt.setInt(2, oldID);

        pstmt.executeUpdate();
    }

    /**
     * Get the class of the teacher
     *
     * @param conn
     * @param id
     * @param offset
     * @return
     * @throws SQLException
     */
    private int getClass(Connection conn, int id, int offset) throws SQLException {
        int returnID = 0;
        //-SQL to get the class of the teacher at an offset
        //(first, second, or third class)
        String sqlClass = "SELECT class_ID "
                + "FROM ClassTeacher "
                + "WHERE teacher_ID = ? "
                + "ORDER BY class_ID ASC LIMIT ?,1";

        //--Execute query
        PreparedStatement stmtClass = conn.prepareStatement(sqlClass);
        stmtClass.setInt(1, id);
        stmtClass.setInt(2, offset);
        ResultSet rs = stmtClass.executeQuery();

        while (rs.next()) {
            returnID = rs.getInt("class_ID");
        }

        return returnID;
    }

    /**
     * Update the new teacher id from the chosen option
     */
    private void updateNewTeacherID() {
        Object Option = cmbNewTeacher.getSelectedItem();
        int chosenTeacherID = ((DropdownItem) Option).getOptionID();
        this.newTeacherID = chosenTeacherID;
    }

    /**
     * ActionEvent when the user clicks confirm
     *
     * @param evt
     */
    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        //--Update the replacement teacher ID and process the delete
        updateNewTeacherID();
        processTeacher();

        //--Dispose of the current window
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnConfirmActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //--Set the SeaGlass look and feel
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.getMessage();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JComboBox cmbNewTeacher;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblReplace;
    // End of variables declaration//GEN-END:variables
}
