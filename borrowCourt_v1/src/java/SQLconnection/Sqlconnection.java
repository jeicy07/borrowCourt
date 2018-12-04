/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author qiaofuli
 */
@ManagedBean
public class Sqlconnection {
     static public Connection con=null;

    public Sqlconnection() {
    }
    
    public static Connection getConnection(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String url ="jdbc:mysql://localhost:3306/person?useUnicode=true&characterEncoding=utf-8";
        con = con=DriverManager.getConnection(url,"root","13674351926qfl");
        }catch(Exception e){
            System.out.println(e.toString());
        }
        
        return con;
    }
    
    public static void closeCon(){
        try{
            con.close();
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
        con = null;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
