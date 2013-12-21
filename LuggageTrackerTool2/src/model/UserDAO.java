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

    private static final connectivity.DatabaseManager databaseManager = main.LuggageTrackerTool2.getDatabaseManager();

    public UserDAO() {
    }

    public static List<User> readAll() throws SQLException {
        List<User> list = new LinkedList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        String query = "SELECT * FROM User;";
        
        databaseManager.openConnection();
        
        ps = databaseManager.getConnection().prepareStatement(query);
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
    
    public static User readById(int id) throws SQLException {
        User tempUser = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        String query = "SELECT * FROM User WHERE userid=?;";
        
        databaseManager.openConnection();
        
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
    
    public static User readByUsername(String username) throws SQLException {
        User tempUser = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        String query = "SELECT * FROM User WHERE username=?;";
        
        databaseManager.openConnection();
        
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
    
    public static int create(User user) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "INSERT INTO User (username, password, privileges)"
                + "VALUES(?, ?, ?);";
        
        databaseManager.openConnection();

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
    
    public static int update(User user) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "username=?, "
                + "password=?, "
                + "privileges=? "
                + "WHERE userid=?;";
        
        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getPrivileges());
        ps.setInt(4, user.getUserid());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        
        return rowsAffected;
    }
    
    public static int delete(int userid) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;
        
        String query = "DELETE FROM User WHERE userid=?;";
        
        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, userid);

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }
    
    public static boolean userExistsByUsername(String username) throws SQLException
    {
        int count = 1;
        PreparedStatement ps = null;
        
        String query = "SELECT COUNT(*) as `users` FROM User WHERE username = '" + username + "';";
        
        databaseManager.openConnection();
        
        ResultSet rs = databaseManager.doQuery(query);
        if (rs.next())
            count = rs.getInt("users");
        //count = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        
        return count > 0;
    }
}
