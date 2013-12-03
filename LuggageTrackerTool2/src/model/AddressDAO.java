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
public class AddressDAO {

    private static final connectivity.DatabaseManager databaseManager = main.LuggageTrackerTool2.getDatabaseManager();

    public AddressDAO() {

    }

    /**
     *
     * @return List of all addresses
     * @throws java.sql.SQLException
     */
    public static List<Address> readAll() throws SQLException {
        List<Address> list = new LinkedList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        String query = "SELECT addressid,street,streetnumber,zipcode,city,country from Address";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        rs = databaseManager.performSelect(ps);

        while (rs.next()) {
            Address tempAddress = new Address();
            tempAddress.setAddressid(rs.getInt("addressid"));
            tempAddress.setStreetname(rs.getString("streetname"));
            tempAddress.setStreetnumber(rs.getInt("streetnumber"));
            tempAddress.setZipcode(rs.getString("zipcode"));
            tempAddress.setCity(rs.getString("city"));
            tempAddress.setCountry(rs.getString("country"));
            list.add(tempAddress);
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return list;
    }

    public static Address readById(int id) throws SQLException {
        Address tempAddress = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String query = "SELECT * FROM Address WHERE addressid=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, id);

        rs = databaseManager.performSelect(ps);

        if (rs.next()) {
            tempAddress = new Address();
            tempAddress.setAddressid(rs.getInt("addressid"));
            tempAddress.setStreetname(rs.getString("streetname"));
            tempAddress.setStreetnumber(rs.getInt("streetnumber"));
            tempAddress.setZipcode(rs.getString("zipcode"));
            tempAddress.setCity(rs.getString("city"));
            tempAddress.setCountry(rs.getString("country"));
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return tempAddress;
    }

    public static List<Address> readByCity(String city) throws SQLException {
        List<Address> list = new LinkedList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        String query = "SELECT addressid, FROM Address WHERE city=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setString(1, city);

        rs = databaseManager.performSelect(ps);

        while (rs.next()) {
            Address tempAddress = new Address();
            tempAddress.setAddressid(rs.getInt("addressid"));
            tempAddress.setStreetname(rs.getString("streetname"));
            tempAddress.setStreetnumber(rs.getInt("streetnumber"));
            tempAddress.setZipcode(rs.getString("zipcode"));
            tempAddress.setCity(rs.getString("city"));
            tempAddress.setCountry(rs.getString("country"));
            list.add(tempAddress);
        }

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return list;
    }

    /**
     * Adds an address
     *
     * @param address
     * @return the affected rows
     * @throws java.sql.SQLException
     */
    public static int create(Address address) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "INSERT INTO Address (streetname, streetnumber, zipcode, city, country) "
                + "VALUES(?,?,?,?,?)";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, address.getStreetname());
        ps.setInt(2, 2);
        ps.setString(3, address.getZipcode());
        ps.setString(4, address.getCity());
        ps.setString(5, address.getCountry());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

    /**
     *
     * @param address
     * @return
     * @throws java.sql.SQLException
     */
    public static int update(Address address) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;

        String query = "UPDATE Address SET "
                + "streetname=?, "
                + "streetnumber=?, "
                + "zipcode=?, "
                + "city=?, "
                + "country=? "
                + "WHERE addressid=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);

        ps.setString(1, address.getStreetname());
        ps.setInt(2, address.getStreetnumber());
        ps.setString(3, address.getZipcode());
        ps.setString(4, address.getCity());
        ps.setString(5, address.getCountry());
        ps.setInt(6, address.getAddressid());

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }

    /**
     * Deletes address with given ID.
     *
     * @param addressid
     * @return
     * @throws java.sql.SQLException
     */
    public static int delete(int addressid) throws SQLException {
        int rowsAffected;
        PreparedStatement ps = null;
        String query = "DELETE FROM Address WHERE addressid=?";

        databaseManager.openConnection();

        ps = databaseManager.getConnection().prepareStatement(query);
        ps.setInt(1, addressid);

        rowsAffected = ps.executeUpdate();

        if (databaseManager != null) {
            databaseManager.closeConnection();
        }

        return rowsAffected;
    }
}
