//Author: Ian Hamilton - 15003706
package administration;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class EditUser extends javax.swing.JFrame {

    int currentUserID;
    String currentUserName;
    int userID, teacherID = 0;

    /**
     * Constructor - Displays the logged-in user details and populates the
     * drop-down menus and existing user details
     *
     * @param userID
     * @param name
     * @param selectedUserID
     */
    public EditUser(int userID, String name, int selectedUserID) {
        initComponents(); //Create and display components
        this.currentUserID = userID;
        this.currentUserName = name;
        this.userID = selectedUserID;
        displayUserDetails(); //Display logged in user details

        populateTeacherDropdown(); //Populates teacher drop down menu
        populateFields(); //Populates all fields with users information
    }

    /**
     * Populates the teacher drop-down menu with teachers in the db
     */
    private void populateTeacherDropdown() {
        //-SQL for getting all teachers
        String sqlSelectTeachers = "SELECT UserDetails.user_ID, FName, LName "
                + "FROM UserDetails JOIN LoginCredentials ON (LoginCredentials.user_ID = UserDetails.user_ID) "
                + "WHERE LoginCredentials.UserLevel = 'TEACHER'";

        try {
            //-Connect to the Database
            Connection conn = DBConnectA.connectToDB();

            //--Query the database
            Statement stmt = conn.createStatement();
            ResultSet selectResults = stmt.executeQuery(sqlSelectTeachers);

            while (selectResults.next()) {
                int teacher_ID = selectResults.getInt("user_ID");
                String FName = selectResults.getString("FName");
                String LName = selectResults.getString("LName");
                String teacherName = (FName + " " + LName);
                cmbTeacher.addItem(new DropdownItem(teacher_ID, teacherName));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Populates fields with user information found in the database
     */
    private void populateFields() {
        String userType = null;
        int userClass = 0;
        String fName = null;
        String lName = null;
        String email = null;
        Date suspendedUntil = null;

        //-SQL for getting user details
        String sqlSelectGeneral = "SELECT UserLevel, SuspendedUntil, FName, LName, Email "
                + "FROM LoginCredentials JOIN UserDetails ON LoginCredentials.user_ID = UserDetails.user_ID "
                + "WHERE LoginCredentials.user_ID = ?";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();

            //--Query database
            PreparedStatement GeneralStmt = conn.prepareStatement(sqlSelectGeneral);
            GeneralStmt.setInt(1, userID);

            ResultSet generalResults = GeneralStmt.executeQuery();

            while (generalResults.next()) {
                userType = generalResults.getString("UserLevel");
                fName = generalResults.getString("FName");
                lName = generalResults.getString("LName");
                email = generalResults.getString("Email");
                String suspendedUntilString = generalResults.getString("SuspendedUntil");
                //-Convert date string to date format yyyy-MM-dd
                suspendedUntil = new SimpleDateFormat("yyyy-MM-dd").parse(suspendedUntilString);
            }

            //-Set the User Details fields - minus teacher and class
            cmbType.setSelectedItem(userType);
            inputFName.setText(fName);
            inputLName.setText(lName);
            inputEmail.setText(email);
            inputSuspensionDate.setDate(suspendedUntil);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception", "Connection Failed", JOptionPane.PLAIN_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Parse Exception", "Connection Failed", JOptionPane.PLAIN_MESSAGE);
        }

        //--Exit function if the user isn't a student
        if (!userType.equals("STUDENT")) {
            return;
        }

        //-SQL for getting student class and teacher
        String sqlSelectStudent = "SELECT StudentDetails.class_ID, teacher_ID "
                + "FROM StudentDetails JOIN ClassTeacher ON StudentDetails.class_ID = ClassTeacher.class_ID "
                + "WHERE student_ID = ?";
        //--Enable fields relating to students
        lblAlTeacher.setEnabled(true);
        cmbTeacher.setEnabled(true);
        cmbClass.setEnabled(true);

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();

            //--Query Database
            PreparedStatement StudentStmt = conn.prepareStatement(sqlSelectStudent);
            StudentStmt.setInt(1, userID);
            ResultSet studentResults = StudentStmt.executeQuery();

            while (studentResults.next()) {
                userClass = studentResults.getInt("class_ID");
                this.teacherID = studentResults.getInt("teacher_ID");
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Error");
        }

        //-SQL for getting teacher's name
        String sqlSelectTeacherName = "SELECT FName, LName "
                + "FROM UserDetails "
                + "WHERE user_ID = ?";

        try {
            //-Connect to database
            Connection conn = DBConnectA.connectToDB();

            //--Query database
            PreparedStatement TeacherNameStmt = conn.prepareStatement(sqlSelectTeacherName);
            TeacherNameStmt.setInt(1, teacherID);
            ResultSet teacherResults = TeacherNameStmt.executeQuery();

            while (teacherResults.next()) {
                String teacherFName = teacherResults.getString("FName");
                String teacherLName = teacherResults.getString("LName");
                String name = teacherFName + " " + teacherLName;

                //--Check if the name of the teacher is in the dropdown menu
                for (int i = 0; i < cmbTeacher.getItemCount(); i++) {
                    String cmbTeacherOption = cmbTeacher.getItemAt(i).toString();
                    //--If the names match, set the selected index
                    if (cmbTeacherOption.equals(name)) {
                        cmbTeacher.setSelectedIndex(i);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Teacher Name SQL Query Error: " + e.getMessage());
        }

        //--Map the class ID to an option index
        int modOption = Integer.valueOf(userClass) % 3;
        if (modOption == 0) {
            modOption = 3;
        }

        //--Set the required class option
        for (int x = 0; x < cmbClass.getItemCount(); x++) {
            String cmbClassOption = cmbClass.getItemAt(x).toString();

            if (cmbClassOption.equals(String.valueOf(modOption))) {
                cmbClass.setSelectedIndex(x);
            }
        }
    }

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
        lblUserType = new javax.swing.JLabel();
        lblAlTeacher = new javax.swing.JLabel();
        lblFName = new javax.swing.JLabel();
        lblLName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        lblSuspended = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox();
        cmbTeacher = new javax.swing.JComboBox();
        inputFName = new javax.swing.JTextField();
        inputLName = new javax.swing.JTextField();
        inputEmail = new javax.swing.JTextField();
        inputSuspensionDate = new org.jdesktop.swingx.JXDatePicker();
        cmbClass = new javax.swing.JComboBox();
        chkSuspendIndef = new javax.swing.JCheckBox();
        lblUserType1 = new javax.swing.JLabel();
        lblInformation = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit User");
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
        lblUserLevel.setText("Admin");

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(5, 24, 42));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblUserType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserType.setForeground(new java.awt.Color(5, 24, 42));
        lblUserType.setText("* TYPE OF USER:");
        lblUserType.setEnabled(false);

        lblAlTeacher.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAlTeacher.setForeground(new java.awt.Color(5, 24, 42));
        lblAlTeacher.setText("* ALLOCATED TEACHER:");
        lblAlTeacher.setEnabled(false);

        lblFName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFName.setForeground(new java.awt.Color(5, 24, 42));
        lblFName.setText("* FIRST NAME:");

        lblLName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblLName.setForeground(new java.awt.Color(5, 24, 42));
        lblLName.setText("* LAST NAME:");

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(5, 24, 42));
        lblEmail.setText("* EMAIL:");

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(5, 24, 42));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblSuspended.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSuspended.setForeground(new java.awt.Color(5, 24, 42));
        lblSuspended.setText("SUSPENDED UNTIL: ");

        cmbType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbType.setForeground(new java.awt.Color(5, 24, 42));
        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<select>", "STUDENT", "TEACHER", "CREATOR", "ADMIN" }));
        cmbType.setEnabled(false);
        cmbType.setPreferredSize(new java.awt.Dimension(170, 30));

        cmbTeacher.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbTeacher.setForeground(new java.awt.Color(5, 24, 42));
        cmbTeacher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<teacher>" }));
        cmbTeacher.setEnabled(false);
        cmbTeacher.setPreferredSize(new java.awt.Dimension(170, 30));

        inputFName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputFName.setForeground(new java.awt.Color(5, 24, 42));
        inputFName.setPreferredSize(new java.awt.Dimension(170, 30));
        inputFName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputFNameKeyPressed(evt);
            }
        });

        inputLName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputLName.setForeground(new java.awt.Color(5, 24, 42));
        inputLName.setPreferredSize(new java.awt.Dimension(170, 30));
        inputLName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputLNameKeyPressed(evt);
            }
        });

        inputEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputEmail.setForeground(new java.awt.Color(5, 24, 42));
        inputEmail.setPreferredSize(new java.awt.Dimension(170, 30));
        inputEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputEmailKeyPressed(evt);
            }
        });

        inputSuspensionDate.setForeground(new java.awt.Color(5, 24, 42));
        inputSuspensionDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputSuspensionDate.setFormats("dd-MM-yyyy");
        inputSuspensionDate.setPreferredSize(new java.awt.Dimension(170, 30));

        cmbClass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbClass.setForeground(new java.awt.Color(5, 24, 42));
        cmbClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<class>", "1", "2", "3" }));
        cmbClass.setEnabled(false);
        cmbClass.setPreferredSize(new java.awt.Dimension(170, 30));

        chkSuspendIndef.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chkSuspendIndef.setForeground(new java.awt.Color(5, 24, 42));
        chkSuspendIndef.setText("Indefinitely");

        lblUserType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserType1.setForeground(new java.awt.Color(5, 24, 42));
        lblUserType1.setText("* (required)");

        lblInformation.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblInformation.setForeground(new java.awt.Color(200, 0, 0));
        lblInformation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformation.setText(" ");
        lblInformation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
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
                .addComponent(btnLogout)
                .addGap(30, 30, 30))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSuspended, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAlTeacher, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblUserType1)
                        .addComponent(lblUserType)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inputFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(inputSuspensionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkSuspendIndef)))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUserType1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserType))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmbTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlTeacher)
                    .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(inputFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFName))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(inputLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLName))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(inputSuspensionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSuspended)
                    .addComponent(chkSuspendIndef))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblInformation)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
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

    /**
     * Populates the top section of the window with the logged-in users details
     */
    private void displayUserDetails() {
        lblUserDetails.setText(currentUserName + " (" + Integer.toString(currentUserID) + ")");
        lblUserLevel.setText("ADMIN");
    }

    /**
     * Saves the updated details to the database
     */
    private void saveUser() {
        String userLevel, FName, LName, email = null, suspendDate = null,
                teacherName = null;
        int classID = 0;
        lblInformation.setText(" ");

        //--Has a usertype been chosen
        if (cmbType.getSelectedItem().equals("<select>")) {
            lblInformation.setText("Select a Type of User.");
            return;
        } else {
            userLevel = String.valueOf(cmbType.getSelectedItem()).toUpperCase();
        }

        //--Is the usertype student
        if (userLevel.equals("STUDENT")) {
            //--Has a Teacher been selected
            if (cmbTeacher.getSelectedItem().equals("<teacher>")) {
                lblInformation.setText("Select a Teacher.");
                return;
            } else {
                teacherName = String.valueOf(cmbTeacher.getSelectedItem());
            }

            //--Has a Class been selected
            if (cmbClass.getSelectedItem().equals("<class>")) {
                lblInformation.setText("Select a Class.");
                return;
            } else {
                try {
                    String classIDString = cmbClass.getSelectedItem().toString();
                    classID = Integer.valueOf(classIDString);
                } catch (NumberFormatException e) {
                    System.out.println("PARSE CLASS INT EXCEPTION");
                    return;
                }
            }
        }

        //--Is there a first name entered
        if (inputFName.getText().trim().equals("")) {
            lblInformation.setText("Enter a First Name.");
            return;
        } else {
            FName = inputFName.getText().trim();
        }

        //--Is there a last name entered
        if (inputLName.getText().trim().equals("")) {
            lblInformation.setText("Enter a Last Name.");
            return;
        } else {
            LName = inputLName.getText().trim();
        }

        //--Is there an email entered
        if (inputEmail.getText().trim().equals("")) {
            lblInformation.setText("Enter an Email.");
            return;
        } else {
            //-Check if the email is valid (_@_)
            Boolean isEmailValid = isValidEmail(inputEmail.getText().trim());
            if (!isEmailValid) {
                lblInformation.setText("Not a Valid Email.");
                return;
            }
            email = inputEmail.getText().trim();
        }

        //--Suspension Date is not required, if it is set, assign it
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (inputSuspensionDate.getDate() != null) {
            try {
                suspendDate = dFormat.format(inputSuspensionDate.getDate());
            } catch (Exception e) {
                System.out.println("FAILED TO PARSE SUSPENSION DATE");
            }
        } else {
            //-Default suspension date is today (not suspended)
            suspendDate = dFormat.format(new Date());
        }

        //--Check if sespension is indefinite
        if (chkSuspendIndef.isSelected()) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, 30);
            suspendDate = dFormat.format(c.getTime());
        }

        //--All the required fields are completed   
        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();

            //-Update the Database with new login credentials
            updateLoginCredentials(conn, userID, suspendDate);
            //-Update the Database with new User details
            updateUserDetails(conn, userID, FName, LName, email);

            //--If student, update StudentDetails
            if (userLevel.equals("STUDENT")) {
                //-Get the new class ID
                classID = getClassID(classID);
                //-Update the Database with new student details
                updateStudentDetails(conn, userID, classID);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ONE OR MORE UPDATES FAILED", "UPDATE FAILED", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        //-Tell the user that the update is successful
        JOptionPane.showMessageDialog(null, "User Updated Successfully", "Confirmation", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Get the teacher's class ID of the chosen 1-3 option
     *
     * @param classID
     * @return classID of the chosen teacher and class
     * @throws SQLException
     */
    private int getClassID(int classID) throws SQLException {
        int returnVal = 0;
        int classIDRef = classID - 1;
        //--Get the teacherID from the select option
        Object option = cmbTeacher.getSelectedItem();
        int chosenTeacherID = ((DropdownItem) option).getOptionID();

        //-SQL for getting class_ID, offset by ref amount
        String sqlSelect = "SELECT class_ID FROM ClassTeacher WHERE teacher_ID = ? "
                + "ORDER BY class_ID ASC LIMIT ?,1";

        //-Connect to Database
        Connection conn = DBConnectA.connectToDB();
        //--Execute Query
        PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
        pstmt.setInt(1, chosenTeacherID);
        pstmt.setInt(2, classIDRef);
        ResultSet selectResults = pstmt.executeQuery();

        while (selectResults.next()) {
            returnVal = selectResults.getInt("class_ID");
        }
        return returnVal;
    }

    /**
     * Update the login credentials of the user with the new suspension date
     *
     * @param conn
     * @param userID
     * @param suspendDate
     * @throws SQLException
     */
    private void updateLoginCredentials(Connection conn, int userID, String suspendDate) throws SQLException {
        //SQL to update suspension date for the user
        String sqlUpdateCred = "UPDATE LoginCredentials SET SuspendedUntil = ? "
                + "WHERE user_ID = ?";

        //--Execute update
        PreparedStatement updtStmt = conn.prepareStatement(sqlUpdateCred);
        updtStmt.setString(1, suspendDate);
        updtStmt.setInt(2, userID);

        updtStmt.executeUpdate();
    }

    /**
     * Update the database with the new details relating to the student
     *
     * @param conn
     * @param userID
     * @param chosenClassID
     * @throws SQLException
     */
    private void updateStudentDetails(Connection conn, int userID, int chosenClassID) throws SQLException {
        //-SQL to updte the class ID for the user
        String sqlUpdateCred = "UPDATE StudentDetails SET class_ID = ? "
                + "WHERE student_ID = ?";

        //--Execute update
        PreparedStatement updtStmt = conn.prepareStatement(sqlUpdateCred);
        updtStmt.setInt(1, chosenClassID);
        updtStmt.setInt(2, userID);

        updtStmt.executeUpdate();
    }

    /**
     * Update the database with new user details relating to the user
     *
     * @param conn
     * @param userID
     * @param FName
     * @param LName
     * @param email
     * @throws SQLException
     */
    private void updateUserDetails(Connection conn, int userID, String FName, String LName, String email) throws SQLException {
        //-SQL to update the user details
        String sqlUpdateUser = "UPDATE UserDetails SET FName = ?, "
                + "LName = ?, "
                + "Email = ? "
                + "WHERE user_ID = ?";

        //--Execute update
        PreparedStatement updtStmt = conn.prepareStatement(sqlUpdateUser);
        updtStmt.setString(1, FName);
        updtStmt.setString(2, LName);
        updtStmt.setString(3, email);
        updtStmt.setInt(4, userID);

        updtStmt.executeUpdate();
    }

    /**
     * Checks if the entered email is valid
     *
     * @param emailToValidate
     * @return Boolean: true if the email is valid
     */
    public boolean isValidEmail(String emailToValidate) {
        boolean result = true;
        try {
            InternetAddress validateThis = new InternetAddress(emailToValidate);
            validateThis.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    /**
     * Action-event when the user clicks logout
     *
     * @param evt
     */
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

    /**
     * Action-event when the user clicks save
     *
     * @param evt
     */
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveUser();
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * Action-event when the user hits enter on their keyboard instead of
     * clicking the save button
     *
     * @param evt
     */
    private void inputFNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputFNameKeyPressed
        //--User hits enter instead of clicking save
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            saveUser();
        }
    }//GEN-LAST:event_inputFNameKeyPressed

    /**
     * Action-event when the user hits enter on their keyboard instead of
     * clicking the save button
     *
     * @param evt
     */
    private void inputLNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputLNameKeyPressed
        //--User hits enter instead of clicking save
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            saveUser();
        }
    }//GEN-LAST:event_inputLNameKeyPressed

    /**
     * Action-event when the user hits enter on their keyboard instead of
     * clicking the save button
     *
     * @param evt
     */
    private void inputEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEmailKeyPressed
        //--User hits enter instead of clicking save
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            saveUser();
        }
    }//GEN-LAST:event_inputEmailKeyPressed

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
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkSuspendIndef;
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JComboBox cmbTeacher;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputFName;
    private javax.swing.JTextField inputLName;
    private org.jdesktop.swingx.JXDatePicker inputSuspensionDate;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblAlTeacher;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFName;
    private javax.swing.JLabel lblInformation;
    private javax.swing.JLabel lblLName;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSuspended;
    private javax.swing.JLabel lblUserDetails;
    private javax.swing.JLabel lblUserLevel;
    private javax.swing.JLabel lblUserType;
    private javax.swing.JLabel lblUserType1;
    // End of variables declaration//GEN-END:variables
}
