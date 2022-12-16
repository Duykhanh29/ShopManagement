/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import model.Goods;
import model.Bill;
import model.BillDetails;
import Lists.*;
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
import File.*;

/**
 *
 * @author Admin
 */
public class NhanVienBanHangUI extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienBanHangUI
     */
    GoodsFileIO goodsFileIO;
    BillDetailsFIleIO billDetailsFIleIO;
    BillFileIO billFileIO;
    DanhSachChiTietHoaDon danhSachChiTietHoaDon;
    DanhSachHoaDon danhSachHoaDon;
    DanhSachHangHoa danhSachHangHoa;
    HoaDonJDBC hoaDonJDBC;
    ChiTietHoaDonJDBC chiTietHoaDonJDBC;
    HangHoaJDBC hangHoaJDBC;
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModelDetail;

    ArrayList<Goods> listHangHoa = new ArrayList<>();
    ArrayList<BillDetails> listChiTietHoaDon = new ArrayList<>();
    ArrayList<Bill> listBill = new ArrayList<>();     // nếu được chỉ là danh sách các bill của nhân viên này thôi. Còn hiện tại là all staff ( 21/11/2022)
    DanhSachChiTietHoaDon temporaryDanhSachChiTietHoaDon = new DanhSachChiTietHoaDon();

    String maNV;
    int clickChiTietHoaDon = -1;
    int clickHoaDon = -1;

    public NhanVienBanHangUI(String dataController) throws Exception {
        initComponents();
        billFileIO=new BillFileIO();
        billDetailsFIleIO=new BillDetailsFIleIO();
        goodsFileIO=new GoodsFileIO();
        hoaDonJDBC = new HoaDonJDBC();
        hangHoaJDBC = new HangHoaJDBC();
        chiTietHoaDonJDBC = new ChiTietHoaDonJDBC();
        danhSachChiTietHoaDon = new DanhSachChiTietHoaDon();
        danhSachHoaDon = new DanhSachHoaDon();
        danhSachHangHoa = new DanhSachHangHoa();

        listBill = hoaDonJDBC.getDataHoaDon();
        listHangHoa = hangHoaJDBC.getDataHangHoa();
        listChiTietHoaDon = chiTietHoaDonJDBC.getDataChiTietHoaDon();
        //  danhSachChiTietHoaDon

        danhSachHangHoa.setList(listHangHoa);
        danhSachHoaDon.setDanhSachHoaDon(listBill);

        goodsFileIO.writeDataFromFile(goodsFileIO.fileGoods, listHangHoa);
        billDetailsFIleIO.writeDataFromFile(billDetailsFIleIO.fileDetail, listChiTietHoaDon);
        billFileIO.writeDataFromFile(billFileIO.fileBill, listBill);
        maNV = dataController;

        defaultTableModelDetail = (DefaultTableModel) chiTietTable.getModel();
        defaultTableModel = (DefaultTableModel) hoaDonTable.getModel();
        showStaffID.setText(maNV);
        showComboBox();
        // showSpinner();
        showDateLabel.setText(returnDate());
        display();
        this.setLocationRelativeTo(null);
    }

    private NhanVienBanHangUI() {
    }

    public void display() {
        defaultTableModel.setRowCount(0);
        if (listBill != null) {
            for (int i = 0; i < listBill.size(); i++) {
                showTable((i + 1), listBill.get(i));
            }
        }

    }

    public void showTable(int index, Bill h) {
        defaultTableModel.addRow(new Object[]{
            index, h.getBillID(), h.getStaffID(), h.getTime(), h.getQuantity(), h.getTotalCosts()
        });
    }

    public void displayChiTietHangHoa() {
        if (temporaryDanhSachChiTietHoaDon.getSize() != 0) {
            for (int i = 0; i < temporaryDanhSachChiTietHoaDon.getSize(); i++) {
                showTableDetail((i + 1), temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(i));
            }
        }
        displayTKDetail();
    }

    public void displayTKDetail() {
        int soLuong = 0;
        int tongTien = 0;
        for (int i = 0; i < temporaryDanhSachChiTietHoaDon.getSize(); i++) {
            soLuong = soLuong + temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(i).getQuantity();
            tongTien = tongTien + temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(i).getTotalCosts();
        }
        defaultTableModelDetail.addRow(new Object[]{
            "", "", "", "", soLuong, tongTien
        });
    }

    public void showTableDetail(int index, BillDetails c) {
        defaultTableModelDetail.addRow(new Object[]{
            index, c.getGoodsID(), c.getGoodsName(), c.getSellingCost(), c.getQuantity(), c.getTotalCosts()
        });
    }

    private void showComboBox() {
        ArrayList<String> danhSachMaHH = new ArrayList<>();
        danhSachMaHH = danhSachHangHoa.getListID();
        for (String string : danhSachMaHH) {
            goodsIDComboBox.addItem(string);
        }
    }

    public boolean emptyCheck() {
        if (maHDTextField.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Input again");
            maHDTextField.requestFocus();
            return true;
        }
        return false;
    }

    public boolean emptyCheck2() {
        if (Integer.parseInt(quantitySpinner.getValue().toString()) == 0) {
            JOptionPane.showMessageDialog(this, "Please input quantity");
            quantitySpinner.requestFocus();
            return true;
        }
        return false;
    }

    public String returnDate() {
        //  DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString();
    }

    public void showFormChiTietHoaDon(BillDetails cthd) {
        maHDTextField.setText(cthd.getBillID());
        goodsIDComboBox.setSelectedItem(cthd.getGoodsID());
        goodsNameLabel.setText(cthd.getGoodsName());
        showCostLabel.setText(cthd.getSellingCost()+ "");
        showTotalCostsLabel.setText(cthd.getTotalCosts()+ "");
        quantitySpinner.setValue(cthd.getQuantity());
    }

    public BillDetails getChiTietHoaDonFromForm() {
        BillDetails chiTietHoaDon = new BillDetails();
        chiTietHoaDon.setBillID(maHDTextField.getText());
        chiTietHoaDon.setGoodsID(goodsIDComboBox.getSelectedItem().toString());
        chiTietHoaDon.setQuantity(Integer.valueOf(quantitySpinner.getValue().toString()));
        chiTietHoaDon.setGoodsName(goodsNameLabel.getText());
        chiTietHoaDon.setTotalCosts(Integer.valueOf(showTotalCostsLabel.getText()));
        chiTietHoaDon.setSellingCost(Integer.valueOf(showCostLabel.getText()));
        return chiTietHoaDon;
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
        ngayBanLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hoaDonTable = new javax.swing.JTable();
        findButton = new javax.swing.JButton();
        findTextField = new javax.swing.JTextField();
        themHangHoaButton = new javax.swing.JButton();
        maHHLabel = new javax.swing.JLabel();
        goodsIDComboBox = new javax.swing.JComboBox<>();
        donGiaLabel2 = new javax.swing.JLabel();
        showCostLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        tenHHLabel = new javax.swing.JLabel();
        tongTienLabel = new javax.swing.JLabel();
        showTotalCostsLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        chiTietTable = new javax.swing.JTable();
        resetChiTietHoaDon = new javax.swing.JButton();
        suaChiTietHoaDon = new javax.swing.JButton();
        xoaChiTietHoaDon = new javax.swing.JButton();
        confirmPaymentButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        goodsNameLabel = new javax.swing.JLabel();
        showDateLabel = new javax.swing.JLabel();
        showStaffID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BILL");

        maHDLabel.setText("Bill ID");

        maHDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maHDTextFieldActionPerformed(evt);
            }
        });

        maNVLabel.setText("Staff ID");

        ngayBanLabel.setText("Date");

        hoaDonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Bill ID", "Staff ID", "Time", "Total quantity", "Total Costs"
            }
        ));
        hoaDonTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hoaDonTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hoaDonTableMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(hoaDonTable);
        if (hoaDonTable.getColumnModel().getColumnCount() > 0) {
            hoaDonTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        findButton.setText("Find");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        themHangHoaButton.setText("Add Goods");
        themHangHoaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themHangHoaButtonActionPerformed(evt);
            }
        });

        maHHLabel.setText("Goods ID");

        goodsIDComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goodsIDComboBoxActionPerformed(evt);
            }
        });

        donGiaLabel2.setText("Selling Cost");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Quantity");

        quantitySpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        quantitySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                quantitySpinnerStateChanged(evt);
            }
        });

        tenHHLabel.setText("Goods Name");

        tongTienLabel.setText("Total Price");

        chiTietTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Goods ID", "Goods name", "Don Gia", "Quantity", "Total Costs"
            }
        ));
        chiTietTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chiTietTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(chiTietTable);
        if (chiTietTable.getColumnModel().getColumnCount() > 0) {
            chiTietTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            chiTietTable.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        resetChiTietHoaDon.setText("Reset");
        resetChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetChiTietHoaDonActionPerformed(evt);
            }
        });

        suaChiTietHoaDon.setText("Edit");
        suaChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaChiTietHoaDonActionPerformed(evt);
            }
        });

        xoaChiTietHoaDon.setText("Delete");
        xoaChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaChiTietHoaDonActionPerformed(evt);
            }
        });

        confirmPaymentButton.setText("Confirm payment");
        confirmPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPaymentButtonActionPerformed(evt);
            }
        });

        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(maHDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maHDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(maNVLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(showStaffID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(findTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(findButton)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(resetButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(ngayBanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(showDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tenHHLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(maHHLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(169, 169, 169)
                                                .addComponent(confirmPaymentButton))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(goodsNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tongTienLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(showTotalCostsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(goodsIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(donGiaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(showCostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(resetChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(themHangHoaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(xoaChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(suaChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(maHHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(goodsIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(donGiaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(showDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(showCostLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quantitySpinner))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(showTotalCostsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tenHHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maNVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(goodsNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(showStaffID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themHangHoaButton)
                            .addComponent(suaChiTietHoaDon))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xoaChiTietHoaDon)
                            .addComponent(resetChiTietHoaDon)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(findTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(findButton)
                            .addComponent(resetButton))))
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


    private void themHangHoaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themHangHoaButtonActionPerformed
        if (emptyCheck() != true) {
            if (emptyCheck2() != true) {
                try {
                    String maHD = String.valueOf(maHDTextField.getText());
                    String maHH = goodsIDComboBox.getSelectedItem().toString();
                    if (temporaryDanhSachChiTietHoaDon.validCheck(maHD, maHH) == true) {
                        // if 2 bill detail are same => input again
                        JOptionPane.showMessageDialog(this, "Please input other goods's id or other bill's id");
                    } else {

                        String tenHH = danhSachHangHoa.getTenHHFromMaHH(maHH);
                        int soLuong = Integer.parseInt(quantitySpinner.getValue().toString());
                        int giaBan = danhSachHangHoa.getDonGiaFromID(maHH);
                        int tongSoTien = giaBan * soLuong;
                        BillDetails c = new BillDetails(maHD, maHH, tenHH, soLuong, giaBan, tongSoTien);
                        System.out.println("C:" + c.toString());
                        if (danhSachHoaDon.checkExist(maHD) == false) {
                            Bill hh = new Bill(maHD, maNV, returnDate(), 0, 0);
                            System.out.println("You are in here");
                            hoaDonJDBC.insertIntoDatabase(hh);
                            listBill = hoaDonJDBC.getDataHoaDon();
                            danhSachHoaDon.setDanhSachHoaDon(listBill);
                        }
                        defaultTableModelDetail.setRowCount(0);
                        temporaryDanhSachChiTietHoaDon.add(c);
                        displayChiTietHangHoa();

                    }

                } catch (Exception ex) {
                    Logger.getLogger(NhanVienBanHangUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }//GEN-LAST:event_themHangHoaButtonActionPerformed

    private void goodsIDComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goodsIDComboBoxActionPerformed
        // TODO add your handling code here:
        String id = goodsIDComboBox.getSelectedItem().toString();
        System.out.println("ID: " + id);
        int soLuong = danhSachHangHoa.getSoLuongCuaMotHangHoa(id);
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, // initial value
                0, // min
                soLuong, // max
                1);
        quantitySpinner.setModel(spinnerModel);
        String tenHH = danhSachHangHoa.getTenHHFromMaHH(id);
        goodsNameLabel.setText(tenHH);
        int giaBan = danhSachHangHoa.getDonGiaFromID(id);
        showCostLabel.setText(giaBan + "");
    }//GEN-LAST:event_goodsIDComboBoxActionPerformed

    private void quantitySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_quantitySpinnerStateChanged
        // TODO add your handling code here:
        String id = goodsIDComboBox.getSelectedItem().toString();
        int giaBan = danhSachHangHoa.getDonGiaFromID(id);
        int soLuong = Integer.parseInt(quantitySpinner.getValue().toString());
        int tongTien = giaBan * soLuong;
        showTotalCostsLabel.setText(tongTien + "");
    }//GEN-LAST:event_quantitySpinnerStateChanged

    private void xoaChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        if (temporaryDanhSachChiTietHoaDon.getSize() != 0) {
            if (clickChiTietHoaDon == -1 || clickChiTietHoaDon == temporaryDanhSachChiTietHoaDon.getSize()) {

            } else {
                BillDetails chiTietHoaDon = temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(clickChiTietHoaDon);
                temporaryDanhSachChiTietHoaDon.delete(chiTietHoaDon);
                defaultTableModelDetail.setRowCount(0);
                displayChiTietHangHoa();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No data");
        }

    }//GEN-LAST:event_xoaChiTietHoaDonActionPerformed

    private void confirmPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPaymentButtonActionPerformed
        // TODO add your handling code here:
        if (temporaryDanhSachChiTietHoaDon.getSize() != 0) {
            try {
                for (int i = 0; i < temporaryDanhSachChiTietHoaDon.getSize(); i++) {
                    chiTietHoaDonJDBC.insertIntoDatabase(temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(i));
                }

                // update so luong hang cua tung mat hang sau khi ban
                for (int i = 0; i < temporaryDanhSachChiTietHoaDon.getSize(); i++) {
                    String id = temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(i).getGoodsID();
                    int newQuantity = temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(i).getQuantity();
                    int oldQuantity = danhSachHangHoa.getGoodsWithID(id).getQuantity();
                    hangHoaJDBC.update(id, oldQuantity, newQuantity);
                }
                int tongSoLuongHang = temporaryDanhSachChiTietHoaDon.getTotalQuantityOfEachBill();
                int tongTien = temporaryDanhSachChiTietHoaDon.getTotalCostsOfEachBill();
                String maHD = String.valueOf(maHDTextField.getText());
                Bill h = new Bill(maHD, maNV, returnDate(), tongSoLuongHang, tongTien);
                hoaDonJDBC.edit(maHD, h);
                listBill = hoaDonJDBC.getDataHoaDon();
                danhSachHoaDon.setDanhSachHoaDon(listBill);
                defaultTableModel.setRowCount(0);
                defaultTableModelDetail.setRowCount(0);
                temporaryDanhSachChiTietHoaDon = new DanhSachChiTietHoaDon();
                listHangHoa = hangHoaJDBC.getDataHangHoa();
                goodsFileIO.writeDataFromFile(goodsFileIO.fileGoods, listHangHoa);
                billDetailsFIleIO.writeDataFromFile(billDetailsFIleIO.fileDetail, listChiTietHoaDon);
                billFileIO.writeDataFromFile(billFileIO.fileBill, listBill);
                danhSachHangHoa.setList(listHangHoa);
                display();
            } catch (Exception ex) {
                Logger.getLogger(NhanVienBanHangUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_confirmPaymentButtonActionPerformed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            StaffUI staffUI = new StaffUI(maNV);
            staffUI.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(NhanVienBanHangUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void suaChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        if (temporaryDanhSachChiTietHoaDon.getSize() != 0) {
            if (clickChiTietHoaDon != -1) {
                try {
                    BillDetails chiTietHoaDon = temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(clickChiTietHoaDon);
                    //showFormChiTietHoaDon(chiTietHoaDon);
                    BillDetails chiTietHoaDon1 = getChiTietHoaDonFromForm();
                    temporaryDanhSachChiTietHoaDon.edit(chiTietHoaDon, chiTietHoaDon1);
                    defaultTableModelDetail.setRowCount(0);
                    displayChiTietHangHoa();
                    // chiTietHoaDonJDBC.edit(chiTietHoaDon, chiTietHoaDon1);
                } catch (Exception ex) {
                    Logger.getLogger(NhanVienBanHangUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_suaChiTietHoaDonActionPerformed

    private void chiTietTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chiTietTableMouseClicked
        // TODO add your handling code here:
        if (temporaryDanhSachChiTietHoaDon.getSize() != 0) {
            int point = chiTietTable.getSelectedRow();
            clickChiTietHoaDon = point;
            if (clickChiTietHoaDon == -1 || clickChiTietHoaDon == temporaryDanhSachChiTietHoaDon.getSize()) {
                JOptionPane.showMessageDialog(rootPane, "Something went wrong");
            } else {
                BillDetails chiTietHoaDon = temporaryDanhSachChiTietHoaDon.getBillDetailsAtIndex(clickChiTietHoaDon);
                showFormChiTietHoaDon(chiTietHoaDon);
            }
        }
    }//GEN-LAST:event_chiTietTableMouseClicked

    private void resetChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        goodsIDComboBox.setSelectedIndex(0);
        String id = goodsIDComboBox.getSelectedItem().toString();
        goodsNameLabel.setText(danhSachHangHoa.getTenHHFromMaHH(id));
        quantitySpinner.setValue(0);
        showCostLabel.setText("");
        showTotalCostsLabel.setText("");
        findTextField.setText("");
        defaultTableModelDetail.setRowCount(0);
        displayChiTietHangHoa();
    }//GEN-LAST:event_resetChiTietHoaDonActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        // TODO add your handling code here:
        if (danhSachHoaDon.getSize() != 0) {
            String id = findTextField.getText();
            Bill hd = danhSachHoaDon.getBillWithID(id);
            if (hd == null) {
                JOptionPane.showMessageDialog(this, "Not found");
            } else {
                defaultTableModel.setRowCount(0);
                showTable(1, hd);
            }
        } else {
            JOptionPane.showMessageDialog(this, "NO data");
        }
    }//GEN-LAST:event_findButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        findTextField.setText("");
        defaultTableModel.setRowCount(0);
        display();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void hoaDonTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoaDonTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_hoaDonTableMouseClicked

    private void hoaDonTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoaDonTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_hoaDonTableMouseEntered

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NhanVienBanHangUI nhanVienBanHangUI = new NhanVienBanHangUI();
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
    private javax.swing.JLabel donGiaLabel2;
    private javax.swing.JButton findButton;
    private javax.swing.JTextField findTextField;
    private javax.swing.JComboBox<String> goodsIDComboBox;
    private javax.swing.JLabel goodsNameLabel;
    private javax.swing.JTable hoaDonTable;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel maHDLabel;
    private javax.swing.JTextField maHDTextField;
    private javax.swing.JLabel maHHLabel;
    private javax.swing.JLabel maNVLabel;
    private javax.swing.JLabel ngayBanLabel;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton resetChiTietHoaDon;
    private javax.swing.JLabel showCostLabel;
    private javax.swing.JLabel showDateLabel;
    private javax.swing.JLabel showStaffID;
    private javax.swing.JLabel showTotalCostsLabel;
    private javax.swing.JButton suaChiTietHoaDon;
    private javax.swing.JLabel tenHHLabel;
    private javax.swing.JButton themHangHoaButton;
    private javax.swing.JLabel tongTienLabel;
    private javax.swing.JButton xoaChiTietHoaDon;
    // End of variables declaration//GEN-END:variables

}
