/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowcourt.test;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import SQLconnection.Sqlconnection;
import static SQLconnection.Sqlconnection.con;
/**
 *
 * @author qiaofuli
 */
public class RegisterServlet {
    public void wrong1(){
        String msg="不允许有空，注册失败！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong2(){
        String msg="两次密码不同，注册失败！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong3(){
        String msg="用户名已存在，注册失败！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void right(){
        String msg="注册信息合格，注册成功！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        String username=new String(request.getParameter("username"));
        String password1=new String(request.getParameter("password1"));
        String password2=new String(request.getParameter("password2"));
        String name=new String(request.getParameter("name"));
        String telephone=new String(request.getParameter("telephone"));
        if(username.length()==0||password1.length()==0||password2.length()==0||name.length()==0||telephone.length()==0){
            wrong1();
            response.sendRedirect("http://localhost:8080/borrowcourt_v1/register/register.jsp");
        }else if(!(password1.equals(password2))){
            wrong2();
            response.sendRedirect("http://localhost:8080/borrowcourt_v1/register/register.jsp");
        }else{
            try{
                con=null;
                Statement stmt=null;
                ResultSet rs=null;
                con = Sqlconnection.getConnection();
                stmt=con.createStatement();
                String sql1="select * from user where userName='"+username+"'";
                rs=stmt.executeQuery(sql1);
                rs.last();
                int k;
                k=rs.getRow();
                if(k>0){
                    wrong3();
                    response.sendRedirect("http://localhost:8080/PIMS/register/register.jsp");
                }else{
                    String sql2="insert into user"+"(username,password,name,telephone)"+"values("+"'"+username+"'"+","+"'"+password1+"'"+","+"'"+name+"'"+","+"'"+telephone+"'"+")";
                    stmt.executeUpdate(sql2);
                }
                rs.close();
                stmt.close();
                Sqlconnection.closeCon();
                con = null;
                //right();
                response.sendRedirect("http://localhost:8080/PIMS/login.jsp");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    
    
}
