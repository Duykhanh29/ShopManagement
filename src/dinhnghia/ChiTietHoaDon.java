/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhnghia;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDon {
    private String maHD,maHH,tenHangHoa;
    private int soLuong,giaBan,tongTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maHD, String maHH, String tenHangHoa, int soLuong, int giaBan, int tongTien) {
        this.maHD = maHD;
        this.maHH = maHH;
        this.tenHangHoa = tenHangHoa;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaHH() {
        return maHH;
    }

    public void setMaHH(String maHH) {
        this.maHH = maHH;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return  maHD + "\t" + maHH + "\t" + tenHangHoa + "\t" + soLuong + "\t" + giaBan + "\t" + tongTien;
    }
    
}
