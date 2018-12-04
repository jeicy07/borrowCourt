/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowcourt.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import SQLconnection.Sqlconnection;
import static SQLconnection.Sqlconnection.con;
import borrowcourt.bean.UserBean;
import java.io.UnsupportedEncodingException;



/**
 *
 * @author qiaofuli
 */
public class LoginServlet extends HttpServlet {
    public void wrong1(){//对话框提示信息
        String msg="用户名不能为空!";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong2(){
        String msg="用户密码不能为空，登录失败!";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong3(){
        String msg="该用户尚未注册，登录失败!";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong4(){
        String msg="用户密码不正确，登录失败!";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = new String(request.getParameter("username")) ;
        String password = new String(request.getParameter("password"));
        if(username.equals("")){
            wrong1();
            response.sendRedirect("http://localhost:8080/borrowcourt_v1/login.jsp");   
        }else if(password.equals("")){
            wrong2();
            response.sendRedirect("http://localhost:8080/borrowcourt_v1/login.jsp");
        }else{
            try{
                con=null;
                Statement stmt=null;
                ResultSet rs = null;
                con = Sqlconnection.getConnection();
                stmt = con.createStatement();
                String sql="select * from user where username ='"+username+"'";
                rs = stmt.executeQuery(sql);
                int N=0;
                int P=0;
                while(rs.next()){
                    if(username.equals(rs.getString("username"))){
                        N=111111;
                        if(password.equals(rs.getString("password"))){
                            P=11111;
                            UserBean nn = new UserBean();
                            nn.setUsername(username);
                            nn.setPassword(password);
                            HttpSession session = request.getSession();
                            ArrayList login = new ArrayList();
                            login.add(nn);
                            session.setAttribute("login", login);
                            response.sendRedirect("http://localhost:8080/borrowcourt_v1/main/main.jsp");
                            
                        }else{
                            
                        }
                    }else{
                        N++;
                    }
                }
                if(N<111111){
                    wrong3();
                    response.sendRedirect("login.jsp");
                    
                }else if(P<111111){
                    wrong4();
                    response.sendRedirect("login.jsp");       
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request, response);
    }
//    protected void doPost (HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        
//        String html = null;
//        
//        if ("admin".equals(username) && "123".equals(password))
//            html = "<div style = 'color:green'>success</div>";
//        else
//            html = "<div style = 'color:red'>fail</div>";
//        
//        PrintWriter pw = response.getWriter();
//        pw.println(html);
//        System.out.println("username:" + username);
//        System.out.println("password:" + password);
//    }
        
}
