/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Goods {
    private String goodsID,goodsName,producer;
    private int quantity,importedCost,sellingCost;

    public Goods() {
    }

    public Goods(String goodsID, String tenHangHoa, String producer, int quantity, int importedCost, int sellingCost) {
        this.goodsID = goodsID;
        this.goodsName = tenHangHoa;
        this.producer = producer;
        this.quantity = quantity;
        this.importedCost = importedCost;
        this.sellingCost = sellingCost;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImportedCost() {
        return importedCost;
    }

    public void setImportedCost(int importedCost) {
        this.importedCost = importedCost;
    }

    public int getSellingCost() {
        return sellingCost;
    }

    public void setSellingCost(int sellingCost) {
        this.sellingCost = sellingCost;
    }

   

    @Override
    public String toString() {
        return goodsID + "\t" + goodsName + "\t" + producer + "\t" + quantity + "\t" + importedCost + "\t" + sellingCost ;
    }
    
   
}
