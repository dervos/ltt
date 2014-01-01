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

    //LUGGAGE OVERVIEW MONTHS 2014
    
    public static int readFoundJanuary2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-01-1 00:00:00' AND '2014-01-31 00:00:00'";

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

    public static int readMissingJanuary2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-01-1 00:00:00' AND '2014-01-31 00:00:00'";

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

    public static int readReturnedJanuary2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-01-1 00:00:00' AND '2014-01-31 00:00:00'";

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

    public static int readDestroyedJanuary2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-01-1 00:00:00' AND '2014-01-31 00:00:00'";

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

        public static int readFoundFebruary2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-02-1 00:00:00' AND '2014-02-28 00:00:00'";

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

    public static int readMissingFebruary2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-02-1 00:00:00' AND '2014-02-28 00:00:00'";

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

    public static int readReturnedFebruary2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-02-1 00:00:00' AND '2014-02-28 00:00:00'";

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

    public static int readDestroyedFebruary2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-02-1 00:00:00' AND '2014-02-28 00:00:00'";

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
    
    public static int readFoundMarch2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-03-1 00:00:00' AND '2014-03-31 00:00:00'";

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

    public static int readMissingMarch2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-03-1 00:00:00' AND '2014-03-31 00:00:00'";

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

    public static int readReturnedMarch2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-03-1 00:00:00' AND '2014-03-31 00:00:00'";

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

    public static int readDestroyedMarch2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-03-1 00:00:00' AND '2014-03-31 00:00:00'";

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
    
    public static int readFoundApril2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-04-1 00:00:00' AND '2014-04-30 00:00:00'";

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

    public static int readMissingApril2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-04-1 00:00:00' AND '2014-04-30 00:00:00'";

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

    public static int readReturnedApril2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-04-1 00:00:00' AND '2014-04-30 00:00:00'";

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

    public static int readDestroyedApril2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-04-1 00:00:00' AND '2014-04-30 00:00:00'";

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
    
    public static int readFoundMay2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-05-1 00:00:00' AND '2014-05-31 00:00:00'";

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

    public static int readMissingMay2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-05-1 00:00:00' AND '2014-05-31 00:00:00'";

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

    public static int readReturnedMay2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-05-1 00:00:00' AND '2014-05-31 00:00:00'";

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

    public static int readDestroyedMay2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-05-1 00:00:00' AND '2014-05-31 00:00:00'";

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
    
    public static int readFoundJune2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-06-1 00:00:00' AND '2014-06-30 00:00:00'";

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

    public static int readMissingJune2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-06-1 00:00:00' AND '2014-06-30 00:00:00'";

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

    public static int readReturnedJune2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-06-1 00:00:00' AND '2014-06-30 00:00:00'";

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

    public static int readDestroyedJune2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-06-1 00:00:00' AND '2014-06-30 00:00:00'";

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
    
    public static int readFoundJuly2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-07-1 00:00:00' AND '2014-07-31 00:00:00'";

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

    public static int readMissingJuly2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-07-1 00:00:00' AND '2014-07-31 00:00:00'";

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

    public static int readReturnedJuly2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-07-1 00:00:00' AND '2014-07-31 00:00:00'";

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

    public static int readDestroyedJuly2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-07-1 00:00:00' AND '2014-07-31 00:00:00'";

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
    
    public static int readFoundAugust2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-08-1 00:00:00' AND '2014-08-31 00:00:00'";

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

    public static int readMissingAugust2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-08-1 00:00:00' AND '2014-08-31 00:00:00'";

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

    public static int readReturnedAugust2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-08-1 00:00:00' AND '2014-08-31 00:00:00'";

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

    public static int readDestroyedAugust2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-08-1 00:00:00' AND '2014-08-31 00:00:00'";

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
    
    public static int readFoundSeptember2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-09-1 00:00:00' AND '2014-09-30 00:00:00'";

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

    public static int readMissingSeptember2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-09-1 00:00:00' AND '2014-09-30 00:00:00'";

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

    public static int readReturnedSeptember2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-09-1 00:00:00' AND '2014-09-30 00:00:00'";

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

    public static int readDestroyedSeptember2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-09-1 00:00:00' AND '2014-09-30 00:00:00'";

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
    
    public static int readFoundOctober2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-10-1 00:00:00' AND '2014-10-31 00:00:00'";

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

    public static int readMissingOctober2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-10-1 00:00:00' AND '2014-10-31 00:00:00'";

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

    public static int readReturnedOctober2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-10-1 00:00:00' AND '2014-10-31 00:00:00'";

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

    public static int readDestroyedOctober2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-10-1 00:00:00' AND '2014-10-31 00:00:00'";

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
    
    public static int readFoundNovember2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-11-1 00:00:00' AND '2014-11-30 00:00:00'";

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

    public static int readMissingNovember2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-11-1 00:00:00' AND '2014-11-30 00:00:00'";

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

    public static int readReturnedNovember2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-11-1 00:00:00' AND '2014-11-30 00:00:00'";

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

    public static int readDestroyedNovember2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-11-1 00:00:00' AND '2014-11-30 00:00:00'";

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
    
    public static int readFoundDecember2014() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2014-12-1 00:00:00' AND '2014-12-31 00:00:00'";

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

    public static int readMissingDecember2014() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2014-12-1 00:00:00' AND '2014-12-31 00:00:00'";

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

    public static int readReturnedDecember2014() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2014-12-1 00:00:00' AND '2014-12-31 00:00:00'";

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

    public static int readDestroyedDecember2014() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2014-12-1 00:00:00' AND '2014-12-31 00:00:00'";

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
    
    public static int readFoundJanuary2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-01-1 00:00:00' AND '2015-01-31 00:00:00'";

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

    public static int readMissingJanuary2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-01-1 00:00:00' AND '2015-01-31 00:00:00'";

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
    
    // MONTH OVERVIEW STATUS 2015

    public static int readReturnedJanuary2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-01-1 00:00:00' AND '2015-01-31 00:00:00'";

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

    public static int readDestroyedJanuary2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-01-1 00:00:00' AND '2015-01-31 00:00:00'";

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

        public static int readFoundFebruary2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-02-1 00:00:00' AND '2015-02-28 00:00:00'";

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

    public static int readMissingFebruary2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-02-1 00:00:00' AND '2015-02-28 00:00:00'";

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

    public static int readReturnedFebruary2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-02-1 00:00:00' AND '2015-02-28 00:00:00'";

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

    public static int readDestroyedFebruary2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-02-1 00:00:00' AND '2015-02-28 00:00:00'";

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
    
    public static int readFoundMarch2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-03-1 00:00:00' AND '2015-03-31 00:00:00'";

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

    public static int readMissingMarch2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-03-1 00:00:00' AND '2015-03-31 00:00:00'";

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

    public static int readReturnedMarch2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-03-1 00:00:00' AND '2015-03-31 00:00:00'";

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

    public static int readDestroyedMarch2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-03-1 00:00:00' AND '2015-03-31 00:00:00'";

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
    
    public static int readFoundApril2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-04-1 00:00:00' AND '2015-04-30 00:00:00'";

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

    public static int readMissingApril2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-04-1 00:00:00' AND '2015-04-30 00:00:00'";

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

    public static int readReturnedApril2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-04-1 00:00:00' AND '2015-04-30 00:00:00'";

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

    public static int readDestroyedApril2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-04-1 00:00:00' AND '2015-04-30 00:00:00'";

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
    
    public static int readFoundMay2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-05-1 00:00:00' AND '2015-05-31 00:00:00'";

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

    public static int readMissingMay2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-05-1 00:00:00' AND '2015-05-31 00:00:00'";

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

    public static int readReturnedMay2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-05-1 00:00:00' AND '2015-05-31 00:00:00'";

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

    public static int readDestroyedMay2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-05-1 00:00:00' AND '2015-05-31 00:00:00'";

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
    
    public static int readFoundJune2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-06-1 00:00:00' AND '2015-06-30 00:00:00'";

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

    public static int readMissingJune2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-06-1 00:00:00' AND '2015-06-30 00:00:00'";

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

    public static int readReturnedJune2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-06-1 00:00:00' AND '2015-06-30 00:00:00'";

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

    public static int readDestroyedJune2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-06-1 00:00:00' AND '2015-06-30 00:00:00'";

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
    
    public static int readFoundJuly2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-07-1 00:00:00' AND '2015-07-31 00:00:00'";

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

    public static int readMissingJuly2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-07-1 00:00:00' AND '2015-07-31 00:00:00'";

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

    public static int readReturnedJuly2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-07-1 00:00:00' AND '2015-07-31 00:00:00'";

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

    public static int readDestroyedJuly2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-07-1 00:00:00' AND '2015-07-31 00:00:00'";

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
    
    public static int readFoundAugust2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-08-1 00:00:00' AND '2015-08-31 00:00:00'";

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

    public static int readMissingAugust2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-08-1 00:00:00' AND '2015-08-31 00:00:00'";

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

    public static int readReturnedAugust2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-08-1 00:00:00' AND '2015-08-31 00:00:00'";

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

    public static int readDestroyedAugust2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-08-1 00:00:00' AND '2015-08-31 00:00:00'";

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
    
    public static int readFoundSeptember2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-09-1 00:00:00' AND '2015-09-30 00:00:00'";

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

    public static int readMissingSeptember2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-09-1 00:00:00' AND '2015-09-30 00:00:00'";

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

    public static int readReturnedSeptember2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-09-1 00:00:00' AND '2015-09-30 00:00:00'";

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

    public static int readDestroyedSeptember2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-09-1 00:00:00' AND '2015-09-30 00:00:00'";

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
    
    public static int readFoundOctober2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-10-1 00:00:00' AND '2015-10-31 00:00:00'";

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

    public static int readMissingOctober2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-10-1 00:00:00' AND '2015-10-31 00:00:00'";

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

    public static int readReturnedOctober2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-10-1 00:00:00' AND '2015-10-31 00:00:00'";

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

    public static int readDestroyedOctober2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-10-1 00:00:00' AND '2015-10-31 00:00:00'";

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
    
    public static int readFoundNovember2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-11-1 00:00:00' AND '2015-11-30 00:00:00'";

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

    public static int readMissingNovember2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-11-1 00:00:00' AND '2015-11-30 00:00:00'";

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

    public static int readReturnedNovember2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-11-1 00:00:00' AND '2015-11-30 00:00:00'";

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

    public static int readDestroyedNovember2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-11-1 00:00:00' AND '2015-11-30 00:00:00'";

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
    
    public static int readFoundDecember2015() {
        int found = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'found'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Found'"
                    + "AND dateadded BETWEEN '2015-12-1 00:00:00' AND '2015-12-31 00:00:00'";

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

    public static int readMissingDecember2015() {
        int missing = -1;

        databaseManager.openConnection();

        try {
            String query = "SELECT count(*) as 'missing'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Missing'"
                    + "AND dateadded BETWEEN '2015-12-1 00:00:00' AND '2015-12-31 00:00:00'";

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

    public static int readReturnedDecember2015() {
        int returned = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'returned'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Returned'"
                    + "AND dateadded BETWEEN '2015-12-1 00:00:00' AND '2015-12-31 00:00:00'";

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

    public static int readDestroyedDecember2015() {
        int destroyed = -1;

        databaseManager.openConnection();

        try {
             String query = "SELECT count(*) as 'destroyed'"
                    + "FROM Luggage "
                    + "WHERE luggagestatus = 'Destroyed'"
                    + "AND dateadded BETWEEN '2015-12-1 00:00:00' AND '2015-12-31 00:00:00'";

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
