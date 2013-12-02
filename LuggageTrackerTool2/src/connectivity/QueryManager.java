package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Reinhard van Apeldoorn, Tomas Slaman
 */
public class QueryManager {

    private final DatabaseManager databaseManager;
//
//    /**
//     *
//     * @param databaseManager
//     */
    public QueryManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
//
//    
//
//    /**
//     * Returned alle passagiers als een list
//     *
//     * @return
//     */
//    public List<Passenger> getPassengers() {
//        List<Passenger> passengers = new ArrayList<Passenger>();
//
//        try {
//            String sql = "SELECT * FROM passenger";
//            ResultSet result = databaseManager.doQuery(sql);
//            while (result.next()) {
//                passengers.add(new Passenger(
//                        result.getInt("Passenger ID"),
//                        result.getString("Surname"),
//                        result.getString("Name"),
//                        result.getString("Gender"),
//                        result.getDate("Date of birth"),
//                        result.getString("Mobile phone"),
//                        result.getString("Home phone"),
//                        result.getInt("Home address ID"),
//                        result.getInt("Temporary address ID"),
//                        result.getString("Insertion")));
//            }
//        } catch (SQLException e) {
//            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
//        }
//        return passengers;
//    }
//
//    
//
//    /**
//     *
//     * @param addressId
//     * @return address requested
//     */
//    public Address getAddress(int addressId) {
//        Address address = new Address();
//        try {
//            String sql = "SELECT * FROM Address WHERE Address ID = '" + addressId + "'";
//            ResultSet result = databaseManager.doQuery(sql);
//            if (result.next()) {
//                address = new Address(result.getInt("Address ID"),
//                        result.getString("Street"),
//                        result.getInt("Street Number"),
//                        result.getString("Zipcode"),
//                        result.getString("City"),
//                        result.getString("Country"));
//            }
//        } catch (SQLException e) {
//            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
//        }
//
//        return address;
//    }
//
//    /**
//     * adds luggage to DB.
//     *
//     * @param luggage
//     */
//    public void addLuggage(Luggage luggage) {
//        String sql = "INSERT INTO `Luggage` (Luggage ID, Description, Storage Location, Luggage Status)"
//                + "VALUES: ('" + luggage.getLuggageId() + "', '" + luggage.getDescription() + "', '" + luggage.getStorageLocation() + "', '" + luggage.getStatus() + "')";
//        this.databaseManager.insertQuery(sql);
//    }
//
//    /**
//     * Gets luggage.
//     *
//     * @param luggageId
//     * @return
//     */
//    public Luggage getLuggage(int luggageId) {
//        Luggage luggage = new Luggage();
//        try {
//            String sql = "SELECT * FROM Luggage WHERE Luggage ID = '" + luggageId + "'";
//            ResultSet result = databaseManager.doQuery(sql);
//            if (result.next()) {
//                luggage = new Luggage(result.getInt("Luggage ID"),
//                        result.getString("Description"),
//                        result.getString("Storage Location"),
//                        result.getString("Luggage Status"));
//            }
//        } catch (SQLException e) {
//            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
//        }
//
//        return luggage;
//    }
//
//    public void updateLuggage(Luggage luggage) {
//        String sql = "UPDATE `Luggage` SET Description = '" + luggage.getDescription()
//                + "', Storage Location'" + luggage.getStorageLocation()
//                + "', Luggage Status'" + luggage.getStatus()
//                + "' WHERE Luggage ID = '" + luggage.getLuggageId() + "'";
//
//        this.databaseManager.insertQuery(sql);
//    }
//
//    /**
//     *
//     * @param luggageId
//     */
//    public void deleteLuggage(int luggageId) {
//        String sql = "DELETE FROM `Luggage` WHERE `Luggage ID` = '" + luggageId + "'";
//        this.databaseManager.insertQuery(sql);
//    }
//
//    /**
//     * Gets a list which includes every piece of luggage.
//     *
//     * @return
//     */
//    public List<Luggage> getLuggages() {
//        List<Luggage> luggages = new ArrayList<Luggage>();
//
//        try {
//            String sql = "SELECT * FROM Luggage";
//            ResultSet result = databaseManager.doQuery(sql);
//            while (result.next()) {
//                luggages.add(new Luggage(result.getInt("Luggage ID"),
//                        result.getString("Description"),
//                        result.getString("Storage Location"),
//                        result.getString("Luggage Status")));
//            }
//        } catch (SQLException e) {
//            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
//        }
//
//        return luggages;
//    }
//
//    /**
//     * Add a user.
//     *
//     * @param user
//     */
//    public void addUser(User user) {
//        String sql = "INSERT INTO `User` (Username, Password, Privileges)"
//                + "VALUES: ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getRights() + "')";
//
//        this.databaseManager.insertQuery(sql);
//    }
//
//    /**
//     * Get a user out of the database by username
//     *
//     * @param username
//     * @return
//     */
//    public User getUserByName(String username) {
//        User user = new User();
//        try {
//            String sql = "SELECT * FROM `User` WHERE `Username` = '" + username + "'";
//            ResultSet result = databaseManager.doQuery(sql);
//            if (result.next()) {
//                user = new User(result.getString("Username"),
//                        result.getString("Password"),
//                        result.getInt("Privileges"));
//            }
//
//        } catch (SQLException e) {
//            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
//        }
//
//        return user;
//    }
//
//    /**
//     *
//     * @param user
//     */
//    public void updateUser(User user) {
//        String sql = "UPDATE `User` SET Username = '" + user.getUsername()
//                + "', Password = '" + user.getPassword()
//                + "', Privileges = '" + user.getRights()
//                + "' WHERE Username = '" + user.getUsername() + "'";
//
//        this.databaseManager.insertQuery(sql);
//    }
//
//    /**
//     * Delete user by its username
//     *
//     * @param username
//     */
//    public void deleteUserByName(String username) {
//        String sql = "DELETE FROM `User` WHERE `Username` = '" + username + "'";
//        this.databaseManager.insertQuery(sql);
//    }
//
//    public List<User> getUsers() {
//        List<User> users = new ArrayList<User>();
//
//        try {
//            String sql = "SELECT * FROM User";
//            ResultSet result = databaseManager.doQuery(sql);
//            while (result.next()) {
//                users.add(new User(result.getString("Username"),
//                        result.getString("Password"),
//                        result.getInt("Privileges")));
//            }
//        } catch (SQLException e) {
//            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
//        }
//
//        return users;
//    }
}
