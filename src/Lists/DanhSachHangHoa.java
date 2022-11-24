/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import dinhnghia.HangHoa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class DanhSachHangHoa {

    public ArrayList<HangHoa> getList() {
        return list;
    }

    public void setList(ArrayList<HangHoa> list) {
        this.list = list;
    }
    
    ArrayList<HangHoa> list=new ArrayList<>();
    public int getSize(){
        return list.size();
    }
    public HangHoa getHangHoaAtIndex(int index){
        return list.get(index);
    }
    public HangHoa getHangHoaWithID(String id){
        for (HangHoa hangHoa : list) {
            if(hangHoa.getMaHH().equals(id)){
                return hangHoa;
            }
        }
        return null;
    }
    public ArrayList<String> getDanhSachMaHH(){
        ArrayList<String> newList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList.add(list.get(i).getMaHH());
        }
        return newList;
    }
    public void sortAscendingbySellingPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getGiaBan()>list.get(j).getGiaBan()){
                    HangHoa temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingbySellingPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getGiaBan()<=list.get(j).getGiaBan()){
                    HangHoa temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
    public void sortAscendingbyImportPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getGiaNhap()>list.get(j).getGiaNhap()){
                    HangHoa temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingbyImportPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getGiaNhap()<=list.get(j).getGiaNhap()){
                    HangHoa temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingByQuantity()
     {
         Collections.sort(list,  new Comparator<HangHoa>() {
             @Override
             public int compare(HangHoa o1, HangHoa o2) {
                 if(o1.getSoLuong()<o2.getSoLuong()){
                     return 1;
                 }
                 return -1;
             }
         });
     }
     public void sortAscendingByQuantity()
     {
         Collections.sort(list,  new Comparator<HangHoa>() {
             @Override
             public int compare(HangHoa o1, HangHoa o2) {
                 if(o1.getSoLuong()>=o2.getSoLuong()){
                     return 1;
                 }
                 return -1;
             }
         });
     }
    public void display(){
        for (HangHoa hangHoa : list) {
            System.out.println(hangHoa.toString());
        }
    }
    // temporary datas
    public void insertHangHoa()
    {
        list.add(new HangHoa("HH01", "fdsa", "fdasf", 23, 43, 65)) ;
        list.add(new HangHoa("HH02s", "fdsa", "fdasf", 43, 43, 34)) ;
        list.add(new HangHoa("HH03", "fdsa", "fdasf", 65, 43, 32)) ;
        list.add(new HangHoa("HH04", "fdsa", "fdasf", 23, 43, 87)) ;
        list.add(new HangHoa("HH05", "fdsa", "fdasf", 12, 43, 12)) ;
        list.add(new HangHoa("HH07", "fdsa", "fdasf", 21, 43, 76)) ;
    }

    public ArrayList<String> getListID() {
        ArrayList<String> newList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList.add(list.get(i).getMaHH());
        }
        return newList;
    }
    public String getTenHHFromMaHH(String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaHH().equals(id)){
                return list.get(i).getTenHangHoa();
            }
        }
        return null;
    }
    public int getDonGiaFromID(String id){
         for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaHH().equals(id)){
                return list.get(i).getGiaBan();
            }
        }
        return -1;
    }
    
    public boolean checkExist(String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaHH().equals(id)){
                return true;
            }
        }
        return false;
    }
}
