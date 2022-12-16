/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import model.BillDetails;
import model.Goods;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonJDBC {
    ConnectToDatabase connectToDatabase=new ConnectToDatabase();
     public ArrayList<BillDetails> getDataChiTietHoaDon() throws Exception
    {
        ArrayList<BillDetails> list=new ArrayList<>();
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
            list.add(new BillDetails(maHH, maHH, tenHangHoa, soLuongHH, giaBan, tongSoTien));
        }
        resultSet.close();
        connection.close();
        return list;
    }
    public void insertIntoDatabase(BillDetails c)throws Exception
    { 
        Connection connection=connectToDatabase.getConnection();
        String sql="insert into ChiTietHoaDon values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, c.getBillID());
        preparedStatement.setString(2, c.getGoodsID());
        preparedStatement.setString(3, c.getGoodsName());
        preparedStatement.setInt(4, c.getQuantity());
        preparedStatement.setInt(5, c.getSellingCost());
        preparedStatement.setInt(6, c.getTotalCosts());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tInsert successfully");
        }else{
            System.out.println("\tInsert unsuccessfully");
        }
        connection.close();
    }
    public void delete(BillDetails c) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="delete from ChiTietHoaDon where maHD=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, c.getBillID());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tDelete successfully");
        }else{
            System.out.println("\tDelete unsuccessfully");
        }
        connection.close();
    }
    public void edit(BillDetails oldH,BillDetails newH) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update ChiTietHoaDon set maHD=? , maHH= ?, tenHangHoa=? , soLuongHH=? , giaBan=? ,tongSoTien=? where maHD=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, newH.getBillID());
        preparedStatement.setString(2, newH.getGoodsID());
        preparedStatement.setString(3, newH.getGoodsName());
        preparedStatement.setInt(4, newH.getQuantity());
        preparedStatement.setInt(5, newH.getSellingCost());
        preparedStatement.setDouble(6, newH.getTotalCosts());
        preparedStatement.setString(7, oldH.getBillID());
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
