/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class ConnectToDatabase {

    public Connection getConnection() throws Exception {
        {
            String sqlInstanceName = "SQLEXPRESS";
            String database = "QuanLyCuaHang";
            String userName = "sa";
            String passWord = "Duykhanh02092002";
            String serverName = "DESKTOP-LD1RM1O";
            //     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + serverName + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + database + ";encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
            Connection connection = DriverManager.getConnection(url, userName, passWord);
            return connection;
        }
    }
    
}
