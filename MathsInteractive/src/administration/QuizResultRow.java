//Author: Ian Hamilton - 15003706
package administration;

import javax.swing.JPanel;

/**
 *
 * @author Ian Hamilton - 15003706
 */
public class QuizResultRow extends JPanel {

    //--Variables
    private final javax.swing.JPanel jPanel1;
    private final javax.swing.JLabel lblName;
    private final javax.swing.JLabel lblQID;

    private final String quizID, quizTitle;

    /**
     * Constructor - Creates and populates fields with quiz information
     * @param quiz_ID
     * @param quizTitle 
     */
    public QuizResultRow(int quiz_ID, String quizTitle) {
        this.quizID = Integer.toString(quiz_ID);
        this.quizTitle = quizTitle;

        //--Initialise Panel Contents
        jPanel1 = new javax.swing.JPanel();
        lblQID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();

        //--Main Panel
        setPreferredSize(new java.awt.Dimension(436, 60));
        jPanel1.setBackground(new java.awt.Color(179, 218, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(436, 60));

        lblQID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblQID.setForeground(new java.awt.Color(5, 24, 42));
        lblQID.setText(this.quizID);

        lblName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(5, 24, 42));
        lblName.setText(this.quizTitle);
        lblName.setPreferredSize(new java.awt.Dimension(320, 22));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblQID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblQID))
                        .addContainerGap(19, Short.MAX_VALUE))
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
