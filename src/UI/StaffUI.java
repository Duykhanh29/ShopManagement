/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;
import model.Staff;
import java.util.logging.Level;
import java.util.logging.Logger;
import Lists.*;
import java.util.ArrayList;
import jdbc.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class StaffUI extends javax.swing.JFrame {

    /**
     * Creates new form StaffUI
     */
    String staffID;
    StaffJDBC staffJDBC;
    StaffList staffList;
    ArrayList<Staff> listStaff=new ArrayList<>();
    Staff thisStaff=new Staff();
    public StaffUI(String dataController)throws Exception {
        staffJDBC=new StaffJDBC();
        staffList=new StaffList();
        listStaff=staffJDBC.getData();
        staffList.setList(listStaff);
        staffID=dataController;
        thisStaff=staffList.getStaffWithID(staffID);
        initComponents();
        this.setLocationRelativeTo(null);
        showTitle.setText("WELCOME "+staffID+"  " + thisStaff.getStaffName());
        
    }

    private StaffUI() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showTitle = new javax.swing.JLabel();
        showInforLabel = new javax.swing.JLabel();
        logOutButton = new javax.swing.JButton();
        changePasswordButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        showTitle.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        showTitle.setForeground(new java.awt.Color(255, 0, 0));
        showTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        showInforLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        changePasswordButton.setText("Change Password");
        changePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordButtonActionPerformed(evt);
            }
        });

        homeButton.setText("Home");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(showInforLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(showTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(showTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(showInforLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        try {
            // TODO add your handling code here:
            LogIn logIn=new LogIn();
            logIn.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(StaffUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        if(thisStaff.getRole().equals("Shop assistant")){
            try {
                ShopAssistantUI banHangUI=new ShopAssistantUI(staffID);
                banHangUI.setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                Logger.getLogger(StaffUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(thisStaff.getRole().equals("Manager")){
            try {
                AdminUI adminUI=new AdminUI(staffID);
                adminUI.setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                Logger.getLogger(StaffUI.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }else{
            try {
                StorageStaffUI nhanVienKhoUI=new StorageStaffUI(staffID);
                nhanVienKhoUI.setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                Logger.getLogger(StaffUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_homeButtonActionPerformed

    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordButtonActionPerformed
        // TODO add your handling code here:
        String oldPass, newPass;
        oldPass = JOptionPane.showInputDialog("Input current password");
        if (oldPass != null && oldPass.equals("") == false) {
            Staff nv = staffList.getStaffWithID(staffID);
            if (oldPass.equals(nv.getPassword())) {
                try {
                    newPass = JOptionPane.showInputDialog("Input new password");
                    if (newPass == null) {
                        // JOptionPane.showMessageDialog(rootPane, "Nothing change");
                    } else if (newPass.equals("")) {
                        // JOptionPane.showMessageDialog(rootPane, "Nothing change");
                    } else {
                        staffJDBC.changePassword(staffID, newPass);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ManagerUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "You input wrong password");
            }
        }
    }//GEN-LAST:event_changePasswordButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaffUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePasswordButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton logOutButton;
    private javax.swing.JLabel showInforLabel;
    private javax.swing.JLabel showTitle;
    // End of variables declaration//GEN-END:variables
}
