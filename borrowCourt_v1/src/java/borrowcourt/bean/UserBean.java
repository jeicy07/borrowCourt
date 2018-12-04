/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrowcourt.bean;

/**
 *
 * @author qiaofuli
 */
public class UserBean {
    private int id;
    private String username;
    private String password;
    private String name;
    private String telephone;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public UserBean(int id, String username, String password, String name, String telephone) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
    }
    public UserBean() {
        super();
    }
    @Override
    public String toString() {
        return "UserBean [id=" + id + ". username =" + username + ", password=" + password + ", name = "+ name + ", telephone =" +telephone + "]";
        
    }
  }        

