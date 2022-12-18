/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;
import model.Goods;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class GoodsJDBC {
    ConnectToDatabase connectToDatabase=new ConnectToDatabase();
    public ArrayList<Goods> getData() throws Exception
    {
        ArrayList<Goods> list=new ArrayList<>();
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
            list.add(new Goods(maHH, tenHangHoa, nhaCungCap, soLuong, giaNhap, giaBan));
        }
        resultSet.close();
        connection.close();
        return list;
    }
    public void insertIntoDatabase(Goods hh)throws Exception
    { 
        Connection connection=connectToDatabase.getConnection();
        String sql="insert into HangHoa values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, hh.getGoodsID());
        preparedStatement.setString(2, hh.getGoodsName());
        preparedStatement.setString(3, hh.getProducer());
        preparedStatement.setInt(4, hh.getQuantity());
        preparedStatement.setInt(5, hh.getImportedCost());
        preparedStatement.setInt(6, hh.getSellingCost());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tInsert successfully");
        }else{
            System.out.println("\tInsert unsuccessfully");
        }
        connection.close();
    }
    public void delete(Goods h) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="delete from HangHoa where maHH=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, h.getGoodsID());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tDelete successfully");
        }else{
            System.out.println("\tDelete unsuccessfully");
        }
        connection.close();
    }
    public void edit(Goods oldHH,Goods newHH) throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update HangHoa set maHH=? , tenHangHoa= ?, nhaCungCap=? , soLuong=? , giaNhap=? ,giaBan=? where maHH=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, newHH.getGoodsID());
        preparedStatement.setString(2, newHH.getGoodsName());
        preparedStatement.setString(3, newHH.getProducer());
        preparedStatement.setInt(4, newHH.getQuantity());
        preparedStatement.setInt(5, newHH.getImportedCost());
        preparedStatement.setDouble(6, newHH.getSellingCost());
        preparedStatement.setString(7, oldHH.getGoodsID());
        int row=preparedStatement.executeUpdate();
        if(row!=0)
        {
            System.out.println("\tEdit successfully");
        }else{
            System.out.println("\tEdit unsuccessfully");
        }
        connection.close();
    } 
    public void update(String code,int oldQuantity,int newQuantity)throws Exception{
        Connection connection=connectToDatabase.getConnection();
        String sql="update HangHoa set soLuong=? where maHH=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, oldQuantity-newQuantity);
        preparedStatement.setString(2, code);
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
