/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author gebak_000, Tomas Slaman
 */
public class AccountManagement extends javax.swing.JPanel {

    private DefaultComboBoxModel dcm;
    private List<model.User> list = new ArrayList<>();
    private String[] usernames;

    /**
     * Creates new form AccountManagement
     */
    public AccountManagement() {
        try {
            list = model.UserDAO.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        usernames = new String[list.size()];
        int index = 0;
        for (User user : list) {
            usernames[index] = user.getUsername();
            index++;
        }

        initComponents();

    }
    
    private void updateUSERModel()
    {
        USER.removeAllItems();
        
        try {
            list = model.UserDAO.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (User user : list) {
            USER.addItem(user.getUsername());
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

        jButton1 = new javax.swing.JButton();
        ACCOUNT_MANAGEMENT = new javax.swing.JPanel();
        NEW_USER = new javax.swing.JLabel();
        NEW_USERNAME = new javax.swing.JLabel();
        NEW_PASSWORD_INPUT = new javax.swing.JTextField();
        NEW_PASSWORD = new javax.swing.JLabel();
        NEW_USERNAME_INPUT = new javax.swing.JTextField();
        CHANGE_PASSWORD_TEXT = new javax.swing.JLabel();
        CHANGE_USERNAME_TEXT = new javax.swing.JLabel();
        CHANGE_PASSWORD_INPUT = new javax.swing.JTextField();
        CHANGE_PASSWORD = new javax.swing.JLabel();
        USER = new javax.swing.JComboBox();
        NEW_CONFIRM = new javax.swing.JButton();
        CHANGE_CONFIRM = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(254, 223, 162));

        ACCOUNT_MANAGEMENT.setBackground(new java.awt.Color(254, 223, 162));

        NEW_USER.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        NEW_USER.setText("New User");

        NEW_USERNAME.setText("Username:");

        NEW_PASSWORD_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NEW_PASSWORD_INPUTActionPerformed(evt);
            }
        });

        NEW_PASSWORD.setText("Password:");

        NEW_USERNAME_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NEW_USERNAME_INPUTActionPerformed(evt);
            }
        });

        CHANGE_PASSWORD_TEXT.setText("Password:");

        CHANGE_USERNAME_TEXT.setText("Username:");

        CHANGE_PASSWORD_INPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHANGE_PASSWORD_INPUTActionPerformed(evt);
            }
        });

        CHANGE_PASSWORD.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        CHANGE_PASSWORD.setText("Change Password");

        USER.setModel(new javax.swing.DefaultComboBoxModel(usernames));
        USER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USERActionPerformed(evt);
            }
        });

        NEW_CONFIRM.setText("Confirm");
        NEW_CONFIRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NEW_CONFIRMActionPerformed(evt);
            }
        });

        CHANGE_CONFIRM.setText("Confirm");
        CHANGE_CONFIRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHANGE_CONFIRMActionPerformed(evt);
            }
        });

        jLabel1.setText("Rights:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Account Manager", "Manager", "Service Employee"}));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(usernames));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Delete user");

        jLabel3.setText("Username:");

        jLabel4.setText("Verify:");

        jButton2.setText("Confirm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(254, 223, 162));
        jCheckBox1.setText("Check to confirm");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ACCOUNT_MANAGEMENTLayout = new javax.swing.GroupLayout(ACCOUNT_MANAGEMENT);
        ACCOUNT_MANAGEMENT.setLayout(ACCOUNT_MANAGEMENTLayout);
        ACCOUNT_MANAGEMENTLayout.setHorizontalGroup(
            ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCOUNT_MANAGEMENTLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jLabel4)
                    .addComponent(NEW_USER)
                    .addComponent(CHANGE_PASSWORD)
                    .addGroup(ACCOUNT_MANAGEMENTLayout.createSequentialGroup()
                        .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CHANGE_PASSWORD_TEXT)
                            .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NEW_USERNAME)
                                    .addComponent(NEW_PASSWORD))
                                .addComponent(CHANGE_USERNAME_TEXT, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(NEW_CONFIRM)
                            .addComponent(CHANGE_CONFIRM)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCheckBox1)
                            .addComponent(NEW_USERNAME_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NEW_PASSWORD_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CHANGE_PASSWORD_INPUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(USER, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        ACCOUNT_MANAGEMENTLayout.setVerticalGroup(
            ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCOUNT_MANAGEMENTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NEW_USER)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NEW_USERNAME)
                    .addComponent(NEW_USERNAME_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NEW_PASSWORD)
                    .addComponent(NEW_PASSWORD_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NEW_CONFIRM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CHANGE_PASSWORD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CHANGE_USERNAME_TEXT)
                    .addComponent(USER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CHANGE_PASSWORD_TEXT)
                    .addComponent(CHANGE_PASSWORD_INPUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CHANGE_CONFIRM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(ACCOUNT_MANAGEMENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ACCOUNT_MANAGEMENT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ACCOUNT_MANAGEMENT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void NEW_PASSWORD_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NEW_PASSWORD_INPUTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NEW_PASSWORD_INPUTActionPerformed

    private void NEW_USERNAME_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NEW_USERNAME_INPUTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NEW_USERNAME_INPUTActionPerformed

    private void CHANGE_PASSWORD_INPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHANGE_PASSWORD_INPUTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CHANGE_PASSWORD_INPUTActionPerformed

    private void USERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_USERActionPerformed

    private void NEW_CONFIRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NEW_CONFIRMActionPerformed
        boolean canEdit = true;
        boolean userExists = false;
        String username = NEW_USERNAME_INPUT.getText();
        String password = NEW_PASSWORD_INPUT.getText();
        int result = JOptionPane.CANCEL_OPTION;

        if (username.length() == 0) {
            JOptionPane.showMessageDialog(null, "Username needs to be longer than 0 characters", "Error", JOptionPane.WARNING_MESSAGE);
            canEdit = false;
        }
        if (username.length() > 45 && canEdit) {
            JOptionPane.showMessageDialog(null, "Username is too long 45 characters maximum", "Error", JOptionPane.WARNING_MESSAGE);
            canEdit = false;
        }
        if (password.length() == 0 && canEdit) {
            JOptionPane.showMessageDialog(null, "Password needs to be longer than 0 characters", "Error", JOptionPane.WARNING_MESSAGE);
            canEdit = false;
        }
        if (password.length() > 45 && canEdit) {
            JOptionPane.showMessageDialog(null, "Password is too long 45 characters maximum", "Error", JOptionPane.WARNING_MESSAGE);
            canEdit = false;
        }
        if (canEdit)
        {
            try {
                userExists = model.UserDAO.userExistsByUsername(username);
            }
            catch (SQLException e) { System.err.println(e.getMessage());}
        
            result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to create a new user?", "Question",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }

        if (result == JOptionPane.YES_OPTION && canEdit && !userExists) {
            model.User user = new model.User();
            user.setUsername(NEW_USERNAME_INPUT.getText());
            user.setPassword(NEW_PASSWORD_INPUT.getText());
            user.setPrivileges(jComboBox2.getSelectedItem().toString());
            try {
                model.UserDAO.create(user);
                updateUSERModel();
                JOptionPane.showMessageDialog(null, "Succesfully created user: " + username, "Message", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (userExists){
            JOptionPane.showMessageDialog(null, "Username already exists!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_NEW_CONFIRMActionPerformed

    private void CHANGE_CONFIRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHANGE_CONFIRMActionPerformed
        boolean canEdit = true;

        String pass = CHANGE_PASSWORD_INPUT.getText();
        if (pass.length() == 0) {
            JOptionPane.showMessageDialog(null, "Password needs to be longer than 0 characters", "Error", JOptionPane.WARNING_MESSAGE);
            canEdit = false;
        }
        if (pass.length() > 45) {
            JOptionPane.showMessageDialog(null, "Password is too long 45 characters maximum", "Error", JOptionPane.WARNING_MESSAGE);
            canEdit = false;
        }
        
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to change the user's password?", "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION && canEdit) {
            try {
                model.User user = model.UserDAO.readByUsername(USER.getSelectedItem().toString());
                user.setPassword(pass);
                model.UserDAO.update(user);
            } catch (SQLException ex) {
                Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_CHANGE_CONFIRMActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete the user?", "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
             try {
                model.User user = model.UserDAO.readByUsername(USER.getSelectedItem().toString());
                user.getUserid();
                model.UserDAO.delete(user.getUserid());
            } catch (SQLException ex) {
                Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

    }//GEN-LAST:event_jCheckBox1ActionPerformed

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ACCOUNT_MANAGEMENT;
    private javax.swing.JButton CHANGE_CONFIRM;
    private javax.swing.JLabel CHANGE_PASSWORD;
    private javax.swing.JTextField CHANGE_PASSWORD_INPUT;
    private javax.swing.JLabel CHANGE_PASSWORD_TEXT;
    private javax.swing.JLabel CHANGE_USERNAME_TEXT;
    private javax.swing.JButton NEW_CONFIRM;
    private javax.swing.JLabel NEW_PASSWORD;
    private javax.swing.JTextField NEW_PASSWORD_INPUT;
    private javax.swing.JLabel NEW_USER;
    private javax.swing.JLabel NEW_USERNAME;
    private javax.swing.JTextField NEW_USERNAME_INPUT;
    private javax.swing.JComboBox USER;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
