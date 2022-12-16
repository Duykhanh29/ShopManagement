/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import model.Bill;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class DanhSachHoaDon {

    ArrayList<Bill> list = new ArrayList<>();

    public void setDanhSachHoaDon(ArrayList<Bill> newList) {
        this.list = newList;
    }

    public ArrayList<Bill> getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

    public Bill getBillAtIndex(int index) {
        return list.get(index);
    }

    public Bill getBillWithID(String id) {
        for (Bill bill : list) {
            if (bill.getBillID().equals(id)) {
                return bill;
            }
        }
        return null;
    }

    

    public ArrayList<Bill> getListBillByStaff(String staffID) {
        ArrayList<Bill> listHD = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStaffID().equals(staffID)) {
                listHD.add(list.get(i));
            }
        }
        return listHD;
    }

    public void display() {
        for (Bill hoaDon : list) {
            System.out.println(hoaDon.toString());
        }
    }

    public ArrayList<String> getListID() {
        ArrayList<String> newList = new ArrayList<>();
        for (Bill h : list) {
            newList.add(h.getBillID());
        }
        return newList;
    }

    public boolean checkExist(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBillID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public int getTotalCostAtACertainDate(String date, String month, String year) {
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            String[] text = list.get(i).getTime().split("-");
            if (date.equals("") == true) {
                if (month.equals("") == true) {
                    if (text[0].equals(year)) {
                        total += list.get(i).getTotalCosts();
                    }
                    
                } else {
                    if (text[0].equals(year) && text[1].equals(month)) {
                        total += list.get(i).getTotalCosts();
                    }
                   
                }
            } else {
                if (text[0].equals(year) && text[1].equals(month)&&text[2].equals(date)) {
                    total += list.get(i).getTotalCosts();
                }
               
            }
        }
        return total;
    }
    
    // unnecessary methods
    public void sortAscendingbyQuatity() {
        ArrayList<Bill> newList = new ArrayList<>();
        newList = list;
        for (int i = 0; i < newList.size(); i++) {
            for (int j = i + 1; j < newList.size(); j++) {
                if (newList.get(i).getQuantity() > newList.get(j).getQuantity()) {
                    Bill temp = newList.get(i);
                    newList.set(i, newList.get(j));
                    newList.set(j, temp);
                }
            }
        }
    }

    public void sortDescendingbyQuatity() {
        ArrayList<Bill> newList = new ArrayList<>();
        newList = list;
        for (int i = 0; i < newList.size(); i++) {
            for (int j = i + 1; j < newList.size(); j++) {
                if (newList.get(i).getQuantity()<= newList.get(j).getQuantity()) {
                    Bill temp = newList.get(i);
                    newList.set(i, newList.get(j));
                    newList.set(j, temp);
                }
            }
        }
    }

    public void sortAscendingbyTotalPrice() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getTotalCosts() > list.get(j).getTotalCosts()) {
                    Bill temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    public void sortDescendingbyTotalPrice() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getTotalCosts()<= list.get(j).getTotalCosts()) {
                    Bill temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

}
