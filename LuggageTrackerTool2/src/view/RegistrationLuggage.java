package view;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import main.CustomException;

/**
 *
 * @author Haris, Tomas Slaman
 */
public class RegistrationLuggage extends javax.swing.JPanel {

    /**
     * Creates new form RegistrationLuggage
     */
    public RegistrationLuggage() {
        initComponents();
        //setBounds(new Rectangle(0,0,1023,826));
    }

    public model.Luggage createLuggage() throws CustomException {
        String description = DESCRIPTION_INPUT1.getText();
        String storageLocation = STORAGE_LOCATION_INPUT.getSelectedItem().toString();
        String differentLocation = ANDERS_INPUT.getText();
        String luggageLabel = LUGGAGEID_INPUT.getText();
        String status = STATUS_COMBOBOX.getSelectedItem().toString();

        if (luggageLabel.length() > 50) {
            throw new CustomException("Luggage Label length is too long, 50 characters maximum. You've got: " + luggageLabel.length(), this.LUGGAGEID_INPUT);
        }
        if (luggageLabel.equals("")) {
            luggageLabel = null;
        }
        if (description.length() == 0) {
            throw new CustomException("A luggage description is required!", this.DESCRIPTION_INPUT1);
        }
        if (description.length() > 200) {
            throw new CustomException("Description is too long, 200 characters maximum. You've got: " + description.length(), this.DESCRIPTION_INPUT1);
        }
        if (differentLocation.length() > 200) {
            throw new CustomException("Other field has too much characters, 200 maximum. You've got: " + differentLocation.length(), this.ANDERS_INPUT);
        }
        if (storageLocation.equals("Other") && differentLocation.length() == 0)
            throw new CustomException("Other field is required! Please clarify the location the luggage is being held.", this.ANDERS_INPUT);
        if (!storageLocation.equals("Other")) {
            differentLocation = null;
        }
        if (storageLocation.length() > 45) {
            throw new CustomException("Storage length is too long, 45 characters maximum. You've got: " + storageLocation.length(), this.STORAGE_LOCATION_INPUT);
        }
        if (status.length() > 20) {
            throw new CustomException("Status length is too long, 20 characters maximum. You've got: " + status.length(), this.STATUS_COMBOBOX);
        }

        model.Luggage luggage = new model.Luggage();
        luggage.setLuggageLabel(luggageLabel);
        luggage.setDescription(description);
        luggage.setStoragelocation(storageLocation);
        luggage.setDifferentLocation(differentLocation);
        luggage.setLuggagestatus(status);
        luggage.setPassenger(null);
        return luggage;
    }

    private void clearFields() {
        LUGGAGEID_INPUT.setText("");
        DESCRIPTION_INPUT1.setText("");
        STORAGE_LOCATION_INPUT.setSelectedIndex(0);
        STATUS_COMBOBOX.setSelectedIndex(0);
        ANDERS_INPUT.setText("");
    }
    
    public void setBackgroundColor(Color c)
    {
        this.STATUS_COMBOBOX.setBackground(c);
        this.STORAGE_LOCATION_INPUT.setBackground(c);
        if (STORAGE_LOCATION_INPUT.getSelectedItem().toString().equals("Other"))
            this.ANDERS_INPUT.setBackground(c);
        else
            this.ANDERS_INPUT.setBackground(Color.LIGHT_GRAY);
        this.DESCRIPTION_INPUT1.setBackground(c);
        this.LUGGAGEID_INPUT.setBackground(c);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DESCRIPTION = new javax.swing.JLabel();
        DESCRIPTION_INPUT_FRAME = new javax.swing.JScrollPane();
        DESCRIPTION_INPUT1 = new javax.swing.JTextArea();
        STORAGE_LOCATION = new javax.swing.JLabel();
        STORAGE_LOCATION_INPUT = new javax.swing.JComboBox();
        ANDERS = new javax.swing.JLabel();
        ANDERS_INPUT_FRAME = new javax.swing.JScrollPane();
        ANDERS_INPUT = new javax.swing.JTextArea();
        SUBMIT_BUTTON = new javax.swing.JButton();
        LUGGAGEID_LABEL = new javax.swing.JLabel();
        LUGGAGEID_INPUT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        STATUS_COMBOBOX = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Register Luggage"));
        setMaximumSize(new java.awt.Dimension(500, 279));
        setMinimumSize(new java.awt.Dimension(500, 279));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 280));

        DESCRIPTION.setText("Description");

        DESCRIPTION_INPUT1.setColumns(20);
        DESCRIPTION_INPUT1.setRows(5);
        DESCRIPTION_INPUT_FRAME.setViewportView(DESCRIPTION_INPUT1);

        STORAGE_LOCATION.setText("Storage Location");

        STORAGE_LOCATION_INPUT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Storage A", "Storage B", "Storage C", "Other" }));
        STORAGE_LOCATION_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                STORAGE_LOCATION_INPUTActionPerformed(evt);
            }
        });

        ANDERS.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        ANDERS.setText("Other");

        ANDERS_INPUT.setBackground(java.awt.Color.lightGray);
        ANDERS_INPUT.setColumns(20);
        ANDERS_INPUT.setRows(5);
        ANDERS_INPUT.setEnabled(false);
        ANDERS_INPUT_FRAME.setViewportView(ANDERS_INPUT);

        SUBMIT_BUTTON.setText("Submit");
        SUBMIT_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SUBMIT_BUTTONActionPerformed(evt);
            }
        });

        LUGGAGEID_LABEL.setText("Label Number");

        LUGGAGEID_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LUGGAGEID_INPUTActionPerformed(evt);
            }
        });

        jLabel1.setText("Status");

        STATUS_COMBOBOX.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Missing", "Found", "Returned", "Destroyed" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(STORAGE_LOCATION)
                    .addComponent(DESCRIPTION)
                    .addComponent(LUGGAGEID_LABEL)
                    .addComponent(ANDERS, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SUBMIT_BUTTON)
                    .addComponent(STATUS_COMBOBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ANDERS_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(DESCRIPTION_INPUT_FRAME, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                        .addComponent(STORAGE_LOCATION_INPUT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LUGGAGEID_INPUT)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LUGGAGEID_LABEL)
                    .addComponent(LUGGAGEID_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DESCRIPTION_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(STORAGE_LOCATION_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DESCRIPTION)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(STORAGE_LOCATION)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ANDERS_INPUT_FRAME, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ANDERS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(STATUS_COMBOBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addComponent(SUBMIT_BUTTON)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void STORAGE_LOCATION_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_STORAGE_LOCATION_INPUTActionPerformed
        if (STORAGE_LOCATION_INPUT.getSelectedItem().toString().equals("Other")) {
            ANDERS_INPUT.setEnabled(true);
            ANDERS_INPUT.setBackground(Color.WHITE);
        } else if (ANDERS_INPUT.isEditable()) {
            ANDERS_INPUT.setEnabled(false);
            ANDERS_INPUT.setBackground(Color.lightGray);
        }
    }//GEN-LAST:event_STORAGE_LOCATION_INPUTActionPerformed

    private void SUBMIT_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SUBMIT_BUTTONActionPerformed
        try {
            this.setBackgroundColor(Color.WHITE);
            model.Luggage luggage = createLuggage();

            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to register new luggage?", "Question",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                model.LuggageDAO.create(luggage);
                main.LuggageTrackerTool2.getInstance().getMainMenu().getLuggageTab().refresh();
                main.LuggageTrackerTool2.getInstance().getMainMenu().getjTabbedPane().setSelectedIndex(1);
                clearFields();
            }

        } catch (SQLException ex) {
            System.err.println("Error submitting luggage " + ex.getMessage());
        } catch (CustomException cEx) {
            JOptionPane.showMessageDialog(null, cEx.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            if (cEx.getComponent() != null) {
                cEx.getComponent().setBackground(Color.RED);
            }
        }
    }//GEN-LAST:event_SUBMIT_BUTTONActionPerformed

    private void LUGGAGEID_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LUGGAGEID_INPUTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LUGGAGEID_INPUTActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ANDERS;
    private javax.swing.JTextArea ANDERS_INPUT;
    private javax.swing.JScrollPane ANDERS_INPUT_FRAME;
    private javax.swing.JLabel DESCRIPTION;
    private javax.swing.JTextArea DESCRIPTION_INPUT1;
    private javax.swing.JScrollPane DESCRIPTION_INPUT_FRAME;
    private javax.swing.JTextField LUGGAGEID_INPUT;
    private javax.swing.JLabel LUGGAGEID_LABEL;
    private javax.swing.JComboBox STATUS_COMBOBOX;
    private javax.swing.JLabel STORAGE_LOCATION;
    private javax.swing.JComboBox STORAGE_LOCATION_INPUT;
    private javax.swing.JButton SUBMIT_BUTTON;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
