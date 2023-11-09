//Author: Ian Hamilton - 15003706
package administration;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class DatabaseSearch extends javax.swing.JFrame {

    private final int userID;
    private final String name;
    private static int numResults = 0;

    /**
     * Constructor
     *
     * @param userID
     * @param name
     */
    public DatabaseSearch(int userID, String name) {
        initComponents(); //Create and display components
        this.userID = userID;
        this.name = name;
        displayUserDetails(); //Display logged in user details

        search(); //Populate the search window with un-filtered data
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblUserDetails = new javax.swing.JLabel();
        lblUserLevel = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblList = new javax.swing.JLabel();
        cmbListOption = new javax.swing.JComboBox();
        lblSearch = new javax.swing.JLabel();
        inputSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        scrollPane = new javax.swing.JScrollPane();
        lblNoResults = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Database Search");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(179, 218, 255));

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(5, 24, 42));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

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

        lblList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblList.setForeground(new java.awt.Color(5, 24, 42));
        lblList.setText("Category:");

        cmbListOption.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbListOption.setForeground(new java.awt.Color(5, 24, 42));
        cmbListOption.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Learning Packages", "Quizzes", "Teachers" }));
        cmbListOption.setPreferredSize(new java.awt.Dimension(288, 30));
        cmbListOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbListOptionActionPerformed(evt);
            }
        });

        lblSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(5, 24, 42));
        lblSearch.setText("Search:");

        inputSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputSearch.setForeground(new java.awt.Color(5, 24, 42));
        inputSearch.setPreferredSize(new java.awt.Dimension(288, 30));
        inputSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputSearchKeyPressed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(5, 24, 42));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        scrollPane.setForeground(new java.awt.Color(5, 24, 42));
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new java.awt.Dimension(480, 260));
        scrollPane.setMinimumSize(new java.awt.Dimension(480, 260));
        scrollPane.setPreferredSize(new java.awt.Dimension(480, 260));

        lblNoResults.setText(" ");
        lblNoResults.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        scrollPane.setViewportView(lblNoResults);

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbListOption, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
            .addComponent(jSeparator3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnSearch)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblList)
                    .addComponent(cmbListOption, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
     * Search the db based on the search parameters and dynamically populate the
     * JScrollPane with custom panes containing information
     */
    private void search() {
        numResults = 0;
        //0: Learning Packages, 1: Quizzes, 2: Teachers
        int chosenOption = cmbListOption.getSelectedIndex();
        //-Set the search parameter as the trimmed user input
        String searchParam = inputSearch.getText().trim();
        //-SQL for learning packages information
        String sqlSearchLearningPackages = "SELECT pack_ID, Name, archived, title "
                + "FROM LearningPackages JOIN Quiz ON (LearningPackages.quiz_ID = Quiz.quiz_ID) "
                + "WHERE Name LIKE '%" + searchParam + "%'";
        //SQL for quiz information
        String sqlSearchQuizzes = "SELECT quiz_ID, title "
                + "FROM Quiz "
                + "WHERE title LIKE '%" + searchParam + "%'";
        //SQL for teachers information
        String sqlSearchTeachers = "SELECT LoginCredentials.user_ID, FName || ' ' || LName AS Name, Email "
                + "FROM LoginCredentials JOIN UserDetails ON (LoginCredentials.user_ID = UserDetails.user_ID) "
                + "WHERE Name LIKE '%" + searchParam + "%' "
                + "AND UserLevel = 'TEACHER'";

        try {
            //-Get connection to Database
            Connection conn = DBConnectA.connectToDB();
            Statement stmt = conn.createStatement();

            //--Populate the search window with desired category information
            if (chosenOption == 0) { //Learning Packages
                ResultSet searchResults = stmt.executeQuery(sqlSearchLearningPackages);

                LearningPackagesPanel PanelLearningPackages = new LearningPackagesPanel(searchResults);
                getContentPane().add(PanelLearningPackages);

                scrollPane.setViewportView(PanelLearningPackages);
            } else if (chosenOption == 1) { //Quizzes
                ResultSet searchResults = stmt.executeQuery(sqlSearchQuizzes);

                QuizzesPanel PanelQuizzes = new QuizzesPanel(searchResults);
                getContentPane().add(PanelQuizzes);

                scrollPane.setViewportView(PanelQuizzes);
            } else { //Teachers
                ResultSet searchResults = stmt.executeQuery(sqlSearchTeachers);

                TeachersPanel PanelTeachers = new TeachersPanel(conn, searchResults);
                getContentPane().add(PanelTeachers);
                scrollPane.setViewportView(PanelTeachers);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //--Display 'No Results Found.' if no search results
        if (numResults == 0) {
            lblNoResults.setText("No Results Found.");
            lblNoResults.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            scrollPane.setViewportView(lblNoResults);
        }
    }

    /**
     * Increment the number of database results
     */
    public static void incrementResultCount() {
        numResults = numResults + 1;
    }

    /**
     * Populate the top panel with the logged-in users details
     */
    private void displayUserDetails() {
        lblUserDetails.setText(name + " (" + Integer.toString(userID) + ")");
        lblUserLevel.setText("ADMIN");
    }

    /**
     * Action-event when user clicks logout
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
     * Action-event when the user changes the list option
     *
     * @param evt
     */
    private void cmbListOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbListOptionActionPerformed
        //--Clear the user input and display the entire category information
        inputSearch.setText(" ");
        search();
    }//GEN-LAST:event_cmbListOptionActionPerformed

    /**
     * Action-event when the user hits enter on their keyboard instead of
     * clicking search
     *
     * @param evt
     */
    private void inputSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyPressed
        //--The user has hit enter instead of clicking search
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }//GEN-LAST:event_inputSearchKeyPressed

    /**
     * Action-event when the user clicks search
     *
     * @param evt
     */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        //-Populate the search window with desired information
        search();
    }//GEN-LAST:event_btnSearchActionPerformed

    /**
     * Action-event when the user clicks back
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
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cmbListOption;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblList;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNoResults;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblUserDetails;
    private javax.swing.JLabel lblUserLevel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
