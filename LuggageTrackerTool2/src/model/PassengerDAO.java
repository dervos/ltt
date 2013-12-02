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
public class PassengerDAO {

    private final connectivity.DatabaseManager databaseManager = main.LuggageTrackerTool2.getDatabaseManager();

    public PassengerDAO() {
    }

    public List<Passenger> readAll() throws SQLException {
        List<Passenger> list = new LinkedList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String query = "SELECT passengerid, surname, insertion, name, gender, dob, mobphone, homephone, homeaddressid, tempaddressid FROM passenger";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        rs = databaseManager.performSelect(ps);

        while (rs.next()) {
            Passenger tempPassenger = new Passenger();
            tempPassenger.setPassengerid(rs.getInt("passengerid"));
            tempPassenger.setSurname(rs.getString("surname"));
            tempPassenger.setInsertion(rs.getString("insertion"));
            tempPassenger.setName(rs.getString("name"));
            tempPassenger.setGender(rs.getString("gender"));
            tempPassenger.setDob(rs.getDate("dob"));
            tempPassenger.setMobphone(rs.getString("mobphone"));
            tempPassenger.setHomephone(rs.getString("homephone"));
            tempPassenger.setHomeaddressid(rs.getInt("homeaddressid"));
            tempPassenger.setTempaddressid(rs.getInt("tempaddressid"));
            list.add(tempPassenger);
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return list;
    }

    /**
     * Function to search passenger in DB.
     *
     * @param id
     * @return
     */
    public Passenger readById(int id) throws SQLException {
        Passenger passenger = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String query = "SELECT * FROM passenger WHERE passengerid=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, id);

        rs = databaseManager.performSelect(ps);

        if (rs.next()) {
            passenger = new Passenger();
            passenger.setPassengerid(rs.getInt("passengerid"));
            passenger.setSurname(rs.getString("surname"));
            passenger.setInsertion(rs.getString("insertion"));
            passenger.setName(rs.getString("name"));
            passenger.setGender(rs.getString("gender"));
            passenger.setDob(rs.getDate("dob"));
            passenger.setMobphone(rs.getString("mobphone"));
            passenger.setHomephone(rs.getString("homephone"));
            passenger.setHomeaddressid(rs.getInt("homeaddressid"));
            passenger.setTempaddressid(rs.getInt("tempaddressid"));
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return passenger;
    }

    public List<Passenger> readByName(String name) throws SQLException {
        List<Passenger> list = new LinkedList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String query = "SELECT passengerid, surname, insertion, name, gender, dob, mobphone, homephone, homeaddressid, tempaddressid FROM passenger WHERE name=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setString(1, name);

        rs = databaseManager.performSelect(ps);

        while (rs.next()) {
            Passenger tempPassenger = new Passenger();
            tempPassenger.setPassengerid(rs.getInt("passengerid"));
            tempPassenger.setSurname(rs.getString("surname"));
            tempPassenger.setInsertion(rs.getString("insertion"));
            tempPassenger.setName(rs.getString("name"));
            tempPassenger.setGender(rs.getString("gender"));
            tempPassenger.setDob(rs.getDate("name"));
            tempPassenger.setMobphone(rs.getString("mobphone"));
            tempPassenger.setHomephone(rs.getString("homephone"));
            tempPassenger.setHomeaddressid(rs.getInt("homeaddressid"));
            tempPassenger.setTempaddressid(rs.getInt("tempaddressid"));
            list.add(tempPassenger);
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return list;
    }

    /**
     * Function to create a new passenger row in DB
     *
     * @param passenger
     */
    public int create(Passenger passenger) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "INSERT INTO "
                + "passenger (surname, insertion, name, gender, dob, mobphone, homephone, homeaddessid, tempaddressid)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, passenger.getSurname());
        ps.setString(2, passenger.getInsertion());
        ps.setString(3, passenger.getName());
        ps.setString(4, passenger.getGender());
        ps.setDate(5, passenger.getDob());
        ps.setString(6, passenger.getMobphone());
        ps.setString(7, passenger.getHomephone());
        ps.setInt(8, passenger.getHomeaddressid());
        ps.setInt(9, passenger.getTempaddressid());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

    /**
     * Updates passenger row in DB.
     *
     * @param passenger
     */
    public int update(Passenger passenger) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "UPDATE passenger SET "
                + "surname=?,"
                + "insertion=?, "
                + "name=?, "
                + "gender=?, "
                + "dob=?, "
                + "mobphone=?, "
                + "homephone=?, "
                + "homeaddessid=?, "
                + "tempaddressid=? "
                + "WHERE passengerid=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, passenger.getSurname());
        ps.setString(2, passenger.getInsertion());
        ps.setString(3, passenger.getName());
        ps.setString(4, passenger.getGender());
        ps.setDate(5, passenger.getDob());
        ps.setString(6, passenger.getMobphone());
        ps.setString(7, passenger.getHomephone());
        ps.setInt(8, passenger.getHomeaddressid());
        ps.setInt(9, passenger.getTempaddressid());
        ps.setInt(10, passenger.getPassengerid());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

    /**
     * Deletes passenger does NOT delete addresses, yet.
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public int delete(int id) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;
        String query = "DELETE FROM passenger WHERE passengerid=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, id);

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

}
