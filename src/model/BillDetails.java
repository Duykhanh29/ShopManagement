/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class BillDetails {
    private String billID,goodsID,goodsName;
    private int quantity,sellingCost,totalCosts;

    public BillDetails() {
    }

    public BillDetails(String billID, String goodsID, String goodsName, int quantity, int sellingCost, int totalCosts) {
        this.billID = billID;
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.sellingCost = sellingCost;
        this.totalCosts = totalCosts;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSellingCost() {
        return sellingCost;
    }

    public void setSellingCost(int sellingCost) {
        this.sellingCost = sellingCost;
    }

    public int getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(int totalCosts) {
        this.totalCosts = totalCosts;
    }

 

    @Override
    public String toString() {
        return  billID + "\t" + goodsID + "\t" + goodsName + "\t" + quantity + "\t" + sellingCost + "\t" + totalCosts;
    }
    
}
