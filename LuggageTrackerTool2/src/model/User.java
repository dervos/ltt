/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author reintjehard
 */
public class User {

    public static User getAdmin() {
        return new User(Integer.SIZE, "admin", "admin", "all");
    }

    private Integer userid;

    private String username;

    private String password;

    private String privileges;

    public User() {
    }

    public User(Integer userid) {
        this.userid = userid;
    }

    public User(Integer userid, String privileges) {
        this.userid = userid;
        this.privileges = privileges;
    }

    public User(Integer userid, String username, String password, String privileges) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.privileges = privileges;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return "User{" + "userid=" + userid + ", username=" + username + ", password=dacht het niet, privileges=" + privileges + '}';
    }

}
