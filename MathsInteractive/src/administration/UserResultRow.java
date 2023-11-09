//Author: Ian Hamilton - 15003706
package administration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 * @author Ian Hamilton - 15003706
 * UserResultRow is a JPanel type that creates and populates a JPanel with information
 */
public class UserResultRow extends JPanel {

    //--Variables
    private final javax.swing.JPanel jPanel1;
    private final javax.swing.JLabel lblEmail;
    private final javax.swing.JLabel lblName;
    private final javax.swing.JLabel lblSuspended;
    private final javax.swing.JLabel lblUID;
    private final javax.swing.JLabel lblUserLevel;

    private final String userID, Name, Email, SuspendedUntilString, UserLevel;
    private Date suspendedUntil;

    /**
     * Event-handler for when a user clicks a panel
     * @param evt 
     */
    private void jPanelMouseClicked(java.awt.event.MouseEvent evt) {
        //-Update the currently selected row
        ManageUsers.updateSelected(this.userID);
    }

    /**
     * Checks if the user is suspended
     * @param suspendedUntil Date corresponding to suspension
     * @return true if user is suspended
     */
    private boolean isUserSuspended(Date suspendedUntil) {
        boolean isUserSupended = true;
        Date today = new Date();

        if (suspendedUntil.before(today)) { //Suspension date has passed
            isUserSupended = false;
        }

        return isUserSupended;
    }

    /**
     * Constructor
     * @param user_ID
     * @param Name
     * @param Email
     * @param SuspendedUntilString
     * @param UserLevel 
     */
    public UserResultRow(int user_ID, String Name, String Email, String SuspendedUntilString, String UserLevel) {
        this.userID = Integer.toString(user_ID);
        this.Name = Name;
        this.Email = Email;
        this.SuspendedUntilString = SuspendedUntilString;
        this.UserLevel = UserLevel;
        try {
            suspendedUntil = new SimpleDateFormat("yyyy-MM-dd").parse(SuspendedUntilString);
        } catch (ParseException ex) {
            System.out.println("Parse Date error in ResultRow.java");
        }
        Boolean isSuspended = isUserSuspended(suspendedUntil);

        //--- Initialise Panel Contents
        jPanel1 = new javax.swing.JPanel();
        lblUID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblSuspended = new javax.swing.JLabel();
        lblUserLevel = new javax.swing.JLabel();

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelMouseClicked(evt);
            }
        });

        //---Main Panel
        Border border = BorderFactory.createLineBorder(java.awt.Color.BLACK, 1);
        jPanel1.setBackground(new java.awt.Color(179, 218, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(436, 60));
        jPanel1.setBorder(border);

        lblUID.setFont(new java.awt.Font("Tahoma", 0, 18));
        lblUID.setForeground(new java.awt.Color(5, 24, 42));
        lblUID.setText(this.userID);

        lblName.setFont(new java.awt.Font("Tahoma", 0, 18));
        lblName.setForeground(new java.awt.Color(5, 24, 42));
        lblName.setText(this.Name);

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblEmail.setForeground(new java.awt.Color(5, 24, 42));
        lblEmail.setText(this.Email);

        lblSuspended.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblSuspended.setForeground(new java.awt.Color(5, 24, 42));
        if (isSuspended == true){
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = newFormat.format(this.suspendedUntil);
            
            lblSuspended.setText("Suspended: " + formattedDate);
        } else {
            lblSuspended.setText("Not Suspended");
        }
        

        lblUserLevel.setFont(new java.awt.Font("Tahoma", 0, 18));
        lblUserLevel.setForeground(new java.awt.Color(5, 24, 42));
        lblUserLevel.setText(this.UserLevel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSuspended)
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSuspended))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblName)
                                .addComponent(lblUID)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
          );
    }
}
