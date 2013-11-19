/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Haris
 */
public class RegistrationLuggage extends javax.swing.JPanel {

    /**
     * Creates new form RegistrationLuggage
     */
    public RegistrationLuggage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LUGGAGE_ID = new javax.swing.JLabel();
        LUGGAGE_ID_INPUT = new javax.swing.JTextField();
        DESCRIPTION = new javax.swing.JLabel();
        DESCRIPTION_INPUT_FRAME = new javax.swing.JScrollPane();
        DESCRIPTION_INPUT1 = new javax.swing.JTextArea();
        STORAGE_LOCATION = new javax.swing.JLabel();
        STORAGE_LOCATION_INPUT = new javax.swing.JComboBox();
        PRINT_ON_REGISTER_INPUT = new javax.swing.JCheckBox();
        ANDERS = new javax.swing.JLabel();
        ANDERS_INPUT_FRAME = new javax.swing.JScrollPane();
        ANDERS_INPUT = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Register Luggage"));
        setMaximumSize(new java.awt.Dimension(523, 279));
        setMinimumSize(new java.awt.Dimension(523, 279));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(523, 279));

        LUGGAGE_ID.setText("Luggage ID");

        DESCRIPTION.setText("Description");

        DESCRIPTION_INPUT1.setColumns(20);
        DESCRIPTION_INPUT1.setRows(5);
        DESCRIPTION_INPUT_FRAME.setViewportView(DESCRIPTION_INPUT1);

        STORAGE_LOCATION.setText("Storage Location");

        STORAGE_LOCATION_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Opslag A", "Opslag B", "Opslag C", "Anders .. *" }));
        STORAGE_LOCATION_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                STORAGE_LOCATION_INPUTActionPerformed(evt);
            }
        });

        PRINT_ON_REGISTER_INPUT.setText("Print on register");

        ANDERS.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        ANDERS.setText("Anders *");

        ANDERS_INPUT.setColumns(20);
        ANDERS_INPUT.setRows(5);
        ANDERS_INPUT.setText("Vul hier zo specifiek mogelijk de\ngegevens in van de opslagplaats.");
        ANDERS_INPUT_FRAME.setViewportView(ANDERS_INPUT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(STORAGE_LOCATION)
                            .addComponent(DESCRIPTION)
                            .addComponent(LUGGAGE_ID))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LUGGAGE_ID_INPUT)
                            .addComponent(DESCRIPTION_INPUT_FRAME, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(STORAGE_LOCATION_INPUT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PRINT_ON_REGISTER_INPUT)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ANDERS)))
                        .addGap(18, 18, 18)
                        .addComponent(ANDERS_INPUT_FRAME, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LUGGAGE_ID)
                    .addComponent(LUGGAGE_ID_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DESCRIPTION_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(STORAGE_LOCATION_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DESCRIPTION)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(STORAGE_LOCATION)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ANDERS_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ANDERS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PRINT_ON_REGISTER_INPUT)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void STORAGE_LOCATION_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_STORAGE_LOCATION_INPUTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_STORAGE_LOCATION_INPUTActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ANDERS;
    private javax.swing.JTextArea ANDERS_INPUT;
    private javax.swing.JScrollPane ANDERS_INPUT_FRAME;
    private javax.swing.JLabel DESCRIPTION;
    private javax.swing.JTextArea DESCRIPTION_INPUT1;
    private javax.swing.JScrollPane DESCRIPTION_INPUT_FRAME;
    private javax.swing.JLabel LUGGAGE_ID;
    private javax.swing.JTextField LUGGAGE_ID_INPUT;
    private javax.swing.JCheckBox PRINT_ON_REGISTER_INPUT;
    private javax.swing.JLabel STORAGE_LOCATION;
    private javax.swing.JComboBox STORAGE_LOCATION_INPUT;
    // End of variables declaration//GEN-END:variables
}
