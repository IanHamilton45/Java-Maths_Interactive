//Author: Ian Hamilton - 15003706
package administration;

import javax.swing.JPanel;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class PackageResultRow extends JPanel {

    //--Variables
    private final javax.swing.JPanel jPanel1;
    private final javax.swing.JLabel lblEmail;
    private final javax.swing.JLabel lblPackID;
    private final javax.swing.JLabel lblSuspended;
    private final javax.swing.JLabel lblUserLevel;

    private final String packID, packName, quizTitle;
    private final int intArchived;

    /**
     * Constructor - Creates and populates fields with learning package information
     * @param packID
     * @param packName
     * @param isArchived
     * @param quizTitle 
     */
    public PackageResultRow(int packID, String packName, int isArchived, String quizTitle) {
        this.packID = Integer.toString(packID);
        this.packName = packName;
        this.intArchived = isArchived;
        this.quizTitle = quizTitle;

        //--Initialise Panel Contents
        jPanel1 = new javax.swing.JPanel();
        lblPackID = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblSuspended = new javax.swing.JLabel();
        lblUserLevel = new javax.swing.JLabel();

        //--Main Panel
        jPanel1.setBackground(new java.awt.Color(179, 218, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(436, 69));

        lblPackID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPackID.setForeground(new java.awt.Color(5, 24, 42));
        lblPackID.setText(this.packID);

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(5, 24, 42));
        lblEmail.setText("Pack Name: " + this.packName);
        lblEmail.setMaximumSize(new java.awt.Dimension(278, 17));
        lblEmail.setMinimumSize(new java.awt.Dimension(278, 17));
        lblEmail.setPreferredSize(new java.awt.Dimension(278, 17));

        lblSuspended.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSuspended.setForeground(new java.awt.Color(5, 24, 42));
        lblSuspended.setText("Linked Quiz: " + this.quizTitle);
        lblSuspended.setMaximumSize(new java.awt.Dimension(278, 17));
        lblSuspended.setMinimumSize(new java.awt.Dimension(278, 17));
        lblSuspended.setPreferredSize(new java.awt.Dimension(278, 17));

        lblUserLevel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserLevel.setForeground(new java.awt.Color(5, 24, 42));
        lblUserLevel.setText(" ");
        if (this.intArchived == 1){
            lblUserLevel.setText("ARCHIVED");
        }
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPackID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSuspended, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUserLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSuspended, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblPackID)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblUserLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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
