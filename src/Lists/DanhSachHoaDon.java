/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import dinhnghia.HoaDon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class DanhSachHoaDon {
    
    ArrayList<HoaDon> list=new ArrayList<>();
    public void setDanhSachHoaDon(ArrayList<HoaDon> newList){
        this.list=newList;
    }

    public ArrayList<HoaDon> getList() {
        return list;
    }
    
    public int getSize(){
        return list.size();
    }
    public HoaDon getHoaDonAtIndex(int index){
        return list.get(index);
    }
    public HoaDon getHoaDonWithID(String id){
        for (HoaDon hoaDon : list) {
            if(hoaDon.getMaHD().equals(id)){
                return hoaDon;
            }
        }
        return null;
    }
    
    public void sortAscendingbyQuatity()
    {
        ArrayList<HoaDon> newList=new ArrayList<>();
        newList=list;
        for(int i=0;i<newList.size();i++)
        {
            for(int j=i+1;j<newList.size();j++){
                if(newList.get(i).getSoLuong()>newList.get(j).getSoLuong()){
                    HoaDon temp=newList.get(i);
                    newList.set(i,newList.get(j));
                    newList.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingbyQuatity()
    {
        ArrayList<HoaDon> newList=new ArrayList<>();
        newList=list;
        for(int i=0;i<newList.size();i++)
        {
            for(int j=i+1;j<newList.size();j++){
               if(newList.get(i).getSoLuong()<=newList.get(j).getSoLuong()){
                    HoaDon temp=newList.get(i);
                    newList.set(i,newList.get(j));
                    newList.set(j, temp);
                }
            }
        }
    }
    public void sortAscendingbyTotalPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getTongTien()>list.get(j).getTongTien()){
                    HoaDon temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingbyTotalPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                 if(list.get(i).getTongTien()<=list.get(j).getTongTien()){
                    HoaDon temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
   
     
     public ArrayList<HoaDon> getDanhSachHoaDonDoNhanVien(String maNV)
     {
         ArrayList<HoaDon> listHD=new ArrayList<>();
         for (int i = 0; i < list.size(); i++) {
             if(list.get(i).getMaNV().equals(maNV)){
                 listHD.add(list.get(i));
             }
         }
         return listHD;
     }
    public void display(){
        for (HoaDon hoaDon : list) {
            System.out.println(hoaDon.toString());
        }
    }
    
    public ArrayList<String> getListID(){
        ArrayList<String> newList=new ArrayList<>();
        for (HoaDon h : list) {
            newList.add(h.getMaHD());
        }
        return newList;
    }
    public boolean checkExist(String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaHD().equals(id)){
                return true;
            }
        }
        return false;
    }
    
    
    // temporary datas
    public void insertHangHoa()
    {
        list.add(new HoaDon("HD01", "NV01", "TG01", 6, 7)) ;
        list.add(new HoaDon("HD02", "NV02", "TG02",  6, 7)) ;
        list.add(new HoaDon("HD03", "NV03", "TG03", 6, 7)) ;
        list.add(new HoaDon("HD04", "UFD", "fdsa", 6, 7)) ;
        list.add(new HoaDon("HD05", "UFD", "ehtsrg", 4,6)) ;
        list.add(new HoaDon("HD06", "UFD", "EDFE",  4, 1)) ;
        list.add(new HoaDon("HD07", "UFD", "EDFE",  6, 7)) ;
        list.add(new HoaDon("HD08", "UFD", "gshdt",  6, 7)) ;
        list.add(new HoaDon("HD09", "UFD", "5egwt", 6, 7)) ;
    }

  
}
