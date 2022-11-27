/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;
import dinhnghia.HangHoa;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class HangHoaJDBC {
    ConnectToDatabase connectToDatabase=new ConnectToDatabase();
    public ArrayList<HangHoa> getDataHangHoa() throws Exception
    {
        ArrayList<HangHoa> list=new ArrayList<>();
        String sql="select * from HangHoa";
        Connection connection=connectToDatabase.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()) {
            String maHH=resultSet.getString("maHH");
            String tenHangHoa=resultSet.getString("tenHangHoa");
            String nhaCungCap=resultSet.getString("nhaCungCap");
            int soLuong=resultSet.getInt("soLuong");
            int giaNhap=resultSet.getInt("giaNhap");
            int giaBan=resultSet.getInt("giaBan");
            list.add(new HangHoa(maHH, tenHangHoa, nhaCungCap, soLuong, giaNhap, giaBan));
        }
        resultSet.close();
        connection.close();
        return list;
    }
    public void insertIntoDatabase(HangHoa hh)throws Exception
    { 
        Connection connection=connectToDatabase.getConnection();
        String sql="insert into HangHoa values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, hh.getMaHH());
        preparedStatement.setString(2, hh.getTenHangHoa());
        preparedStatement.setString(3, hh.getNhaCungCap());
        preparedStatement.setInt(4, hh.getSoLuong());
        preparedStatement.setInt(5, hh.getGiaNhap());
        preparedStatement.setInt(6, hh.getGiaBan());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tInsert successfully");
        }else{
            System.out.println("\tInsert unsuccessfully");
        }
        connection.close();
    }
    public void delete(HangHoa h) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="delete from HangHoa where maHH=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, h.getMaHH());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tDelete successfully");
        }else{
            System.out.println("\tDelete unsuccessfully");
        }
        connection.close();
    }
    public void edit(HangHoa oleHH,HangHoa newHH) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update HangHoa set maHH=? , tenHangHoa= ?, nhaCungCap=? , soLuong=? , giaNhap=? ,giaBan=? where maHH=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, newHH.getMaHH());
        preparedStatement.setString(2, newHH.getTenHangHoa());
        preparedStatement.setString(3, newHH.getNhaCungCap());
        preparedStatement.setInt(4, newHH.getSoLuong());
        preparedStatement.setInt(5, newHH.getGiaNhap());
        preparedStatement.setDouble(6, newHH.getGiaBan());
        preparedStatement.setString(7, oleHH.getMaHH());
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
