/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowcourt.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import borrowcourt.bean.UserBean;
import borrowcourt.util.DBUtil;
/**
 *
 * @author qiaofuli
 */
public class ServerMain extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取客户端发来的用户名和密码
        String username = req.getParameter("loginName");
        String password = req.getParameter("password");
        //打印流
        PrintWriter pw = resp.getWriter();
        
        Connection con = null;
        PreparedStatement pStmt = null;
        ResultSet resultSet = null;
        UserBean user = null;
        
        try {
            con = DBUtil.getConnection();//与数据库相连
            String sql = "select * from user where username = ? and password = ?";
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            pStmt.setString(2, password);
            resultSet = pStmt.executeQuery();
            
            if(resultSet.next()){
                user = new UserBean();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setTelephone(resultSet.getString("telephone"));
                
                
            }
            if(user != null){
                //登录成功
                pw.print("Login Success");
                
            }else {
                //登录失败
                pw.print("Login Failed");
                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //最后需要把所有资源关闭
            pw.close();
            try {
                DBUtil.closeAll(con, pStmt, resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        
    }
    
}
