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
    private String username, password;
    private final static User admin = new User("admin", "admin");

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public static User getAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + '}';
    }
    
    
    
}
