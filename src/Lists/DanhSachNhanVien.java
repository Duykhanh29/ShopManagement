/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import model.Staff;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class DanhSachNhanVien {

   
    public ArrayList<Staff> getList() {
        return list;
    }

    public void setList(ArrayList<Staff> list) {
        this.list = list;
    }
    
      ArrayList<Staff> list=new ArrayList<>();
    public int getSize(){
        return list.size();
    }
    public Staff getStaffAtIndex(int index){
        return list.get(index);
    }
    public Staff getStaffWithID(String id){
        for (Staff staff : list) {
            if(staff.getStaffID().equals(id)){
                return staff;
            }
        }
        return null;
    }
    
    

    public void display(){
        for (Staff staff : list) {
            System.out.println(staff.toString());
        }
    }
    public ArrayList<Staff> listManager(){
        ArrayList<Staff> newList=new ArrayList<>();
        for (Staff staff : list) {
            if(staff.getRole().equals("Manager")){
                newList.add(staff);
            }
        }
        return newList;
    }
    
    public ArrayList<Staff> listWarehouseStaff(){
        ArrayList<Staff> newList=new ArrayList<>();
        for (Staff staff : list) {
            if(staff.getRole().equals("Warehouse staff")){
                newList.add(staff);
            }
        }
        return newList;
    }
    public ArrayList<Staff> listShopAssistant(){
        ArrayList<Staff> newList=new ArrayList<>();
        for (Staff staff : list) {
            if(staff.getRole().equals("Shop assistant")){
                newList.add(staff);
            }
        }
        return newList;
    }
    public boolean checkStaffInDataBase(String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getStaffID().equals(id)){
                return true;
            }
        }
        return false;
    }
  
}
