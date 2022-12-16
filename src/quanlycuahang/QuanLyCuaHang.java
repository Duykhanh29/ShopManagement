/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlycuahang;

import File.FileIO;
import Lists.DanhSachHangHoa;
import Lists.DanhSachHoaDon;
import Lists.DanhSachNhanVien;
import check.CheckDate;
import model.Goods;
import model.Bill;
import model.Staff;
import java.util.ArrayList;
import jdbc.HoaDonJDBC;

/**
 *
 * @author Admin
 */
public class QuanLyCuaHang {

    /**
     * @param args the command line arguments
     */
     
    public static void main(String[] args) {
        // TODO code application logic here

        // check
//        HangHoa hangHoa = new HangHoa();
//        System.out.println("Chào tất cả mọi người");
//        FileIO fileIO = new FileIO();
//        String string = "Alo alo today is a good day for me. Good morning everyone";
//        String filePath = "example.txt";
//        fileIO.writeData(filePath, string);
//        String x = fileIO.readData(filePath);
//        System.out.println("core value: " + x);
//        DanhSachHangHoa listHangHoa=new DanhSachHangHoa();
//        listHangHoa.insertHangHoa();
//        System.out.println("First display");
//        listHangHoa.display();
//        listHangHoa.sortAscendingbySellingPrice();
//        System.out.println("Second display");
//        listHangHoa.display();
//        listHangHoa.sortAscendingByQuantity();
//        System.out.println("Third display");
//        listHangHoa.display();
//        DanhSachNhanVien danhSachNhanVien=new DanhSachNhanVien();
//        danhSachNhanVien.insertNhanVien();
//        System.out.println("First display");
//        danhSachNhanVien.display();
//        danhSachNhanVien.sortAscendingbyName();
//        System.out.println("Sconde display");
//        danhSachNhanVien.display();
//        DanhSachHoaDon danhSachHoaDon=new DanhSachHoaDon();
//           danhSachHoaDon.insertHangHoa();
//           System.out.println("First display");
//           danhSachHoaDon.display();
//           danhSachHoaDon.sortAscendingbyQuatity();
//           System.out.println("Second display");
//           danhSachHoaDon.display();
//           System.out.println("List hoa don do nhan vien co ma: EDFE nhap vao\n");
//           ArrayList<HoaDon> list=new ArrayList<>();
//           list=danhSachHoaDon.getDanhSachHoaDonDoNhanVien("EDFE");
//           for (HoaDon hoaDon : list) {
//               System.out.println(hoaDon.toString());
//            }
//           danhSachHoaDon.sortDescendingbyQuatity();
//           System.out.println("Third display");
//           danhSachHoaDon.display();
//            System.out.println("List hoa don do nhan vien co ma: EDFE nhap vao lan 2\n");
//           ArrayList<HoaDon> list1=new ArrayList<>();
//           list1=danhSachHoaDon.getDanhSachHoaDonDoNhanVien("EDFE");
//           for (HoaDon hoaDon : list1) {
//               System.out.println(hoaDon.toString());
//            }
//        NhanVien nhanVien=new NhanVien();
//        ArrayList<String> dSachChucVu=new ArrayList<>();
//        dSachChucVu=nhanVien.getdSachChucVu();
//        for (String string : dSachChucVu) {
//            System.out.println(string+"\t");
//        }
//        String x="Nhan vien ban hang";
//        
//        DanhSachNhanVien danhSachNhanVien=new DanhSachNhanVien();
//        danhSachNhanVien.insertNhanVien();
//        ArrayList<NhanVien> newList=new ArrayList<>();
//        newList=danhSachNhanVien.danhSachNhanVienBanHang();
//        System.out.println("Display list");
//        for (NhanVien nhanVien1 : newList) {
//            System.out.println(nhanVien1.toString());
//        }
//String x="15-09-2021";
//        CheckDate check=new CheckDate();
//        check.validDate(x);
//        check.ValidFormat(x);
//String[] text = String.valueOf(x).split("-");
//                String ngaySinh = text[2] +""+ text[1] +""+ text[0]+"";
//                System.out.println(ngaySinh);
//                String DoB=text[2].concat("-").concat(text[1]).concat("-").concat(text[0]);
//        System.out.println("DoB: "+DoB);
FileIO fileIO=new FileIO();
String fileName="thongBao.txt";
String x=fileIO.readData(fileName);
        System.out.println(x);
        System.out.println(x);
    }
//    String x= "29-2-2016";
//    CheckDate check=new CheckDate();
//    if(check)
    

}
    

