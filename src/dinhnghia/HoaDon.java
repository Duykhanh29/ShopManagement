/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhnghia;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private String maHD,maNV,thoiGian;
    private int soLuong,tongTien;

    public HoaDon(String maHD, String maNV, String thoiGian, int soLuong, int tongTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.thoiGian = thoiGian;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }   
   
    public HoaDon() {
    }

    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

   
   
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }


    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return  maHD +  "\t" + maNV +   "\t" + thoiGian + "\t" + soLuong + "\t" + tongTien ;
    }
    
}
