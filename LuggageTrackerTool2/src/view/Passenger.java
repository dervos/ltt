/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import model.PassengerDAO;

/**
 *
 * @author gebak_000
 */
public class Passenger extends javax.swing.JPanel {
    private java.util.List<model.Passenger> passengerList;
    private model.PassengerDAO passengerDAO;
    
    /**
     * Creates new form Passenger
     */
    public Passenger() {
        this.passengerDAO = new PassengerDAO();
        
        initComponents();
        addPassengerItems();
    }
    
    public void refreshPassengerList() throws SQLException {
        passengerList = passengerDAO.readAll();
        
    }
    
    public void addPassengerItems() {
        try {
            refreshPassengerList();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        for (int i = 0; i < passengerList.size(); i++) {
            Object[] newRow = new Object[9];
            newRow[0] = passengerList.get(i).getSurname();
            newRow[1] = passengerList.get(i).getInsertion();
            newRow[2] = passengerList.get(i).getName();
            newRow[3] = passengerList.get(i).getGender();
            newRow[4] = passengerList.get(i).getDob();
            newRow[5] = passengerList.get(i).getMobphone();
            newRow[6] = passengerList.get(i).getHomephone();
            newRow[7] = passengerList.get(i).getHomeaddressid();
            newRow[8] = passengerList.get(i).getTempaddressid();
            ((javax.swing.table.DefaultTableModel) PASSENGER_TABLE.getModel()).addRow(newRow);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        PASSENGER_TABLE = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        CONNECT_TO_LUGGAGE_BUTTON = new javax.swing.JButton();
        EDIT_BUTTON = new javax.swing.JButton();
        DELETE_BUTTON = new javax.swing.JButton();

        PASSENGER_TABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Surname", "", "Name", "Gender", "DoB", "Mob", "Tel", "Home", "Temp"
            }
        ));
        jScrollPane1.setViewportView(PASSENGER_TABLE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        CONNECT_TO_LUGGAGE_BUTTON.setText("Connect to Luggage");
        CONNECT_TO_LUGGAGE_BUTTON.setFocusable(false);
        CONNECT_TO_LUGGAGE_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CONNECT_TO_LUGGAGE_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(CONNECT_TO_LUGGAGE_BUTTON);

        EDIT_BUTTON.setText("Edit");
        EDIT_BUTTON.setFocusable(false);
        EDIT_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EDIT_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(EDIT_BUTTON);

        DELETE_BUTTON.setText("Delete");
        DELETE_BUTTON.setFocusable(false);
        DELETE_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DELETE_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(DELETE_BUTTON);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CONNECT_TO_LUGGAGE_BUTTON;
    private javax.swing.JButton DELETE_BUTTON;
    private javax.swing.JButton EDIT_BUTTON;
    private javax.swing.JTable PASSENGER_TABLE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
