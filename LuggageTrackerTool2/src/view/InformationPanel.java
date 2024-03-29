/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.CustomException;
import main.LuggageTrackerTool2;
import model.Luggage;
import model.PassengerDAO;

/**
 *
 * @author reintjehard, Tomas Slaman
 */
public class InformationPanel extends javax.swing.JPanel {

    private LuggageTrackerTool2 instance;

    /**
     * Creates new form InformationPanel
     */
    public InformationPanel() {
        initComponents();
        instance = LuggageTrackerTool2.getInstance();
    }

    /**
     * Sets luggage information in the panel.
     * @param l 
     */
    public void setLuggageLabel(Luggage l) {
        String location = l.getStoragelocation();
        if (location == null) {
            location = l.getDifferentLocation();
        }

        LUGGAGE_ID_LABEL.setText(l.getLuggageLabel());
        DESCRIPTION_LABEL.setText(l.getDescription());
        STATUS_LABEL.setText(l.getLuggagestatus().name());
        STORAGE_LABEL.setText(location);
    }

    /**
     * Removes the luggage information on panel
     */
    public void clearLuggageLabels() {
        LUGGAGE_ID_LABEL.setText("");
        DESCRIPTION_LABEL.setText("");
        STATUS_LABEL.setText("");
        STORAGE_LABEL.setText("");
    }

    /**
     * Sets passenger information on panel
     * @param p 
     */
    public void setPassengerLabel(model.Passenger p) {
        ID_LABEL.setText(p.getPassengerid().toString());
        NAME_LABEL.setText(p.getName() + " " + p.getInsertion() + " " + p.getSurname());
        DOB_LABEL.setText(p.getDob().toString());
        ADDRESS_LABEL.setText(p.getHomeaddress().getStreetname());
        TEMP_ADDRESS_LABEL.setText(p.getTempaddress().getStreetname());
    }

    /**
     * Removes passenger information on panel.
     */
    public void clearPassengerLabels() {
        ID_LABEL.setText("");
        NAME_LABEL.setText("");
        DOB_LABEL.setText("");
        ADDRESS_LABEL.setText("");
        TEMP_ADDRESS_LABEL.setText("");
    }

    /**
     * Connect luggage to passenger button triggers this on click.
     */
    private void connectButton() {
        try {
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to connect the selected passenger and luggage?", "Question",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {

                instance.connectLuggageToPassenger();
                instance.getMainMenu().getLuggageTab().refresh();
            }
        } catch (CustomException cEx) {
            JOptionPane.showMessageDialog(null, cEx.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        passengerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ID_LABEL = new javax.swing.JLabel();
        NAME_LABEL = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        DOB_LABEL = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ADDRESS_LABEL = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TEMP_ADDRESS_LABEL = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        CONNECT_TO_LUGGAGE_BUTTON = new javax.swing.JButton();
        PASSENGER_EDIT_BUTTON = new javax.swing.JButton();
        PASSENGER_DELETE_BUTTON = new javax.swing.JButton();
        luggagePanel = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        CONNECT_TO_PASSENGER_BUTTON = new javax.swing.JButton();
        EDIT_LUGGAGE_BUTTON = new javax.swing.JButton();
        LUGGAGE_DELETE_BUTTON = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LUGGAGE_ID_LABEL = new javax.swing.JLabel();
        DESCRIPTION_LABEL = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        STATUS_LABEL = new javax.swing.JLabel();
        STORAGE_LABEL = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 223, 162));
        setPreferredSize(new java.awt.Dimension(800, 150));

        passengerPanel.setBackground(new java.awt.Color(254, 223, 162));
        passengerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Passenger"));
        passengerPanel.setPreferredSize(new java.awt.Dimension(600, 200));

        jLabel1.setText("ID:");

        jLabel4.setText("Name:");

        jLabel6.setText("Date of Birth:");

        jLabel8.setText("Address:");

        jLabel2.setText("Temp Address:");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        CONNECT_TO_LUGGAGE_BUTTON.setText("Connect to Luggage");
        CONNECT_TO_LUGGAGE_BUTTON.setFocusable(false);
        CONNECT_TO_LUGGAGE_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CONNECT_TO_LUGGAGE_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CONNECT_TO_LUGGAGE_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONNECT_TO_LUGGAGE_BUTTONActionPerformed(evt);
            }
        });
        jToolBar1.add(CONNECT_TO_LUGGAGE_BUTTON);

        PASSENGER_EDIT_BUTTON.setText("Edit");
        PASSENGER_EDIT_BUTTON.setFocusable(false);
        PASSENGER_EDIT_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PASSENGER_EDIT_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PASSENGER_EDIT_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PASSENGER_EDIT_BUTTONActionPerformed(evt);
            }
        });
        jToolBar1.add(PASSENGER_EDIT_BUTTON);

        PASSENGER_DELETE_BUTTON.setText("Delete");
        PASSENGER_DELETE_BUTTON.setFocusable(false);
        PASSENGER_DELETE_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PASSENGER_DELETE_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PASSENGER_DELETE_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PASSENGER_DELETE_BUTTONActionPerformed(evt);
            }
        });
        jToolBar1.add(PASSENGER_DELETE_BUTTON);

        javax.swing.GroupLayout passengerPanelLayout = new javax.swing.GroupLayout(passengerPanel);
        passengerPanel.setLayout(passengerPanelLayout);
        passengerPanelLayout.setHorizontalGroup(
            passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passengerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(passengerPanelLayout.createSequentialGroup()
                        .addGroup(passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(49, 49, 49)
                        .addGroup(passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NAME_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DOB_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ADDRESS_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ID_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TEMP_ADDRESS_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(passengerPanelLayout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 271, Short.MAX_VALUE)))
                .addContainerGap())
        );
        passengerPanelLayout.setVerticalGroup(
            passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passengerPanelLayout.createSequentialGroup()
                .addGroup(passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ID_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NAME_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DOB_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ADDRESS_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(passengerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TEMP_ADDRESS_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        luggagePanel.setBackground(new java.awt.Color(254, 223, 162));
        luggagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Luggage"));
        luggagePanel.setPreferredSize(new java.awt.Dimension(300, 300));

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        CONNECT_TO_PASSENGER_BUTTON.setText("Connect to Passenger");
        CONNECT_TO_PASSENGER_BUTTON.setFocusable(false);
        CONNECT_TO_PASSENGER_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CONNECT_TO_PASSENGER_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CONNECT_TO_PASSENGER_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONNECT_TO_PASSENGER_BUTTONActionPerformed(evt);
            }
        });
        jToolBar2.add(CONNECT_TO_PASSENGER_BUTTON);

        EDIT_LUGGAGE_BUTTON.setText("Edit");
        EDIT_LUGGAGE_BUTTON.setFocusable(false);
        EDIT_LUGGAGE_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EDIT_LUGGAGE_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EDIT_LUGGAGE_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDIT_LUGGAGE_BUTTONActionPerformed(evt);
            }
        });
        jToolBar2.add(EDIT_LUGGAGE_BUTTON);

        LUGGAGE_DELETE_BUTTON.setText("Delete");
        LUGGAGE_DELETE_BUTTON.setFocusable(false);
        LUGGAGE_DELETE_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LUGGAGE_DELETE_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        LUGGAGE_DELETE_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LUGGAGE_DELETE_BUTTONActionPerformed(evt);
            }
        });
        jToolBar2.add(LUGGAGE_DELETE_BUTTON);

        jLabel3.setText("Label Number");

        jLabel5.setText("Description");

        jLabel10.setText("Status");

        jLabel11.setText("Storage");

        javax.swing.GroupLayout luggagePanelLayout = new javax.swing.GroupLayout(luggagePanel);
        luggagePanel.setLayout(luggagePanelLayout);
        luggagePanelLayout.setHorizontalGroup(
            luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(luggagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(luggagePanelLayout.createSequentialGroup()
                        .addGroup(luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(26, 26, 26)
                        .addGroup(luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LUGGAGE_ID_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DESCRIPTION_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(STATUS_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(STORAGE_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(luggagePanelLayout.createSequentialGroup()
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 54, Short.MAX_VALUE)))
                .addContainerGap())
        );
        luggagePanelLayout.setVerticalGroup(
            luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, luggagePanelLayout.createSequentialGroup()
                .addGroup(luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(LUGGAGE_ID_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(DESCRIPTION_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(STATUS_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(luggagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(STORAGE_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passengerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(luggagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passengerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(luggagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CONNECT_TO_LUGGAGE_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONNECT_TO_LUGGAGE_BUTTONActionPerformed
        this.connectButton();
    }//GEN-LAST:event_CONNECT_TO_LUGGAGE_BUTTONActionPerformed

    private void CONNECT_TO_PASSENGER_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONNECT_TO_PASSENGER_BUTTONActionPerformed
        this.connectButton();
    }//GEN-LAST:event_CONNECT_TO_PASSENGER_BUTTONActionPerformed

    private void PASSENGER_EDIT_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PASSENGER_EDIT_BUTTONActionPerformed
        model.Passenger selectedPassenger = instance.getSelectedPassenger();
        try {
            if (selectedPassenger != null) {
                List<Luggage> connectedLuggages = model.LuggageDAO.readByPersonid(selectedPassenger.getPassengerid());
                JFrame editFrame = new JFrame("Edit: " + selectedPassenger.getName() + " " + selectedPassenger.getSurname());
                EditPanel ep = new EditPanel(editFrame);
                if (connectedLuggages.size() > 0) {
                    ep.populateComboBox(connectedLuggages);
                } else {
                    ep.setLuggageEditability(false);
                }
                editFrame.setSize(725, 480);
                editFrame.validate();
                editFrame.setResizable(false);
                ep.fillPassengerInformation(selectedPassenger);
                editFrame.getContentPane().add(ep);
                editFrame.setVisible(true);
                editFrame.setFocusable(true);
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a passenger first.", "", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_PASSENGER_EDIT_BUTTONActionPerformed

    private void PASSENGER_DELETE_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PASSENGER_DELETE_BUTTONActionPerformed
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete the selected passenger?", "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            try {
                model.Passenger pas = instance.getSelectedPassenger();
                if (pas != null) {
                    int id = pas.getPassengerid();
                    if (instance.getMainMenu().getLuggageTab().passengerIDExistsInLuggage(id)) //If passenger is connected to luggage, set the luggage ID to null.
                    {
                        model.LuggageDAO.updatePassengerIDToNull(id);
                        instance.getMainMenu().getLuggageTab().refresh();
                    }

                    model.PassengerDAO.delete(id);

                    int haid = pas.getHomeaddressid();
                    int taid = pas.getTempaddressid();
                    if (haid != 0) {
                        model.AddressDAO.delete(haid);
                    }
                    if (taid != 0 && (taid != haid)) {
                        model.AddressDAO.delete(taid);
                    }
                    instance.getMainMenu().getPassengerTab().refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "You need to select a passenger first.", "", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println("Cannot delete passenger");
            }
        }
    }//GEN-LAST:event_PASSENGER_DELETE_BUTTONActionPerformed

    private void EDIT_LUGGAGE_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDIT_LUGGAGE_BUTTONActionPerformed
        model.Luggage selectedLuggage = instance.getSelectedLuggage();
        try {
            if (selectedLuggage != null) {
                model.Passenger passenger = null;
                if (selectedLuggage.getPassengerid() != 0) {
                    passenger = PassengerDAO.readById(selectedLuggage.getPassengerid());
                }

                JFrame editFrame = new JFrame("Edit");
                EditPanel ep = new EditPanel(editFrame);
                editFrame.setSize(725, 480);
                editFrame.setResizable(false);
                ep.fillLuggageInformation(selectedLuggage);
                if (selectedLuggage.getPassengerid() != 0) {
                    ep.fillPassengerInformation(passenger);
                } else {
                    ep.setPassengerEditability(false);
                }
                editFrame.getContentPane().add(ep);
                editFrame.setVisible(true);
                editFrame.setFocusable(true);
            } else {
                JOptionPane.showMessageDialog(null, "You need to select luggage first.", "", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_EDIT_LUGGAGE_BUTTONActionPerformed

    private void LUGGAGE_DELETE_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LUGGAGE_DELETE_BUTTONActionPerformed
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete the selected passenger?", "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            try {
                model.LuggageDAO.delete(instance.getSelectedLuggage().getLuggageid());
                instance.getMainMenu().getLuggageTab().refresh();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println("Cannot delete luggage");
            }
        }

    }//GEN-LAST:event_LUGGAGE_DELETE_BUTTONActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ADDRESS_LABEL;
    private javax.swing.JButton CONNECT_TO_LUGGAGE_BUTTON;
    private javax.swing.JButton CONNECT_TO_PASSENGER_BUTTON;
    private javax.swing.JLabel DESCRIPTION_LABEL;
    private javax.swing.JLabel DOB_LABEL;
    private javax.swing.JButton EDIT_LUGGAGE_BUTTON;
    private javax.swing.JLabel ID_LABEL;
    private javax.swing.JButton LUGGAGE_DELETE_BUTTON;
    private javax.swing.JLabel LUGGAGE_ID_LABEL;
    private javax.swing.JLabel NAME_LABEL;
    private javax.swing.JButton PASSENGER_DELETE_BUTTON;
    private javax.swing.JButton PASSENGER_EDIT_BUTTON;
    private javax.swing.JLabel STATUS_LABEL;
    private javax.swing.JLabel STORAGE_LABEL;
    private javax.swing.JLabel TEMP_ADDRESS_LABEL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JPanel luggagePanel;
    private javax.swing.JPanel passengerPanel;
    // End of variables declaration//GEN-END:variables

}
