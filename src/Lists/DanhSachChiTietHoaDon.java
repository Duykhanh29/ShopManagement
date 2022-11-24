/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import dinhnghia.ChiTietHoaDon;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DanhSachChiTietHoaDon {

    public void setList(ArrayList<ChiTietHoaDon> list) {
        this.list = list;
    }
    
    ArrayList<ChiTietHoaDon> list=new ArrayList<>();
    public int getSize(){
        return list.size();
    }
    public void add(ChiTietHoaDon c){
        list.add(c);
    }
    public String getTenHHFromMaHH(String id){
        for (ChiTietHoaDon chiTietHoaDon : list) {
            if(chiTietHoaDon.getMaHH().equals(id)){
                return chiTietHoaDon.getTenHangHoa();
            }
        }
        return null;
    }
    public int getDonGiaFromID(String id){
        for (ChiTietHoaDon chiTietHoaDon : list) {
            if(chiTietHoaDon.getMaHH().equals(id)){
                return chiTietHoaDon.getGiaBan();
            }
        }
        return -1;
    }
    public void insert(){
        list.add(new ChiTietHoaDon("HD01", "HH01", "Ten", 4, 2, 51));
        list.add(new ChiTietHoaDon("HD02", "HH01", "fdas", 4, 62, 15));
        list.add(new ChiTietHoaDon("HD03", "HH05", "Ten", 4, 23, 54));
        list.add(new ChiTietHoaDon("HD04", "HH06", "fda", 4, 62, 5));
        list.add(new ChiTietHoaDon("HD05", "HH07", "gfds", 4, 2, 25));
        list.add(new ChiTietHoaDon("HD07", "HH08", "fd", 4, 2, 5));
        list.add(new ChiTietHoaDon("HD08", "HH09", "Ten", 54, 27, 52));
        list.add(new ChiTietHoaDon("HD09", "HH01", "gs", 4, 2, 5));
        list.add(new ChiTietHoaDon("HD10", "HH01", "gfs", 64, 2, 52));
    }
    public ArrayList<ChiTietHoaDon> getList(){
        return list;
    }
    
    // temporary method
    public ArrayList<String> getListID(){
         ArrayList<String> newList=new ArrayList<>();
         for (ChiTietHoaDon chiTietHoaDon : list) {
            newList.add(chiTietHoaDon.getMaHH());
        }
         return newList;
    }
    
    public ChiTietHoaDon getChiTietHoaDonAtIndex(int index){
        return list.get(index);
    }
    
    
    
      public int getTotalQuantityOfEachBill(){
        int num=0;
        for (int i = 0; i < list.size(); i++) {
           // if(list.get(i).getMaHD().equals(maHD)){
                num=num+list.get(i).getSoLuong();
           // }
        }
        return num;
    }
    public int getTotalCostsOfEachBill(){
        int num=0;
        for (int i = 0; i < list.size(); i++) {
      //      if(list.get(i).getMaHD().equals(maHD)){
                num=num+list.get(i).getTongTien();
       //     }
        }
        return num;
    }
    
    
    
    
    
    
    
    // unnecessary methods
    public int getTotalQuantityOfEachBill(String maHD){
        int num=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaHD().equals(maHD)){
                num=num+list.get(i).getSoLuong();
            }
        }
        return num;
    }
    public int getTotalCostsOfEachBill(String maHD){
        int num=0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getMaHD().equals(maHD)){
                num=num+list.get(i).getTongTien();
            }
        }
        return num;
    }
    
    
}
