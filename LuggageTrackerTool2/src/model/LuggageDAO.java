package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author reintjehard
 */
public class LuggageDAO {

    private static final connectivity.DatabaseManager databaseManager = main.LuggageTrackerTool2.getDatabaseManager();

    public LuggageDAO() {
    }

    public static List<Luggage> readAll() throws SQLException {
        List<Luggage> list = new LinkedList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        databaseManager.openConnection();

        String query = "SELECT luggageid, description, storagelocation, luggagestatus, passengerid FROM Luggage;";

        ps = databaseManager.getConnection().prepareStatement(query);
        rs = databaseManager.performSelect(ps);

        while (rs.next()) {
            Luggage tempLuggage = new Luggage();
            tempLuggage.setLuggageid(rs.getInt("luggageid"));
            tempLuggage.setDescription(rs.getString("description"));
            tempLuggage.setStoragelocation(rs.getString("storagelocation"));
            tempLuggage.setLuggagestatus(rs.getString("luggagestatus"));
            tempLuggage.setPassenger(rs.getInt("passengerid"));
            list.add(tempLuggage);
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return list;
    }

    public static Luggage readById(int id) throws SQLException {
        Luggage tempLuggage = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String query = "SELECT luggageid, description, storagelocation, luggagestatus, passengerid FROM Luggage WHERE luggageid=?;";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, id);

        rs = databaseManager.performSelect(ps);

        if (rs.next()) {
            tempLuggage = new Luggage();
            tempLuggage.setLuggageid(rs.getInt("luggageid"));
            tempLuggage.setDescription(rs.getString("description"));
            tempLuggage.setStoragelocation(rs.getString("storagelocation"));
            tempLuggage.setLuggagestatus(rs.getString("luggagestatus"));
            tempLuggage.setPassenger(rs.getInt("passengerid"));
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return tempLuggage;
    }

    public static List<Luggage> readByPersonid(int passengerid) throws SQLException {
        List<Luggage> list = new LinkedList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        String query = "SELECT luggageid, description, storagelocation, luggagestatus, passengerid FROM Luggage WHERE passengerid=?;";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, passengerid);

        rs = databaseManager.performSelect(ps);

        while (rs.next()) {
            Luggage tempLuggage = new Luggage();
            tempLuggage.setLuggageid(rs.getInt("luggageid"));
            tempLuggage.setDescription(rs.getString("description"));
            tempLuggage.setStoragelocation(rs.getString("storagelocation"));
            tempLuggage.setLuggagestatus(rs.getString("luggagestatus"));
            tempLuggage.setPassenger(rs.getInt("passengerid"));
            list.add(tempLuggage);
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        return list;
    }

    public static int create(Luggage luggage) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "INSERT INTO `Luggage` (`description`, `storagelocation`, `luggagestatus`, `passengerid`) "
                + "VALUES(?, ?, ?, ?);";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, luggage.getDescription());
        ps.setString(2, luggage.getStoragelocation());
        ps.setString(3, luggage.getLuggagestatus());
        ps.setInt(4, luggage.getPassengerid());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

    public static int update(Luggage luggage) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "UPDATE Luggage SET "
                + "description=?, "
                + "storagelocation=?, "
                + "luggagestatus=?, "
                + "passengerid=? "
                + "WHERE luggageid=?;";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, luggage.getDescription());
        ps.setString(2, luggage.getStoragelocation());
        ps.setString(3, luggage.getLuggagestatus());
        ps.setInt(4, luggage.getPassengerid());
        ps.setInt(5, luggage.getLuggageid());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

    public static int delete(int luggageid) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;
        String query = "DELETE FROM Luggage WHERE luggageid=?;";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, luggageid);

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }
        return rowsAffected;
    }

    public static int readFound() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as `found` "
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'";


            ResultSet rs = databaseManager.doQuery(query);

            System.out.println(rs);

            if (rs.next()) {
                found = rs.getInt("found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            databaseManager.closeConnection();
        }
        return found;
    }

    public static int readLost() {
        int lost = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as `lost` "
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Lost'";


            ResultSet rs = databaseManager.doQuery(query);

            System.out.println(rs);

            if (rs.next()) {
                lost = rs.getInt("lost");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            databaseManager.closeConnection();
        }
        return lost;
    }

    public static int readDone() {
        int done = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as `done` "
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Done'";


            ResultSet rs = databaseManager.doQuery(query);

            System.out.println(rs);

            if (rs.next()) {
                done = rs.getInt("done");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            databaseManager.closeConnection();
        }
        return done;
    }
}

