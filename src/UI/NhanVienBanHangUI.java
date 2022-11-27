/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Lists.*;
import dinhnghia.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import jdbc.*;

/**
 *
 * @author Admin
 */
public class NhanVienBanHangUI extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienBanHangUI
     */
    DanhSachChiTietHoaDon danhSachChiTietHoaDon;
    DanhSachHoaDon danhSachHoaDon;
    DanhSachHangHoa danhSachHangHoa;
    HoaDonJDBC hoaDonJDBC;
    ChiTietHoaDonJDBC chiTietHoaDonJDBC;
    HangHoaJDBC hangHoaJDBC;
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModelDetail;
    
    ArrayList<HangHoa> listHangHoa=new ArrayList<>();
    ArrayList<ChiTietHoaDon> listChiTietHoaDon=new ArrayList<>();
    ArrayList<HoaDon> listBill=new ArrayList<>();     // nếu được chỉ là danh sách các bill của nhân viên này thôi. Còn hiện tại là all staff ( 21/11/2022)
    DanhSachChiTietHoaDon temporaryDanhSachChiTietHoaDon=new DanhSachChiTietHoaDon();
    
    String maNV;
    
    
    public NhanVienBanHangUI(String dataController) throws Exception {
        initComponents();
        hoaDonJDBC=new HoaDonJDBC();
        hangHoaJDBC=new HangHoaJDBC();
        chiTietHoaDonJDBC=new ChiTietHoaDonJDBC();
        danhSachChiTietHoaDon=new DanhSachChiTietHoaDon();
        danhSachHoaDon=new DanhSachHoaDon();
        danhSachHangHoa =new DanhSachHangHoa();
        
        
        listBill=hoaDonJDBC.getDataHoaDon();
        listHangHoa=hangHoaJDBC.getDataHangHoa();
        listChiTietHoaDon=chiTietHoaDonJDBC.getDataChiTietHoaDon();
      //  danhSachChiTietHoaDon
      
      
        danhSachHangHoa.setList(listHangHoa);
        danhSachHoaDon.setDanhSachHoaDon(listBill);
        
      
        
        
        maNV=dataController;
        
        defaultTableModelDetail=(DefaultTableModel)chiTietTable.getModel();
        defaultTableModel=(DefaultTableModel)hoaDonTable.getModel();
        maNVTextField.setText(maNV);
        showComboBox();
       // showSpinner();
        
        
        display();
    }

//    public void showSpinner(){
//        
//          
//    }
    private NhanVienBanHangUI() {
    }

    public void display(){
        defaultTableModel.setRowCount(0);
        if(listBill!=null){
            for (int i = 0; i < listBill.size(); i++) {
            showTable((i+1),listBill.get(i));
        }
        }
        
    }
    public void showTable(int index,HoaDon h){
        defaultTableModel.addRow(new Object[]{
            index,h.getMaHD(),h.getMaNV(),h.getThoiGian(),h.getSoLuong(),h.getTongTien()
        });
    }
    public void displayChiTietHangHoa(){
        if(temporaryDanhSachChiTietHoaDon.getSize()!=0){
            for(int i=0;i<temporaryDanhSachChiTietHoaDon.getSize();i++){
                showTableDetail((i+1),temporaryDanhSachChiTietHoaDon.getChiTietHoaDonAtIndex(i));
            }
        }
        displayTKDetail();
    }
    public void displayTKDetail(){
        int soLuong=0;
        int tongTien=0;
        for (int i = 0; i < temporaryDanhSachChiTietHoaDon.getSize(); i++) {
            soLuong=soLuong+temporaryDanhSachChiTietHoaDon.getChiTietHoaDonAtIndex(i).getSoLuong();
            tongTien=tongTien+temporaryDanhSachChiTietHoaDon.getChiTietHoaDonAtIndex(i).getTongTien();
        }
        defaultTableModelDetail.addRow(new Object[]{
            "","","","",soLuong,tongTien
        });
    }
    public void showTableDetail(int index,ChiTietHoaDon c){
        defaultTableModelDetail.addRow(new Object[]{
            index,c.getMaHH(),c.getTenHangHoa(),c.getGiaBan(),c.getSoLuong(),c.getTongTien()
        });
    }
    public boolean emptyCheck() {
        if (maHDTextField.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Input again");
            maHDTextField.requestFocus();
            return true;
        }
        return false;
    }
    public boolean emptyCheck2(){
        if(Integer.parseInt(soLuongSpinner.getValue().toString())==0){
                JOptionPane.showMessageDialog(this, "Please input quantity");
                soLuongSpinner.requestFocus();
                return true;
        }
        return false;
    }

    public String returnDate() {
      //  DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        maHDLabel = new javax.swing.JLabel();
        maHDTextField = new javax.swing.JTextField();
        maNVLabel = new javax.swing.JLabel();
        maNVTextField = new javax.swing.JTextField();
        ngayBanTextField = new javax.swing.JTextField();
        ngayBanLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hoaDonTable = new javax.swing.JTable();
        findButton = new javax.swing.JButton();
        findTextField = new javax.swing.JTextField();
        themHangHoaButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        maHHLabel = new javax.swing.JLabel();
        maHangHoaComboBox = new javax.swing.JComboBox<>();
        donGiaLabel2 = new javax.swing.JLabel();
        showCostLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        soLuongSpinner = new javax.swing.JSpinner();
        tenHHLabel = new javax.swing.JLabel();
        tenHangHoaTextField = new javax.swing.JTextField();
        tongTienLabel = new javax.swing.JLabel();
        showTotalCostsLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        chiTietTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        confirmPaymentButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hoa Don Ban Hang");

        maHDLabel.setText("Ma HD");

        maHDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maHDTextFieldActionPerformed(evt);
            }
        });

        maNVLabel.setText("MA NV");

        maNVTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maNVTextFieldActionPerformed(evt);
            }
        });

        ngayBanTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayBanTextFieldActionPerformed(evt);
            }
        });

        ngayBanLabel.setText("Ngay Ban");

        hoaDonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "MaHD", "MaNV", "ThoiGian", "TongSoLuong", "TongTien"
            }
        ));
        jScrollPane1.setViewportView(hoaDonTable);

        findButton.setText("Find");

        themHangHoaButton.setText("Them Hang Hoa");
        themHangHoaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themHangHoaButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");

        maHHLabel.setText("Ma Hang Hoa");

        maHangHoaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maHangHoaComboBoxActionPerformed(evt);
            }
        });

        donGiaLabel2.setText("Don Gia");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("So Luong");

        soLuongSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        soLuongSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                soLuongSpinnerStateChanged(evt);
            }
        });

        tenHHLabel.setText("Ten Hang Hoa");

        tenHangHoaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenHangHoaTextFieldActionPerformed(evt);
            }
        });

        tongTienLabel.setText("Tong Tien");

        chiTietTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Ma HH", "Ten HH", "Don Gia", "So Luong", "Tong Tien"
            }
        ));
        jScrollPane2.setViewportView(chiTietTable);

        jButton1.setText("Reset");

        jButton2.setText("Edit");

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        confirmPaymentButton.setText("Confirm payment");
        confirmPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPaymentButtonActionPerformed(evt);
            }
        });

        jButton4.setText("LogOut");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(maNVLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maNVTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(507, 507, 507))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(maHDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(maHDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(findTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(findButton)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(153, 153, 153)
                                        .addComponent(ngayBanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ngayBanTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(181, 181, 181)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(110, 110, 110)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tenHHLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(maHHLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(maHangHoaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(donGiaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(showCostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(soLuongSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(confirmPaymentButton)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tenHangHoaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(showTotalCostsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(72, 72, 72))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(themHangHoaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(125, 125, 125))))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(454, 454, 454)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maHDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maHDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ngayBanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ngayBanTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maHHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maHangHoaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(donGiaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(showCostLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(soLuongSpinner))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(showTotalCostsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tenHHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tenHangHoaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maNVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maNVTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themHangHoaButton)
                            .addComponent(jButton2))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(findTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(findButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(deleteButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maHDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maHDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maHDTextFieldActionPerformed

    private void maNVTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maNVTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maNVTextFieldActionPerformed

    private void ngayBanTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayBanTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ngayBanTextFieldActionPerformed

    
    private void themHangHoaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themHangHoaButtonActionPerformed
        if (emptyCheck() != true) {
            if(emptyCheck2()!=true){
                try {
                    String maHD=String.valueOf(maHDTextField.getText());
                    String maHH=maHangHoaComboBox.getSelectedItem().toString();
                    String tenHH = danhSachHangHoa.getTenHHFromMaHH(maHH);
                    int soLuong=Integer.parseInt(soLuongSpinner.getValue().toString());
                    int giaBan = danhSachHangHoa.getDonGiaFromID(maHH);
                    int tongSoTien=giaBan*soLuong;
                    ChiTietHoaDon c=new ChiTietHoaDon(maHD, maHH, tenHH, soLuong, giaBan, tongSoTien);
                    System.out.println("C:"+ c.toString());
                   
                    if(danhSachHoaDon.checkExist(maHD)==false){
                        HoaDon hh=new HoaDon(maHD, maNV, returnDate(), 0, 0);
                        System.out.println("You are in here");
                        hoaDonJDBC.insertIntoDatabase(hh);
                        listBill=hoaDonJDBC.getDataHoaDon();
                        danhSachHoaDon.setDanhSachHoaDon(listBill);
                    }
                  //  chiTietHoaDonJDBC.insertIntoDatabase(c);
                    defaultTableModelDetail.setRowCount(0);
                    temporaryDanhSachChiTietHoaDon.add(c);
                    displayChiTietHangHoa();
                } catch (Exception ex) {
                    Logger.getLogger(NhanVienBanHangUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }

    }//GEN-LAST:event_themHangHoaButtonActionPerformed

    private void maHangHoaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maHangHoaComboBoxActionPerformed
        // TODO add your handling code here:
        String id = maHangHoaComboBox.getSelectedItem().toString();
        System.out.println("ID: " + id);
        int soLuong=danhSachHangHoa.getSoLuongCuaMotHangHoa(id);
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, // initial value
                0, // min
                soLuong, // max
                1);
        soLuongSpinner.setModel(spinnerModel);
        String tenHH = danhSachHangHoa.getTenHHFromMaHH(id);
        tenHangHoaTextField.setText(tenHH);
        int giaBan = danhSachHangHoa.getDonGiaFromID(id);
        showCostLabel.setText(giaBan + "");
    }//GEN-LAST:event_maHangHoaComboBoxActionPerformed

    private void soLuongSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_soLuongSpinnerStateChanged
        // TODO add your handling code here:
        String id = maHangHoaComboBox.getSelectedItem().toString();
        int giaBan = danhSachHangHoa.getDonGiaFromID(id);
        int soLuong = Integer.parseInt(soLuongSpinner.getValue().toString());
        int tongTien = giaBan * soLuong;
        showTotalCostsLabel.setText(tongTien + "");
    }//GEN-LAST:event_soLuongSpinnerStateChanged

    private void tenHangHoaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenHangHoaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenHangHoaTextFieldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void confirmPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPaymentButtonActionPerformed
        // TODO add your handling code here:
        if(temporaryDanhSachChiTietHoaDon.getSize()!=0){
            try {
                for (int i = 0; i < temporaryDanhSachChiTietHoaDon.getSize(); i++) {
                    chiTietHoaDonJDBC.insertIntoDatabase(temporaryDanhSachChiTietHoaDon.getChiTietHoaDonAtIndex(i));
                }
                int tongSoLuongHang=temporaryDanhSachChiTietHoaDon.getTotalQuantityOfEachBill();
                int tongTien=temporaryDanhSachChiTietHoaDon.getTotalCostsOfEachBill();
                String maHD=String.valueOf(maHDTextField.getText());
                HoaDon h=new HoaDon(maHD, maNV, returnDate(), tongSoLuongHang, tongTien);
                hoaDonJDBC.edit(maHD,h);
                listBill=hoaDonJDBC.getDataHoaDon();
                danhSachHoaDon.setDanhSachHoaDon(listBill);
                defaultTableModel.setRowCount(0);
                defaultTableModelDetail.setRowCount(0);
                temporaryDanhSachChiTietHoaDon=null;
                System.out.println("SHow Temporary");
//                for (ChiTietHoaDon c : temporaryDanhSachChiTietHoaDon.getList()) {
//                    System.out.println(c.toString());
//                }
                display();
            } catch (Exception ex) {
                Logger.getLogger(NhanVienBanHangUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_confirmPaymentButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:
            LogIn logIn=new LogIn();
            logIn.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(NhanVienBanHangUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienBanHangUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienBanHangUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienBanHangUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienBanHangUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NhanVienBanHangUI nhanVienBanHangUI=new NhanVienBanHangUI();
                    nhanVienBanHangUI.setVisible(true);
                   // nhanVienBanHangUI.setSize(1400, 1200);
                    
                } catch (Exception ex) {
                    Logger.getLogger(NhanVienBanHangUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable chiTietTable;
    private javax.swing.JButton confirmPaymentButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel donGiaLabel2;
    private javax.swing.JButton findButton;
    private javax.swing.JTextField findTextField;
    private javax.swing.JTable hoaDonTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel maHDLabel;
    private javax.swing.JTextField maHDTextField;
    private javax.swing.JLabel maHHLabel;
    private javax.swing.JComboBox<String> maHangHoaComboBox;
    private javax.swing.JLabel maNVLabel;
    private javax.swing.JTextField maNVTextField;
    private javax.swing.JLabel ngayBanLabel;
    private javax.swing.JTextField ngayBanTextField;
    private javax.swing.JLabel showCostLabel;
    private javax.swing.JLabel showTotalCostsLabel;
    private javax.swing.JSpinner soLuongSpinner;
    private javax.swing.JLabel tenHHLabel;
    private javax.swing.JTextField tenHangHoaTextField;
    private javax.swing.JButton themHangHoaButton;
    private javax.swing.JLabel tongTienLabel;
    // End of variables declaration//GEN-END:variables

    private void showComboBox() {
        ArrayList<String> danhSachMaHH = new ArrayList<>();
        danhSachMaHH = danhSachHangHoa.getListID();
        for (String string : danhSachMaHH) {
            maHangHoaComboBox.addItem(string);
        }
    }
}
