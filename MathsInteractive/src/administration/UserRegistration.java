//Author: Ian Hamilton - 15003706
package administration;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class UserRegistration extends javax.swing.JFrame {

    int userID;
    String name;

    /**
     * Constructor - displays top panel and populates drop-down menus
     *
     * @param userID
     * @param name
     */
    public UserRegistration(int userID, String name) {
        initComponents();
        this.userID = userID;
        this.name = name;
        displayUserDetails();

        populateTeacherDropdown();
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
        btnCreate = new javax.swing.JButton();
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
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User Registration");
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

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(5, 24, 42));
        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        lblSuspended.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSuspended.setForeground(new java.awt.Color(5, 24, 42));
        lblSuspended.setText("SUSPENDED UNTIL: ");

        cmbType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbType.setForeground(new java.awt.Color(5, 24, 42));
        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<select>", "Student", "Teacher", "Content Creator", "Admin" }));
        cmbType.setPreferredSize(new java.awt.Dimension(170, 30));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });

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

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(5, 24, 42));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

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
                .addComponent(lblInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreate)
                .addGap(73, 73, 73))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnBack))
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
     * Populates the Teacher Drop-down menu with teachers found in db
     */
    private void populateTeacherDropdown() {
        //-SQL to get the teacher's details
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

                //--Add the teacher to the dropdown menu
                cmbTeacher.addItem(new DropdownItem(teacherID, teacherName));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Checks if all required fields are populated and then updates the db with
     * the new user
     */
    private void createUser() {
        String userLevel, FName, LName, email = null, suspendDate = null,
                newPasswordHash = null;
        int classID = 0, newID = 0;
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
            }

            //--Has a Class been selected
            if (cmbClass.getSelectedItem().equals("<class>")) {
                lblInformation.setText("Select a Class.");
                return;
            } else {
                try {
                    String classIDString = String.valueOf(cmbClass.getSelectedItem());
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

        //--Check if suspension is indefinite
        if (chkSuspendIndef.isSelected()) {
            //--Set the suspension date to 20 years from now
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, 20);
            suspendDate = dFormat.format(c.getTime());
        }

        //--All the required fields are completed 
        //-Get a new ID for the new user
        newID = getNewID();
        //-Get a new password hash
        newPasswordHash = generateNewPasswordHash(FName, newID);

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();

            //--Insert general user details
            insertLoginCredentials(conn, newID, newPasswordHash, suspendDate, userLevel);
            insertUserDetails(conn, newID, FName, LName, email);

            //--If student, insert StudentDetails and QuizBuffer
            if (userLevel.equals("STUDENT")) {
                classID = getClassID(classID);

                insertStudentDetails(conn, newID, classID);
                insertQuizBuffer(conn, newID);
            }

            //--If teacher, insert three new classes
            if (userLevel.equals("TEACHER")) {
                insertThreeClasses(conn, newID);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ONE OR MORE UPDATES FAILED", "UPDATE FAILED", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        //--Email the user their login information
        try {
            String newPW = FName + String.valueOf(newID);
            Mail newMail = new Mail(2, newID, FName, LName, newPW, email);
        } catch (MessagingException e) {
            //--The email failed, tell the admin the information
            String msg = ("Email failed. The username is: " + Integer.toString(newID) + ". The Password is: " + FName + String.valueOf(newID) + ".");
            JOptionPane.showMessageDialog(null, msg, "Email Failed", JOptionPane.PLAIN_MESSAGE);
            System.out.println(e.getMessage());
        }

        //-Tell the admin the user was created
        JOptionPane.showMessageDialog(null, "User Created Successfully", "Confirmation", JOptionPane.PLAIN_MESSAGE);

        //-Dispose of the current window
        this.dispose();
    }

    /**
     * Inserts 3 classes for the teacher into the DB
     *
     * @param conn
     * @param newID
     * @throws SQLException
     */
    private void insertThreeClasses(Connection conn, int newID) throws SQLException {
        //-SQL to insert a teacher class
        String sqlTeacherInsert = "INSERT INTO ClassTeacher(teacher_ID) VALUES (?)";

        //--Execute updates
        PreparedStatement pstmt = conn.prepareStatement(sqlTeacherInsert);
        pstmt.setInt(1, newID);
        pstmt.executeUpdate();

        PreparedStatement pstmt2 = conn.prepareStatement(sqlTeacherInsert);
        pstmt2.setInt(1, newID);
        pstmt2.executeUpdate();

        PreparedStatement pstmt3 = conn.prepareStatement(sqlTeacherInsert);
        pstmt3.setInt(1, newID);
        pstmt3.executeUpdate();
    }

    /**
     * Inserts a blank quiz buffer into the db for the new student
     *
     * @param conn
     * @param newID
     * @throws SQLException
     */
    private void insertQuizBuffer(Connection conn, int newID) throws SQLException {
        //-SQL to insert a quiz buffer
        String sqlStudentInsert2 = "INSERT INTO QuizBuffer(student_ID, Buffer1, Buffer2, Buffer3) VALUES (?, null, null, null)";

        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlStudentInsert2);
        pstmt.setInt(1, newID);

        pstmt.executeUpdate();
    }

    /**
     * Inserts details into the db for the new student
     *
     * @param conn
     * @param newID
     * @param classID
     * @throws SQLException
     */
    private void insertStudentDetails(Connection conn, int newID, int classID) throws SQLException {
        //-SQL to insert stuent details
        String sqlStudentInsert1 = "INSERT INTO StudentDetails(student_ID, class_ID) VALUES (?, "
                + "?)";

        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlStudentInsert1);
        pstmt.setInt(1, newID);
        pstmt.setInt(2, classID);

        pstmt.executeUpdate();
    }

    /**
     * Inserts general user details into db for new user
     *
     * @param conn
     * @param newID
     * @param FName
     * @param LName
     * @param email
     * @throws SQLException
     */
    private void insertUserDetails(Connection conn, int newID, String FName, String LName, String email) throws SQLException {
        //-SQL to insert user details
        String sqlInsertUDetails = "INSERT INTO UserDetails VALUES (?, "
                + "?, "
                + "?, "
                + "?)";

        //--Execute query
        PreparedStatement pstmt = conn.prepareStatement(sqlInsertUDetails);
        pstmt.setInt(1, newID);
        pstmt.setString(2, FName);
        pstmt.setString(3, LName);
        pstmt.setString(4, email);

        pstmt.executeUpdate();
    }

    /**
     * Inserts a record of the new users login credentials
     *
     * @param conn
     * @param newID
     * @param newPasswordHash
     * @param suspendDate
     * @param userLevel
     * @throws SQLException
     */
    private void insertLoginCredentials(Connection conn, int newID, String newPasswordHash, String suspendDate, String userLevel) throws SQLException {
        //-SQL to insert login credentials
        String sqlInsertCred = "INSERT INTO LoginCredentials(user_ID, passwordHash, SuspendedUntil, UserLevel) VALUES (?, "
                + "?, "
                + "?, "
                + "?)";

        //--Execute update
        PreparedStatement pstmt = conn.prepareStatement(sqlInsertCred);
        pstmt.setInt(1, newID);
        pstmt.setString(2, newPasswordHash);
        pstmt.setString(3, suspendDate);
        pstmt.setString(4, userLevel);

        pstmt.executeUpdate();
    }

    /**
     * Generates a new password hash for the new user
     *
     * @param FName
     * @param newID
     * @return hashed password
     */
    private String generateNewPasswordHash(String FName, int newID) {
        String defaultPassword = (FName + String.valueOf(newID));
        return BCrypt.hashpw(defaultPassword, BCrypt.gensalt());
    }

    /**
     * Gets the new user ID for the new user (max id + 1)
     *
     * @return the new user ID
     */
    private int getNewID() {
        int returnID = 0;
        //-SQL to get the max userID
        String sqlGetNextUserID = "SELECT MAX(user_ID) AS maxID FROM LoginCredentials";

        try {
            //-Connect to Database
            Connection conn = DBConnectA.connectToDB();
            //--Execute query
            Statement stmt = conn.createStatement();
            ResultSet resultMaxID = stmt.executeQuery(sqlGetNextUserID);

            while (resultMaxID.next()) {
                returnID = resultMaxID.getInt("maxID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //Return the next user ID (max + 1)
        return returnID + 1;
    }

    /**
     * Checks if the email entered is valid
     *
     * @param emailToValidate
     * @return Boolean - true if email is valid
     */
    public boolean isValidEmail(String emailToValidate) {
        boolean result = true;
        //--Checks if the email is valid (_@_)
        try {
            InternetAddress validateThis = new InternetAddress(emailToValidate);
            validateThis.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    /**
     * Gets the class id of the teacher associated with the chosen class id
     *
     * @param classID
     * @return
     * @throws SQLException
     */
    private int getClassID(int classID) throws SQLException {
        int returnVal = 0;
        int classIDRef = classID - 1;
        //--Get the teacher ID of the chosen option
        Object Option = cmbTeacher.getSelectedItem();
        int chosenTeacherID = ((DropdownItem) Option).getOptionID();

        //-SQL to get the classID
        String sqlSelect = "SELECT class_ID FROM ClassTeacher WHERE teacher_ID = ? "
                + "ORDER BY class_ID ASC LIMIT ?,1";

        //-Connect to Database
        Connection conn = DBConnectA.connectToDB();
        //--Execute query
        PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
        pstmt.setInt(1, chosenTeacherID);
        pstmt.setInt(2, classIDRef);
        ResultSet selectResults = pstmt.executeQuery();

        while (selectResults.next()) {
            returnVal = selectResults.getInt("class_ID");
        }

        //-Return the classID
        return returnVal;
    }

    /**
     * Populates the top area with the logged-in users details
     */
    private void displayUserDetails() {
        lblUserDetails.setText(name + " (" + Integer.toString(userID) + ")");
        lblUserLevel.setText("ADMIN");
    }

    /**
     * Event-handler when the user clicks logout
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
     * Event-handler when the user clicks create
     *
     * @param evt
     */
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        createUser();
    }//GEN-LAST:event_btnCreateActionPerformed

    /**
     * Event-handler when the user changes the type of the new user
     *
     * @param evt
     */
    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        String action = evt.getActionCommand();
        if (action.equals("comboBoxChanged")) {
            //--Has the type been set to student
            if (cmbType.getSelectedItem().equals("Student")) {
                lblAlTeacher.setEnabled(true);
                cmbTeacher.setEnabled(true);
                cmbClass.setEnabled(true);
            } else {
                lblAlTeacher.setEnabled(false);
                cmbTeacher.setEnabled(false);
                cmbClass.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbTypeActionPerformed

    /**
     * Event-handler when the user hits enter on their keyboard instead of
     * clicking the create button
     *
     * @param evt
     */
    private void inputFNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputFNameKeyPressed
        //--User hit enter instead of clicking the button
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            createUser();
        }
    }//GEN-LAST:event_inputFNameKeyPressed

    /**
     * Event-handler when the user hits enter on their keyboard instead of
     * clicking the create button
     *
     * @param evt
     */
    private void inputLNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputLNameKeyPressed
        //--User hit enter instead of clicking the button
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            createUser();
        }
    }//GEN-LAST:event_inputLNameKeyPressed

    /**
     * Event-handler when the user hits enter on their keyboard instead of
     * clicking the create button
     *
     * @param evt
     */
    private void inputEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputEmailKeyPressed
        //--User hit enter instead of clicking the button
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            createUser();
        }
    }//GEN-LAST:event_inputEmailKeyPressed

    /**
     * Event-handler when the user clicks back (closes the current window to
     * display the menu)
     *
     * @param evt
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        for (Window w : java.awt.Window.getWindows()) {
            w.setVisible(false);
            w.dispose();
        }

        //--Open the Admin  window centre-screen
        AdminMenu adminMenuFrame = new AdminMenu(this.userID);
        adminMenuFrame.setLocationRelativeTo(null);
        adminMenuFrame.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnLogout;
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
