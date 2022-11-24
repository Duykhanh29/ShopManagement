/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhnghia;

/**
 *
 * @author Admin
 */
public class HangHoa {
    private String maHH,tenHangHoa,nhaCungCap;
    private int soLuong,giaNhap,giaBan;

    public HangHoa() {
    }

    public HangHoa(String maHH, String tenHangHoa, String nhaCungCap, int soLuong, int giaNhap, int giaBan) {
        this.maHH = maHH;
        this.tenHangHoa = tenHangHoa;
        this.nhaCungCap = nhaCungCap;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
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

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }


    @Override
    public String toString() {
        return maHH + "\t" + tenHangHoa + "\t" + nhaCungCap + "\t" + soLuong + "\t" + giaNhap + "\t" + giaBan ;
    }
    
   
}
