/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BillJDBC {
     ConnectToDatabase connectToDatabase=new ConnectToDatabase();
    public ArrayList<Bill> getData() throws Exception
    {
        ArrayList<Bill> list=new ArrayList<>();
        String sql="select * from HoaDon";
        Connection connection=connectToDatabase.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()) {
            String maHD=resultSet.getString("maHD");
          //  String maHH=resultSet.getString("maHH");
        //    String tenHangHoa=resultSet.getString("tenHangHoa");
            String maNV=resultSet.getString("maNV");
            String thoiGian=resultSet.getString("thoiGian");
            int soLuong=resultSet.getInt("tongSoLuong");
            int tongTien=resultSet.getInt("tongTien");
            list.add(new Bill(maHD, maNV, thoiGian, soLuong, tongTien));
        }
        resultSet.close();
        connection.close();
        return list;
    }
    
    public void insertIntoDatabase(Bill h)throws Exception
    { 
        Connection connection=connectToDatabase.getConnection();
        String sql="insert into HoaDon values(?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,h.getBillID());
       //preparedStatement.setString(2, h.getMaHangHoa());
        //preparedStatement.setString(3, h.getTenHangHoa());
        preparedStatement.setString(2, h.getStaffID());
        preparedStatement.setString(3, h.getTime());
        preparedStatement.setInt(4, h.getQuantity());
        preparedStatement.setInt(5, h.getTotalCosts());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tInsert successfully");
        }else{
            System.out.println("\tInsert unsuccessfully");
        }
        connection.close();
    }
    public void delete(Bill h) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="delete from HoaDon where maHD=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, h.getBillID());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tDelete successfully");
        }else{
            System.out.println("\tDelete unsuccessfully");
        }
        connection.close();
    }
    public void edit(Bill h,Bill x) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update HoaDon set maHD=? , maNV=? , thoiGian=? ,tongSoLuong=?, tongTien=? where maHD=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, x.getBillID());
        preparedStatement.setString(2, x.getStaffID());
        preparedStatement.setString(3, x.getTime());
        preparedStatement.setInt(4, x.getQuantity());
        preparedStatement.setInt(5, x.getTotalCosts());
        preparedStatement.setString(6, h.getBillID());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tEdit successfully");
        }else{
            System.out.println("\tEdit unsuccessfully");
        }
        connection.close();
    }
    public void edit(String ma,Bill x) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update HoaDon set tongSoLuong=?, tongTien=? where maHD=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
    //    preparedStatement.setString(1, x.getMaHD());
//        preparedStatement.setString(2, x.getMaHangHoa());
//        preparedStatement.setString(3, x.getTenHangHoa());
  //      preparedStatement.setString(4, x.getMaNV());
    //    preparedStatement.setString(5, x.getThoiGian());
        preparedStatement.setInt(1, x.getQuantity());
        preparedStatement.setInt(2, x.getTotalCosts());
        preparedStatement.setString(3, ma);
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tEdit successfully");
        }else{
            System.out.println("\tEdit unsuccessfully");
        }
        connection.close();
    }

    public void delete(String maHD)  throws Exception{
         Connection connection=connectToDatabase.getConnection();
        String sql="delete from HoaDon where maHD=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, maHD);
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tDelete successfully");
        }else{
            System.out.println("\tDelete unsuccessfully");
        }
        connection.close();
    }
    public int query(String year,String month,String day)throws Exception{
         Connection connection=connectToDatabase.getConnection();
         int result=0;
        String sql="select sum(tongTien) N'Costs' from HoaDon where year(thoiGian) = ? and month(thoiGian) = ? and day(thoiGian) =?";
       PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, year);
        preparedStatement.setString(2, month);
        preparedStatement.setString(3, day);
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tDelete successfully with: "+row);
        }else{
            System.out.println("\tDelete unsuccessfully");
        }
        
        connection.close();
        return result;
    }
    
  
}
