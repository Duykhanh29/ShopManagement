/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import model.BillDetails;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DanhSachChiTietHoaDon {

    public void setList(ArrayList<BillDetails> list) {
        this.list = list;
    }
    
    ArrayList<BillDetails> list=new ArrayList<>();
    public int getSize(){
        return list.size();
    }
    public void add(BillDetails c){
        list.add(c);
    }
    public String getGoodsNameFromMaHH(String id){
        for (BillDetails billDetails : list) {
            if(billDetails.getGoodsID().equals(id)){
                return billDetails.getGoodsName();
            }
        }
        return null;
    }
    public int getSellingCostFromID(String id){
        for (BillDetails chiTietHoaDon : list) {
            if(chiTietHoaDon.getGoodsID().equals(id)){
                return chiTietHoaDon.getSellingCost();
            }
        }
        return -1;
    }
   
    public ArrayList<BillDetails> getList(){
        return list;
    }
    
    // temporary method
    public ArrayList<String> getListID(){
         ArrayList<String> newList=new ArrayList<>();
         for (BillDetails chiTietHoaDon : list) {
            newList.add(chiTietHoaDon.getGoodsID());
        }
         return newList;
    }
    
    public BillDetails getBillDetailsAtIndex(int index){
        return list.get(index);
    }
    
    
    
      public int getTotalQuantityOfEachBill(){
        int num=0;
        for (int i = 0; i < list.size(); i++) {
           // if(list.get(i).getMaHD().equals(maHD)){
                num=num+list.get(i).getQuantity();
           // }
        }
        return num;
    }
    public int getTotalCostsOfEachBill(){
        int num=0;
        for (int i = 0; i < list.size(); i++) {
      //      if(list.get(i).getMaHD().equals(maHD)){
                num=num+list.get(i).getTotalCosts();
       //     }
        }
        return num;
    }
    
    
    
    
    
    
    
    // unnecessary methods
    public int getTotalQuantityOfEachBill(String maHD){
        int num=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getBillID().equals(maHD)){
                num=num+list.get(i).getQuantity();
            }
        }
        return num;
    }
    public int getTotalCostsOfEachBill(String maHD){
        int num=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getBillID().equals(maHD)){
                num=num+list.get(i).getTotalCosts();
            }
        }
        return num;
    }
    
    // check if 2 Bill Detail is same 
    public boolean validCheck(String maHD,String maHH){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getBillID().equals(maHD)&&list.get(i).getGoodsID().equals(maHH)){
                return true; 
            }
        }
        return false;
    }
    public void edit(BillDetails oldH,BillDetails newH){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getBillID().equals(oldH.getBillID())&&list.get(i).getGoodsID().equals(oldH.getGoodsID())){
                list.set(i, newH);
            }
        }
    }
    public void delete(BillDetails c){
         for(int i=0;i<list.size();i++){
            if(list.get(i).getBillID().equals(c.getBillID())&&list.get(i).getGoodsID().equals(c.getGoodsID())){
                list.remove(i);
            }
        }
    }
    
}
