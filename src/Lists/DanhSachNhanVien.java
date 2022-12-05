/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import dinhnghia.NhanVien;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class DanhSachNhanVien {

   
    public ArrayList<NhanVien> getList() {
        return list;
    }

    public void setList(ArrayList<NhanVien> list) {
        this.list = list;
    }
    
      ArrayList<NhanVien> list=new ArrayList<>();
    public int getSize(){
        return list.size();
    }
    public NhanVien getNhanVienAtIndex(int index){
        return list.get(index);
    }
    public NhanVien getNhanVienVoiID(String id){
        for (NhanVien nhanVien : list) {
            if(nhanVien.getMaNV().equals(id)){
                return nhanVien;
            }
        }
        return null;
    }
    
    public void sortAscendingbyName()
    {
        ArrayList<NhanVien> newList=new ArrayList<>();
        newList=list;
        for(int i=0;i<newList.size();i++)
        {
            for(int j=i+1;j<newList.size();j++){
                if(newList.get(i).getTenNV().compareTo(newList.get(j).getTenNV())>0){
                    NhanVien temp=newList.get(i);
                    newList.set(i,newList.get(j));
                    newList.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingbyName()
    {
        ArrayList<NhanVien> newList=new ArrayList<>();
        newList=list;
        for(int i=0;i<newList.size();i++)
        {
            for(int j=i+1;j<newList.size();j++){
                 if(newList.get(i).getTenNV().compareTo(newList.get(j).getTenNV())>0){
                    NhanVien temp=newList.get(i);
                    newList.set(i,newList.get(j));
                    newList.set(j, temp);
                }
            }
        }
    }
     

    public void display(){
        for (NhanVien nhanVien : list) {
            System.out.println(nhanVien.toString());
        }
    }
    public ArrayList<NhanVien> danhSachQuanLy(){
        ArrayList<NhanVien> newList=new ArrayList<>();
        for (NhanVien nhanVien : list) {
            if(nhanVien.getChucVu().equals("Quan ly")){
                newList.add(nhanVien);
            }
        }
        return newList;
    }
    
    public ArrayList<NhanVien> danhSachNhanVienKho(){
        ArrayList<NhanVien> newList=new ArrayList<>();
        for (NhanVien nhanVien : list) {
            if(nhanVien.getChucVu().equals("Nhan vien kho")){
                newList.add(nhanVien);
            }
        }
        return newList;
    }
    public ArrayList<NhanVien> danhSachNhanVienBanHang(){
        ArrayList<NhanVien> newList=new ArrayList<>();
        for (NhanVien nhanVien : list) {
            if(nhanVien.getChucVu().equals("Nhan vien ban hang")){
                newList.add(nhanVien);
            }
        }
        return newList;
    }
    
    // temporary datas
    public void insertNhanVien()
    {
        list.add(new NhanVien("NV01", "FUUD", "Nhan vien ban hang", "fdaf", "fdaf", "fdafs")) ;
        list.add(new NhanVien("NV02", "Ad", "Quan ly", "fdaf", "fdaf", "fdafs")) ;
        list.add(new NhanVien("NV03", "FUUD", "Nhan vien ban hang", "fdaf", "fdaf", "fdafs")) ;
        list.add(new NhanVien("NV04", "DIB FD", "Nhan vien ban hang", "fdaf", "fdaf", "fdafs")) ;
        list.add(new NhanVien("NV05", "CDEWEFW", "Nhan vien kho", "fdaf", "fdaf", "fdafs")) ;
        list.add(new NhanVien("NV06", "HFRR", "Nhan vien kho", "fdaf", "fdaf", "fdafs")) ;
        list.add(new NhanVien("NV05", "CDEWEFW", "Nhan vien kho", "fdaf", "fdaf", "fdafs")) ;
        list.add(new NhanVien("NV06", "HFRR", "Nhan vien kho", "fdaf", "fdaf", "fdafs")) ;
    }
}
