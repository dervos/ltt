package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author reintjehard, Tomas Slaman
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

        String query = "SELECT luggageid, luggagelabel, description, luggagestatus, storagelocation, differentlocation, dateadded, passengerid FROM luggage;";

        ps = databaseManager.getConnection().prepareStatement(query);
        rs = databaseManager.performSelect(ps);

        while (rs.next()) {
            Luggage tempLuggage = new Luggage();
            tempLuggage.setLuggageid(rs.getInt("luggageid"));
            tempLuggage.setLuggageLabel(rs.getString("luggagelabel"));
            tempLuggage.setDescription(rs.getString("description"));
            tempLuggage.setLuggagestatus(rs.getString("luggagestatus"));
            tempLuggage.setStoragelocation(rs.getString("storagelocation"));
            tempLuggage.setDifferentLocation(rs.getString("differentlocation"));
            tempLuggage.setDateAdded(rs.getDate("dateadded"));
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

        String query = "SELECT luggageid, luggagelabel, description, luggagestatus, storagelocation, differentlocation, dateadded, passengerid FROM luggage WHERE luggageid=?;";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, id);

        rs = databaseManager.performSelect(ps);

        if (rs.next()) {
            tempLuggage = new Luggage();
            tempLuggage.setLuggageid(rs.getInt("luggageid"));
            tempLuggage.setLuggageLabel(rs.getString("luggagelabel"));
            tempLuggage.setDescription(rs.getString("description"));
            tempLuggage.setLuggagestatus(rs.getString("luggagestatus"));
            tempLuggage.setStoragelocation(rs.getString("storagelocation"));
            tempLuggage.setDifferentLocation(rs.getString("differentlocation"));
            tempLuggage.setDateAdded(rs.getDate("dateadded"));
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

        String query = "SELECT luggageid, luggagelabel, description, luggagestatus, storagelocation, differentlocation, dateadded, passengerid FROM luggage WHERE passengerid=?;";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, passengerid);

        rs = databaseManager.performSelect(ps);

        while (rs.next()) {
            Luggage tempLuggage = new Luggage();
            tempLuggage.setLuggageid(rs.getInt("luggageid"));
            tempLuggage.setLuggageLabel(rs.getString("luggagelabel"));
            tempLuggage.setDescription(rs.getString("description"));
            tempLuggage.setLuggagestatus(rs.getString("luggagestatus"));
            tempLuggage.setStoragelocation(rs.getString("storagelocation"));
            tempLuggage.setDifferentLocation(rs.getString("differentlocation"));
            tempLuggage.setDateAdded(rs.getDate("dateadded"));
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

        String query = "INSERT INTO `luggage` (`luggagelabel`, `description`, `luggagestatus`, `storagelocation`, `differentlocation`) "
                + "VALUES(?, ?, ?, ?, ?);";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, luggage.getLuggageLabel());
        ps.setString(2, luggage.getDescription());
        ps.setString(3, luggage.getLuggagestatus().name());
        ps.setString(4, luggage.getStoragelocation());
        ps.setString(5, luggage.getDifferentLocation());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

    public static int update(Luggage luggage) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "UPDATE luggage SET "
                + "luggagelabel=?, "
                + "description=?, "
                + "luggagestatus=?, "
                + "storagelocation=?, "
                + "differentlocation=?, "
                + "passengerid=? "
                + "WHERE luggageid=?;";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, luggage.getLuggageLabel());
        ps.setString(2, luggage.getDescription());
        ps.setString(3, luggage.getLuggagestatus().name());
        ps.setString(4, luggage.getStoragelocation());
        ps.setString(5, luggage.getDifferentLocation());
        if (luggage.getPassengerid() != 0) {
            ps.setInt(6, (Integer) luggage.getPassengerid());
        } else {
            ps.setNull(6, Types.INTEGER);
        }
        ps.setInt(7, luggage.getLuggageid());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }
    
    public static int updatePassengerIDToNull(int id) throws SQLException
    {
        int rowsAffected;
        PreparedStatement ps = null;
        String query = "UPDATE luggage SET " 
                + "passengerid=? "
                + "WHERE passengerid=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setNull(1, Types.INTEGER);
        ps.setInt(2, id);

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

    public static int delete(int luggageid) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;
        String query = "DELETE FROM luggage WHERE luggageid=?;";

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

    public static int readMissing() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as `missing` "
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'";

            ResultSet rs = databaseManager.doQuery(query);

            System.out.println(rs);

            if (rs.next()) {
                missing = rs.getInt("missing");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            databaseManager.closeConnection();
        }
        return missing;
    }

    public static int readReturned() {
        int returned = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as `returned` "
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'";

            ResultSet rs = databaseManager.doQuery(query);

            System.out.println(rs);

            if (rs.next()) {
                returned = rs.getInt("returned");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            databaseManager.closeConnection();
        }
        return returned;
    }

    public static int readDestroyed() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as `destroyed` "
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'";

            ResultSet rs = databaseManager.doQuery(query);

            System.out.println(rs);

            if (rs.next()) {
                destroyed = rs.getInt("destroyed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            databaseManager.closeConnection();
        }
        return destroyed;
    }

    public static int readTotalLuggage() {
        int totalLuggage = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT COUNT(*) AS luggageid FROM luggage";

            ResultSet rs = databaseManager.doQuery(query);

            System.out.println(rs);

            if (rs.next()) {
                totalLuggage = rs.getInt("LuggageId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            databaseManager.closeConnection();
        }
        return totalLuggage;
    }
}
