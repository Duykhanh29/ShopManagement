/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import dinhnghia.ChiTietHoaDon;
import dinhnghia.HangHoa;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonJDBC {
    ConnectToDatabase connectToDatabase=new ConnectToDatabase();
     public ArrayList<ChiTietHoaDon> getDataChiTietHoaDon() throws Exception
    {
        ArrayList<ChiTietHoaDon> list=new ArrayList<>();
        String sql="select * from ChiTietHoaDon";
        Connection connection=connectToDatabase.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()) {
            String maHD=resultSet.getString("maHD");
            String maHH=resultSet.getString("maHH");
            String tenHangHoa=resultSet.getString("tenHangHoa");
            int soLuongHH=resultSet.getInt("soLuongHH");
            int giaBan=resultSet.getInt("giaBan");
            int tongSoTien=resultSet.getInt("tongSoTien");
            list.add(new ChiTietHoaDon(maHH, maHH, tenHangHoa, soLuongHH, giaBan, tongSoTien));
        }
        resultSet.close();
        connection.close();
        return list;
    }
    public void insertIntoDatabase(ChiTietHoaDon c)throws Exception
    { 
        Connection connection=connectToDatabase.getConnection();
        String sql="insert into ChiTietHoaDon values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, c.getMaHD());
        preparedStatement.setString(2, c.getMaHH());
        preparedStatement.setString(3, c.getTenHangHoa());
        preparedStatement.setInt(4, c.getSoLuong());
        preparedStatement.setInt(5, c.getGiaBan());
        preparedStatement.setInt(6, c.getTongTien());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tInsert successfully");
        }else{
            System.out.println("\tInsert unsuccessfully");
        }
        connection.close();
    }
    public void delete(ChiTietHoaDon c) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="delete from ChiTietHoaDon where maHD=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, c.getMaHD());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tDelete successfully");
        }else{
            System.out.println("\tDelete unsuccessfully");
        }
        connection.close();
    }
    public void edit(ChiTietHoaDon h,ChiTietHoaDon x) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update ChiTietHoaDon set maHD=? , maHH= ?, tenHangHoa=? , soLuongHH=? , giaBan=? ,tongSoTien=? where maHD=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, x.getMaHD());
        preparedStatement.setString(2, x.getMaHH());
        preparedStatement.setString(3, x.getTenHangHoa());
        preparedStatement.setInt(4, x.getSoLuong());
        preparedStatement.setInt(5, x.getGiaBan());
        preparedStatement.setDouble(6, x.getTongTien());
        preparedStatement.setString(7, h.getMaHD());
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
