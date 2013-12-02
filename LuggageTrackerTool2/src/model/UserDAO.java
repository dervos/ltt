/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author reintjehard
 */
public class UserDAO {

    private final connectivity.DatabaseManager databaseManager = main.LuggageTrackerTool2.getDatabaseManager();

    public UserDAO() {
    }

    public List<User> readAll() throws SQLException {
        List<User> list = new LinkedList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        String query = "SELECT * FROM User;";
        
        databaseManager.openConnection();
        
        databaseManager.getConnection().prepareStatement(query);
        rs = databaseManager.performSelect(ps);
        
        while (rs.next()) {
            User user = new User();
            user.setUserid(rs.getInt("userid"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setPrivileges(rs.getString("privileges"));
            list.add(user);
        }
        
        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        
        return list;
    }
    
    public User readById(int id) throws SQLException {
        User tempUser = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        String query = "SELECT * FROM User WHERE userid=?;";
        
        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        
        rs = databaseManager.performSelect(ps);
        
        if (rs.next()) {
            tempUser = new User();
            tempUser.setUserid(rs.getInt("userid"));
            tempUser.setUsername(rs.getString("username"));
            tempUser.setPassword(rs.getString("password"));
            tempUser.setPrivileges(rs.getString("privileges"));
        }
        
        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        return tempUser;
    }
    
    public User readByUsername(String username) throws SQLException {
        User tempUser = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        String query = "SELECT * FROM User WHERE userid=?;";
        
        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setString(1, username);
        
        rs = databaseManager.performSelect(ps);
        
        if (rs.next()) {
            tempUser = new User();
            tempUser.setUserid(rs.getInt("userid"));
            tempUser.setUsername(rs.getString("username"));
            tempUser.setPassword(rs.getString("password"));
            tempUser.setPrivileges(rs.getString("privileges"));
        }
        
        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        return tempUser;
    }
    
    public int create(User user) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "INSERT INTO User (username, password, privileges)"
                + "VALUES(?, ?, ?);";

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getPrivileges());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        return rowsAffected;
    }
    
    public int update(User user) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "username=?, "
                + "password=?, "
                + "privileges=? "
                + "WHERE userid=?;";

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getPrivileges());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        
        return rowsAffected;
    }
    
    public int delete(int userid) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;
        String query = "DELETE FROM User WHERE userid=?;";

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, userid);

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }
}
