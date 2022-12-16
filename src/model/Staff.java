/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Staff {
    ArrayList<String> listRole=new ArrayList<>();
    public void ListRole()
    {
        listRole.add("Shop assistant");
        listRole.add("Warehouse staff");
        listRole.add("Manager");
    }
    private String staffID,staffName,role,password,phoneNumber,DoB;

    public ArrayList<String> getListRole() {
        return listRole;
    }

    public void setListRole(ArrayList<String> listRole) {
        this.listRole = listRole;
    }
    
    

    public Staff() {
        ListRole();
    }

    public Staff(String staffID, String staffName, String role, String password, String phoneNumber, String DoB) {
        this.staffID = staffID;
        this.staffName = staffName;
       // this.gioiTinh=gioiTinh;
        this.role = role;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.DoB = DoB;
        ListRole();
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String DoB) {
        this.DoB = DoB;
    }



    @Override
    public String toString() {
        return  staffID + "\t" + staffName + "\t" + role + "\t" + password + "\t" + phoneNumber + "\t" + DoB;
    }
    
}
