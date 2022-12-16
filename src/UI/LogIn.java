/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Lists.*;
import model.Staff;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdbc.NhanVienJDBC;
import jdbc.TaiKhoanJDBC;

/**
 *
 * @author Admin
 */
public class LogIn extends javax.swing.JFrame {

    DanhSachNhanVien danhSachNhanVien;
    Staff nv;
    ArrayList<Staff> listStaff = new ArrayList<>();
    NhanVienJDBC nhanVienJDBC;
    TaiKhoanJDBC taiKhoanJDBC;
    ArrayList<Staff> listNVBH = new ArrayList<>();
    

    /**
     * Creates new form LogIn
     */
    public LogIn() throws Exception {
        initComponents();
        danhSachNhanVien = new DanhSachNhanVien();
        nhanVienJDBC = new NhanVienJDBC();
        taiKhoanJDBC = new TaiKhoanJDBC();
        listStaff = nhanVienJDBC.getDataNhanVien();
        danhSachNhanVien.setList(listStaff);
        //   danhSachNhanVien.insertNhanVien();
        nv = new Staff();
        showComboBox();
        this.setLocationRelativeTo(null);
    }

    private void showComboBox() {
        ArrayList<String> list = nv.getListRole();;
        for (String string : list) {
            roleComboBox.addItem(string);
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

        roleComboBox = new javax.swing.JComboBox<>();
        userNameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        logInButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        roleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleComboBoxActionPerformed(evt);
            }
        });

        userNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameTextFieldActionPerformed(evt);
            }
        });

        logInButton.setText("Log In");
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(408, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roleComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(210, 210, 210))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameTextFieldActionPerformed

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed

        if (String.valueOf(roleComboBox.getSelectedItem()).equals("Shop assistant")) {
            listNVBH = danhSachNhanVien.listShopAssistant();
            boolean value = false;
            for (int i = 0; i < listNVBH.size(); i++) {
                String userName = String.valueOf(userNameTextField.getText());
                String password = String.valueOf(passwordField.getPassword());
                if (userName.equals(listNVBH.get(i).getStaffID())) {
                    try {
                        Staff nv = danhSachNhanVien.getStaffWithID(userName);
                        System.out.println("Password: "+nv.getPassword());
                        if (password.equals(nv.getPassword())) {
                           StaffUI staffUI = new StaffUI(userName);
                           staffUI.setVisible(true);
                           value = true;
                           this.dispose();
                        } else {
                            value = true;
                            JOptionPane.showMessageDialog(rootPane, "Khong dung password");
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (value == false) {
                JOptionPane.showMessageDialog(this, "Khong dung userName");
            }

        } else if (String.valueOf(roleComboBox.getSelectedItem()).equals("Warehouse staff")) {
            listNVBH = danhSachNhanVien.listWarehouseStaff();
            boolean value = false;
            for (int i = 0; i < listNVBH.size(); i++) {
                String userName = String.valueOf(userNameTextField.getText());
                String password = String.valueOf(passwordField.getPassword());
                if (userName.equals(listNVBH.get(i).getStaffID())) {
                    Staff nv = danhSachNhanVien.getStaffWithID(userName);
                    if (password.equals(nv.getPassword())) {
                        try {
                            StaffUI satffUI = new StaffUI(userName);
                            satffUI.setVisible(true);
                            value = true;
                            this.dispose();
                        } catch (Exception ex) {
                            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        value = true;
                        JOptionPane.showMessageDialog(rootPane, "Khong dung password");
                    }

                }
            }
            if (value == false) {
                JOptionPane.showMessageDialog(this, "Ko dung userName");
            }

        } else if (String.valueOf(roleComboBox.getSelectedItem()).equals("Manager")) {
            listNVBH = danhSachNhanVien.listManager();
            boolean value = false;
            String userName = String.valueOf(userNameTextField.getText());
            for (int i = 0; i < listNVBH.size(); i++) {
                if (userName.equals(listNVBH.get(i).getStaffID())) {
                    try {
                        String password = String.valueOf(passwordField.getPassword());
                        Staff nv = danhSachNhanVien.getStaffWithID(userName);
                        if (password.equals(nv.getPassword())) {
                            ManagerUI managerUI = new ManagerUI(userName);
                            managerUI.setVisible(true);
                            value = true;
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Khong dung password");
                            value = true;
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (value == false) {
                JOptionPane.showMessageDialog(this, "Ko dung userName");
            }
        }
    }//GEN-LAST:event_logInButtonActionPerformed

    private void roleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LogIn().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton logInButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables

}
