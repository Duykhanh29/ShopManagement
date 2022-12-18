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
public class ShopAssistantUI extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienBanHangUI
     */
    GoodsFileIO goodsFileIO;
    BillDetailsFIleIO billDetailsFIleIO;
    BillFileIO billFileIO;
    BillDetailsList billDetailsList;
    BillList billList;
    GoodsList goodsList;
    BillJDBC billJDBC;
    BillDetailsJDBC billDetailsJDBC;
    GoodsJDBC goodsJDBC;
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModelDetail;

    ArrayList<Goods> listGoods = new ArrayList<>();
    ArrayList<BillDetails> listBillDetails = new ArrayList<>();
    ArrayList<Bill> listBill = new ArrayList<>();
    BillDetailsList temporaryListBillDetails = new BillDetailsList();

    String staffID;
    int click = -1;

    public ShopAssistantUI(String dataController) throws Exception {
        initComponents();
        billFileIO = new BillFileIO();
        billDetailsFIleIO = new BillDetailsFIleIO();
        goodsFileIO = new GoodsFileIO();
        billJDBC = new BillJDBC();
        goodsJDBC = new GoodsJDBC();
        billDetailsJDBC = new BillDetailsJDBC();
        billDetailsList = new BillDetailsList();
        billList = new BillList();
        goodsList = new GoodsList();

        listBill = billJDBC.getData();
        listGoods = goodsJDBC.getData();
        listBillDetails = billDetailsJDBC.getData();
        //  danhSachChiTietHoaDon

        goodsList.setList(listGoods);
        billList.setList(listBill);

        goodsFileIO.writeDataFromFile(goodsFileIO.fileGoods, listGoods);
        billDetailsFIleIO.writeDataFromFile(billDetailsFIleIO.fileDetail, listBillDetails);
        billFileIO.writeDataFromFile(billFileIO.fileBill, listBill);
        staffID = dataController;

        defaultTableModelDetail = (DefaultTableModel) chiTietTable.getModel();
        defaultTableModel = (DefaultTableModel) hoaDonTable.getModel();
        showStaffID.setText(staffID);
        showComboBox();
        // showSpinner();
        showDateLabel.setText(returnDate());
        display();
        this.setLocationRelativeTo(null);
    }

    private ShopAssistantUI() {
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
        if (temporaryListBillDetails.getSize() != 0) {
            for (int i = 0; i < temporaryListBillDetails.getSize(); i++) {
                showTableDetail((i + 1), temporaryListBillDetails.getBillDetailsAtIndex(i));
            }
        }
        displayTKDetail();
    }

    public void displayTKDetail() {
        int soLuong = 0;
        int tongTien = 0;
        for (int i = 0; i < temporaryListBillDetails.getSize(); i++) {
            soLuong = soLuong + temporaryListBillDetails.getBillDetailsAtIndex(i).getQuantity();
            tongTien = tongTien + temporaryListBillDetails.getBillDetailsAtIndex(i).getTotalCosts();
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
        danhSachMaHH = goodsList.getListID();
        for (String string : danhSachMaHH) {
            goodsIDComboBox.addItem(string);
        }
    }

    public boolean emptyCheck() {
        if (billIDTextField.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Input again");
            billIDTextField.requestFocus();
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
        billIDTextField.setText(cthd.getBillID());
        goodsIDComboBox.setSelectedItem(cthd.getGoodsID());
        goodsNameLabel.setText(cthd.getGoodsName());
        showCostLabel.setText(cthd.getSellingCost() + "");
        showTotalCostsLabel.setText(cthd.getTotalCosts() + "");
        quantitySpinner.setValue(cthd.getQuantity());
    }

    public BillDetails getChiTietHoaDonFromForm() {
        BillDetails chiTietHoaDon = new BillDetails();
        chiTietHoaDon.setBillID(billIDTextField.getText());
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
        billIDTextField = new javax.swing.JTextField();
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

        billIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billIDTextFieldActionPerformed(evt);
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
                "No.", "Goods ID", "Goods name", "Seling Costs", "Quantity", "Total Costs"
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
                                .addComponent(billIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(billIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void billIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billIDTextFieldActionPerformed


    private void themHangHoaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themHangHoaButtonActionPerformed
        if (emptyCheck() != true) {
            if (emptyCheck2() != true) {
                try {
                    String billID = String.valueOf(billIDTextField.getText());
                    String goodsID = goodsIDComboBox.getSelectedItem().toString();
                    if (temporaryListBillDetails.validCheck(billID, goodsID) == true) {
                        // if 2 bill detail are same => input again
                        JOptionPane.showMessageDialog(this, "Please input other goods's id or other bill's id");
                    } else {

                        String name = goodsList.getNameGoodsFromID(goodsID);
                        int quantity = Integer.parseInt(quantitySpinner.getValue().toString());
                        int sellingCosts = goodsList.getSellingCostFromID(goodsID);
                        int totalCosts = sellingCosts * quantity;
                        BillDetails c = new BillDetails(billID, goodsID, name, quantity, sellingCosts, totalCosts);
                        if (billList.checkExist(billID) == false) {
                            Bill b = new Bill(billID, staffID, returnDate(), 0, 0);
                            billJDBC.insertIntoDatabase(b);
                            listBill = billJDBC.getData();
                            billList.setList(listBill);
                        }
                        defaultTableModelDetail.setRowCount(0);
                        temporaryListBillDetails.add(c);
                        displayChiTietHangHoa();

                    }

                } catch (Exception ex) {
                    Logger.getLogger(ShopAssistantUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }//GEN-LAST:event_themHangHoaButtonActionPerformed

    private void goodsIDComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goodsIDComboBoxActionPerformed
        // TODO add your handling code here:
        String id = goodsIDComboBox.getSelectedItem().toString();
        System.out.println("ID: " + id);
        int quantity = goodsList.getQuantityOfAGoods(id);
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, // initial value
                0, // min
                quantity, // max
                1);
        quantitySpinner.setModel(spinnerModel);
        String tenHH = goodsList.getNameGoodsFromID(id);
        goodsNameLabel.setText(tenHH);
        int sellingCost = goodsList.getSellingCostFromID(id);
        showCostLabel.setText(sellingCost + "");
    }//GEN-LAST:event_goodsIDComboBoxActionPerformed

    private void quantitySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_quantitySpinnerStateChanged
        // TODO add your handling code here:
        String id = goodsIDComboBox.getSelectedItem().toString();
        int giaBan = goodsList.getSellingCostFromID(id);
        int soLuong = Integer.parseInt(quantitySpinner.getValue().toString());
        int tongTien = giaBan * soLuong;
        showTotalCostsLabel.setText(tongTien + "");
    }//GEN-LAST:event_quantitySpinnerStateChanged

    private void xoaChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        if (temporaryListBillDetails.getSize() != 0) {
            if (click == -1 || click == temporaryListBillDetails.getSize()) {

            } else {
                BillDetails chiTietHoaDon = temporaryListBillDetails.getBillDetailsAtIndex(click);
                temporaryListBillDetails.delete(chiTietHoaDon);
                defaultTableModelDetail.setRowCount(0);
                displayChiTietHangHoa();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No data");
        }

    }//GEN-LAST:event_xoaChiTietHoaDonActionPerformed

    private void confirmPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPaymentButtonActionPerformed
        // TODO add your handling code here:
        if (temporaryListBillDetails.getSize() != 0) {
            try {
                for (int i = 0; i < temporaryListBillDetails.getSize(); i++) {
                    billDetailsJDBC.insertIntoDatabase(temporaryListBillDetails.getBillDetailsAtIndex(i));
                }

                // update so luong hang cua tung mat hang sau khi ban
                for (int i = 0; i < temporaryListBillDetails.getSize(); i++) {
                    String id = temporaryListBillDetails.getBillDetailsAtIndex(i).getGoodsID();
                    int newQuantity = temporaryListBillDetails.getBillDetailsAtIndex(i).getQuantity();
                    int oldQuantity = goodsList.getGoodsWithID(id).getQuantity();
                    goodsJDBC.update(id, oldQuantity, newQuantity);
                }
                int totalQuantity = temporaryListBillDetails.getTotalQuantityOfEachBill();
                int totalCosts = temporaryListBillDetails.getTotalCostsOfEachBill();
                String billID = String.valueOf(billIDTextField.getText());
                Bill h = new Bill(billID, staffID, returnDate(), totalQuantity, totalCosts);
                billJDBC.edit(billID, h);
                listBill = billJDBC.getData();
                billList.setList(listBill);
                defaultTableModel.setRowCount(0);
                defaultTableModelDetail.setRowCount(0);
                temporaryListBillDetails = new BillDetailsList();
                listGoods = goodsJDBC.getData();
                goodsFileIO.writeDataFromFile(goodsFileIO.fileGoods, listGoods);
                billDetailsFIleIO.writeDataFromFile(billDetailsFIleIO.fileDetail, listBillDetails);
                billFileIO.writeDataFromFile(billFileIO.fileBill, listBill);
                goodsList.setList(listGoods);
                display();
            } catch (Exception ex) {
                Logger.getLogger(ShopAssistantUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_confirmPaymentButtonActionPerformed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            StaffUI staffUI = new StaffUI(staffID);
            staffUI.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(ShopAssistantUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void suaChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        if (temporaryListBillDetails.getSize() != 0) {

            if (click == -1 || click == temporaryListBillDetails.getSize()) {

            } else {
                try {
                    BillDetails chiTietHoaDon = temporaryListBillDetails.getBillDetailsAtIndex(click);
                    //showFormChiTietHoaDon(chiTietHoaDon);
                    BillDetails chiTietHoaDon1 = getChiTietHoaDonFromForm();
                    temporaryListBillDetails.edit(chiTietHoaDon, chiTietHoaDon1);
                    defaultTableModelDetail.setRowCount(0);
                    displayChiTietHangHoa();
                    // chiTietHoaDonJDBC.edit(chiTietHoaDon, chiTietHoaDon1);
                } catch (Exception ex) {
                    Logger.getLogger(ShopAssistantUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_suaChiTietHoaDonActionPerformed

    private void chiTietTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chiTietTableMouseClicked
        // TODO add your handling code here:
        if (temporaryListBillDetails.getSize() != 0) {
            int point = chiTietTable.getSelectedRow();
            click = point;
            if (click == -1 || click == temporaryListBillDetails.getSize()) {
                JOptionPane.showMessageDialog(rootPane, "Something went wrong");
            } else {
                BillDetails chiTietHoaDon = temporaryListBillDetails.getBillDetailsAtIndex(click);
                showFormChiTietHoaDon(chiTietHoaDon);
            }
        }
    }//GEN-LAST:event_chiTietTableMouseClicked

    private void resetChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        goodsIDComboBox.setSelectedIndex(0);
        String id = goodsIDComboBox.getSelectedItem().toString();
        goodsNameLabel.setText(goodsList.getNameGoodsFromID(id));
        quantitySpinner.setValue(0);
        showCostLabel.setText("");
        showTotalCostsLabel.setText("");
        findTextField.setText("");
        defaultTableModelDetail.setRowCount(0);
        displayChiTietHangHoa();
    }//GEN-LAST:event_resetChiTietHoaDonActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        // TODO add your handling code here:
        if (billList.getSize() != 0) {
            String id = findTextField.getText();
            Bill b = billList.getBillWithID(id);
            if (b == null) {
                JOptionPane.showMessageDialog(this, "Not found");
            } else {
                defaultTableModel.setRowCount(0);
                showTable(1, b);
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
            java.util.logging.Logger.getLogger(ShopAssistantUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShopAssistantUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShopAssistantUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShopAssistantUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShopAssistantUI nhanVienBanHangUI = new ShopAssistantUI();
                    nhanVienBanHangUI.setVisible(true);
                    // nhanVienBanHangUI.setSize(1400, 1200);

                } catch (Exception ex) {
                    Logger.getLogger(ShopAssistantUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField billIDTextField;
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
