/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author gebak_000
 */
public class Luggage extends javax.swing.JPanel {

    /**
     * Creates new form Luggage
     */
    public Luggage() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        LUGGAGE_TABLE = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        CONNECT_TO_CUSTOMER_BUTTON = new javax.swing.JButton();
        EDIT_BUTTON = new javax.swing.JButton();
        DELETE_BUTTON = new javax.swing.JButton();

        LUGGAGE_TABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Luggage Description", "Storage Location"
            }
        ));
        LUGGAGE_TABLE.setName("Luggage"); // NOI18N
        jScrollPane1.setViewportView(LUGGAGE_TABLE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        CONNECT_TO_CUSTOMER_BUTTON.setText("Connect to Customer");
        jToolBar1.add(CONNECT_TO_CUSTOMER_BUTTON);

        EDIT_BUTTON.setText("Edit");
        jToolBar1.add(EDIT_BUTTON);

        DELETE_BUTTON.setText("Delete");
        jToolBar1.add(DELETE_BUTTON);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CONNECT_TO_CUSTOMER_BUTTON;
    private javax.swing.JButton DELETE_BUTTON;
    private javax.swing.JButton EDIT_BUTTON;
    private javax.swing.JTable LUGGAGE_TABLE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
