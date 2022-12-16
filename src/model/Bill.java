/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Bill {
    private String billID,staffID,time;
    private int quantity,totalCosts;

    public Bill(String billID, String staffID, String time, int quantity, int totalCosts) {
        this.billID = billID;
        this.staffID = staffID;
        this.time = time;
        this.quantity = quantity;
        this.totalCosts = totalCosts;
    }   
   
    public Bill() {
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(int totalCosts) {
        this.totalCosts = totalCosts;
    }

    

    @Override
    public String toString() {
        return  billID +  "\t" + staffID +   "\t" + time + "\t" + quantity + "\t" + totalCosts ;
    }
    
}
