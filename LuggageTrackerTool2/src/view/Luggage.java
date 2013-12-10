/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gebak_000
 */
public class Luggage extends javax.swing.JPanel {

    private int selection;

    private java.util.List<model.Luggage> luggageList = model.Luggage.getLuggageList();

    /**
     * Creates new form Luggage
     */
    public Luggage() {
        initComponents();
        LUGGAGE_TABLE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        refreshLuggageList();
        addLuggageItemsToTable();
    }
    
    public void refresh() {
        removeAllFromLuggageTable();
        refreshLuggageList();
        addLuggageItemsToTable();
    }

    public void refreshLuggageList() {
        try {
            luggageList = model.LuggageDAO.readAll();
        } catch (SQLException ex) {
            System.err.println("Error getting luggage list: " + ex.getMessage());
        }
    }

    public void refreshWithSearch(int id) throws SQLException {
        removeAllFromLuggageTable();
        luggageList.clear();
        luggageList.add(model.LuggageDAO.readById(id));
    }

    public void addLuggageItemsToTable() {

        for (model.Luggage luggage : luggageList) {
            addLuggageToTable(luggage);
        }
    }

    private void addRow(Object[] row) {
        ((javax.swing.table.DefaultTableModel) LUGGAGE_TABLE.getModel()).addRow(row);
    }

    public void addLuggageToTable(model.Luggage luggage) {
        Object[] newRow = new Object[4];
        newRow[0] = luggage.getLuggageid();
        newRow[1] = luggage.getDescription();
        newRow[2] = luggage.getStoragelocation();
        newRow[3] = luggage.getPassengerid();
        addRow(newRow);
    }

    public model.Luggage getLuggageFromRow(int row) {
        model.Luggage luggage = null;
        int selectedid = Integer.parseInt(LUGGAGE_TABLE.getValueAt(row, 0).toString());
        try {
            System.out.println(selectedid);
            luggage = model.LuggageDAO.readById(selectedid);
        } catch (SQLException ex) {
            System.err.println("Get luggage from table not found.");
        }
        return luggage;
    }

    public void removeAllFromLuggageTable() {
        DefaultTableModel dtm = (DefaultTableModel) LUGGAGE_TABLE.getModel();
        int rows = dtm.getRowCount();

        for (int i = rows - 1; i >= 0; i--) {

            dtm.removeRow(i);
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
        LUGGAGE_TABLE = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        REFRESH_BUTTON = new javax.swing.JButton();

        LUGGAGE_TABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Luggage Description", "Storage Location", "PassengerID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        LUGGAGE_TABLE.setColumnSelectionAllowed(true);
        LUGGAGE_TABLE.setName("Luggage"); // NOI18N
        LUGGAGE_TABLE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        LUGGAGE_TABLE.getTableHeader().setReorderingAllowed(false);
        LUGGAGE_TABLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LUGGAGE_TABLEMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LUGGAGE_TABLEMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(LUGGAGE_TABLE);
        LUGGAGE_TABLE.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (LUGGAGE_TABLE.getColumnModel().getColumnCount() > 0) {
            LUGGAGE_TABLE.getColumnModel().getColumn(0).setResizable(false);
            LUGGAGE_TABLE.getColumnModel().getColumn(0).setPreferredWidth(40);
        }

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        REFRESH_BUTTON.setText("Refresh Table");
        REFRESH_BUTTON.setFocusable(false);
        REFRESH_BUTTON.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        REFRESH_BUTTON.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        REFRESH_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REFRESH_BUTTONActionPerformed(evt);
            }
        });
        jToolBar1.add(REFRESH_BUTTON);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void LUGGAGE_TABLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LUGGAGE_TABLEMouseClicked
//        JTable jtable = (JTable) evt.getSource();
//        selection = jtable.getSelectedRow();
//        System.out.println(selection);
//        main.LuggageTrackerTool2.getInstance().updateLuggageInformationPanel(selection);

    }//GEN-LAST:event_LUGGAGE_TABLEMouseClicked

    private void REFRESH_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REFRESH_BUTTONActionPerformed
        refresh();
    }//GEN-LAST:event_REFRESH_BUTTONActionPerformed

    private void LUGGAGE_TABLEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LUGGAGE_TABLEMousePressed
        JTable jtable = (JTable) evt.getSource();
        int row = jtable.getSelectedRow();
        selection = Integer.parseInt(jtable.getValueAt(row, 0).toString());
        System.out.println(selection);
        main.LuggageTrackerTool2.getInstance().updateLuggageInformationPanel(selection);
    }//GEN-LAST:event_LUGGAGE_TABLEMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable LUGGAGE_TABLE;
    private javax.swing.JButton REFRESH_BUTTON;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}
