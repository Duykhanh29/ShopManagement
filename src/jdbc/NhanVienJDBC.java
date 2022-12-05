/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import dinhnghia.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanVienJDBC {
    ConnectToDatabase connectToDatabase=new ConnectToDatabase();
    public ArrayList<NhanVien> getDataNhanVien() throws Exception
    {
        ArrayList<NhanVien> list=new ArrayList<>();
        String sql="select * from NhanVien";
        Connection connection=connectToDatabase.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()) {
            String maNV=resultSet.getString("maNV");
            String tenNV=resultSet.getString("tenNV");
            String chucVu=resultSet.getString("chucVu");
            String matKhau=resultSet.getString("matKhau");
            String soDienThoai=resultSet.getString("soDienThoai");
            String ngaySinh=resultSet.getString("ngaySinh");
            list.add(new NhanVien(maNV, tenNV, chucVu, matKhau, soDienThoai, ngaySinh));
        }
        resultSet.close();
        connection.close();
        return list;
    }
    public void insertIntoDatabase(NhanVien nv)throws Exception
    { 
        Connection connection=connectToDatabase.getConnection();
        String sql="insert into NhanVien values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,nv.getMaNV());
        preparedStatement.setString(2, nv.getTenNV());
        preparedStatement.setString(3, nv.getChucVu());
        preparedStatement.setString(4, nv.getMatKhau());
        String[] text=nv.getNgaySinh().split("-");
        System.out.println(nv.getNgaySinh());
        System.out.println("Check");
        for (String string : text) {
            System.out.println(string);
        }
        String DoB=text[2].concat("-").concat(text[1]).concat("-").concat(text[0]);
        preparedStatement.setString(6, DoB);
        preparedStatement.setString(5, nv.getSoDienThoai());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tInsert successfully");
        }else{
            System.out.println("\tInsert unsuccessfully");
        }
        connection.close();
    }
    public void delete(NhanVien h) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="delete from NhanVien where maNV=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, h.getMaNV());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tDelete successfully");
        }else{
            System.out.println("\tDelete unsuccessfully");
        }
        connection.close();
    }
    public void edit(NhanVien oldNV,NhanVien newNV) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update NhanVien set maNV=? , tenNV= ?, chucVu=? , matKhau=? , soDienThoai=? ,ngaySinh=? where maNV=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, newNV.getMaNV());
        preparedStatement.setString(2, newNV.getTenNV());
        preparedStatement.setString(3, newNV.getChucVu());
        preparedStatement.setString(4, newNV.getMatKhau());
        preparedStatement.setString(5, newNV.getSoDienThoai());
        String[] text=newNV.getNgaySinh().split("-");
        String DoB=text[2].concat("-").concat(text[1]).concat("-").concat(text[0]);
        preparedStatement.setString(6, DoB);
        preparedStatement.setString(7, oldNV.getMaNV());
        
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tEdit successfully");
        }else{
            System.out.println("\tEdit unsuccessfully");
        }
        connection.close();
    }
    public void changePassword(String maNV,String newPassword) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update NhanVien set matKhau=? where maNV=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, newPassword);
        preparedStatement.setString(2, maNV);
        
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tEdit successfully");
        }else{
            System.out.println("\tEdit unsuccessfully");
        }
        connection.close();
    }
}
