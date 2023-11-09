//Author: Ian Hamilton - 15003706
package administration;

import javax.swing.JPanel;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class TeacherResultRow extends JPanel {

    //--Variables
    private final javax.swing.JPanel jPanel1;
    private final javax.swing.JLabel lblClasses;
    private final javax.swing.JLabel lblEmail;
    private final javax.swing.JLabel lblName;
    private final javax.swing.JLabel lblTeacherID;

    private final String userID, name, email, class1, class2, class3;

    /**
     * Constructor - Creates and populates fields with teacher's information
     * @param userID
     * @param name
     * @param email
     * @param class1
     * @param class2
     * @param class3 
     */
    public TeacherResultRow(int userID, String name, String email, int class1, int class2, int class3) {
        this.userID = Integer.toString(userID);
        this.name = name;
        this.email = email;
        this.class1 = Integer.toString(class1);
        this.class2 = Integer.toString(class2);
        this.class3 = Integer.toString(class3);

        //--Initialise Panel Contents
        jPanel1 = new javax.swing.JPanel();
        lblTeacherID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblClasses = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        
        //--Main Panel
        jPanel1.setBackground(new java.awt.Color(179, 218, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(436, 69));

        lblTeacherID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTeacherID.setForeground(new java.awt.Color(5, 24, 42));
        lblTeacherID.setText(this.userID);

        lblName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(5, 24, 42));
        lblName.setText(this.name);
        lblName.setPreferredSize(new java.awt.Dimension(320, 22));

        lblClasses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblClasses.setForeground(new java.awt.Color(5, 24, 42));
        lblClasses.setText("Classes: " + this.class1 + " " + this.class2 + " " + this.class3);
        lblClasses.setMaximumSize(new java.awt.Dimension(278, 17));
        lblClasses.setMinimumSize(new java.awt.Dimension(278, 17));
        lblClasses.setPreferredSize(new java.awt.Dimension(278, 17));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(5, 24, 42));
        lblEmail.setText(this.email);
        lblEmail.setMaximumSize(new java.awt.Dimension(278, 17));
        lblEmail.setMinimumSize(new java.awt.Dimension(278, 17));
        lblEmail.setPreferredSize(new java.awt.Dimension(278, 17));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTeacherID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClasses, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTeacherID)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }
}
