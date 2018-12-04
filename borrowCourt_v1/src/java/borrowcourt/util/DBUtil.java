/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowcourt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 *
 * @author qiaofuli
 */
public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "borrowCourt";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "admin";
    
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("DBConfig");
        try {
            Class.forName(bundle.getString("com.mysql.jdbc.Driver"));
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
        
    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
    
    public static void closeAll(Connection connection,PreparedStatement pStmt, ResultSet resultSet) throws SQLException{
        if(connection != null){
            connection.close();
        }
        if(pStmt != null){
            pStmt.close();
        }
        if(resultSet != null){
            resultSet.close();
        }
    }
        
    
}
