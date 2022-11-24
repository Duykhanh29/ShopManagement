/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhnghia;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanVien {
    ArrayList<String> dSachChucVu=new ArrayList<>();
    public void listChucVu()
    {
        dSachChucVu.add("Nhan vien ban hang");
        dSachChucVu.add("Nhan vien kho");
        dSachChucVu.add("Quan ly");
    }
    private String maNV,tenNV,chucVu,matKhau,soDienThoai,ngaySinh;
    
    public void setdSachChucVu(ArrayList<String> dSachChucVu) {
        this.dSachChucVu = dSachChucVu;
    }

    public ArrayList<String> getdSachChucVu() {
        return dSachChucVu;
    }

    public NhanVien() {
        listChucVu();
    }

    public NhanVien(String maNV, String tenNV, String chucVu, String matKhau, String soDienThoai, String ngaySinh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
       // this.gioiTinh=gioiTinh;
        this.chucVu = chucVu;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        listChucVu();
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Override
    public String toString() {
        return  maNV + "\t" + tenNV + "\t" + chucVu + "\t" + matKhau + "\t" + soDienThoai + "\t" + ngaySinh;
    }
    
}
