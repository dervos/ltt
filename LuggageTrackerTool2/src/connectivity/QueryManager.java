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

    /**
     *
     * @param databaseManager
     */
    public QueryManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Function to create a new passenger row in DB
     *
     * @param passenger
     * @param homeAddress
     * @param tempAddress
     * @param address
     */
    public void addPassenger(Passenger passenger, Address homeAddress, Address tempAddress) {
        String sql_passengerTable = "INSERT INTO `passenger` (Passenger ID, Surname, Name, Gender, Date of birth, Mobile phone, Home phone, Home address ID, Temporary address ID,  Insertion)"
                + "VALUES ('" + passenger.getPassengerId() + "', '" + passenger.getSurname()
                + "', '" + passenger.getFirstName() + "', '" + passenger.getGender() + "', '"
                + passenger.getDateOfBirth() + "', '" + passenger.getMobileNumber() + "', '"
                + passenger.getPrivateNumber() + "', '" + passenger.getHomeAddressId() + "', '"
                + passenger.getTemporaryAddressId() + "', '" + passenger.getInsertion() + "');";

        //Column address ID needs to be handled in the database, should this auto-increment?
        this.addAddress(homeAddress);
        this.addAddress(tempAddress);
        this.databaseManager.insertQuery(sql_passengerTable);
    }

    /**
     * Function to search passenger in DB.
     *
     * @param passengerId
     * @return
     */
    public Passenger getPassenger(int passengerId) {
        Passenger passenger = new Passenger();
        try {
            String sql = "SELECT * FROM passenger WHERE `passenger id`='" + passengerId + "'";
            ResultSet result = databaseManager.doQuery(sql);
            if (result.next()) {
                passenger = new Passenger(
                        result.getInt("Passenger ID"),
                        result.getString("Surname"),
                        result.getString("Name"),
                        result.getString("Gender"),
                        result.getDate("Date of birth"),
                        result.getString("Mobile phone"),
                        result.getString("Home phone"),
                        result.getInt("Home address ID"),
                        result.getInt("Temporary address ID"),
                        result.getString("Insertion"));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }
        return passenger;
    }

    /**
     * Updates passenger row in DB.
     *
     * @param passenger
     */
    public void updatePassenger(Passenger passenger) {
        String sql = "UPDATE passenger SET Passenger ID = " + passenger.getPassengerId()
                + "', Surname = '" + passenger.getSurname()
                + "', Name = '" + passenger.getFirstName()
                + "', Gender = '" + passenger.getGender()
                + "', Date of birth = '" + passenger.getDateOfBirth()
                + "', Mobile phone = '" + passenger.getMobileNumber()
                + "', Home phone = '" + passenger.getPrivateNumber()
                + "', Home address ID = '" + passenger.getHomeAddressId()
                + "', Temporary address ID = '" + passenger.getTemporaryAddressId()
                + "', Insertion = '" + passenger.getInsertion()
                + "' WHERE `passenger id` = '" + passenger.getPassengerId() + "'";

        this.databaseManager.insertQuery(sql);
    }

    /**
     * Deletes passenger does NOT delete addresses, yet.
     *
     * @param passengerId
     */
    public void deletePassenger(int passengerId) {
        this.getPassenger(passengerId);
        String sql = "DELETE FROM `Passenger` WHERE `Passenger ID` = '" + passengerId + "'";
        this.databaseManager.insertQuery(sql);

        // Do temporary address and home address ALWAYS get a value? 
        // Or does this get set to -1 or something similar if no values are inserted for address?
    }

    /**
     * Returned alle passagiers als een list
     *
     * @return
     */
    public List<Passenger> getPassengers() {
        List<Passenger> passengers = new ArrayList<Passenger>();

        try {
            String sql = "SELECT * FROM passenger";
            ResultSet result = databaseManager.doQuery(sql);
            while (result.next()) {
                passengers.add(new Passenger(
                        result.getInt("Passenger ID"),
                        result.getString("Surname"),
                        result.getString("Name"),
                        result.getString("Gender"),
                        result.getDate("Date of birth"),
                        result.getString("Mobile phone"),
                        result.getString("Home phone"),
                        result.getInt("Home address ID"),
                        result.getInt("Temporary address ID"),
                        result.getString("Insertion")));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }
        return passengers;
    }

    /**
     * Adds an address
     *
     * @param address
     */
    public void addAddress(Address address) {
        String sql = "INSERT INTO `Address` (Address ID, Street, Street Number, Zipcode, City, Country)"
                + "VALUES: ('" + address.getAddressId() + "', '" + address.getStreetName()
                + "', '" + address.getStreetNumber() + "', '" + address.getZipCode()
                + "', '" + address.getCity() + "', '" + address.getCountry() + "')";

        this.databaseManager.insertQuery(sql);
    }

    /**
     *
     * @return List of all addresses
     */
    public List<Address> getAddresses() {
        List<Address> addresses = new ArrayList<Address>();

        try {
            String sql = "SELECT * FROM Address";
            ResultSet result = databaseManager.doQuery(sql);
            while (result.next()) {
                addresses.add(new Address(result.getInt("Address ID"),
                        result.getString("Street"),
                        result.getInt("Street Number"),
                        result.getString("Zipcode"),
                        result.getString("City"),
                        result.getString("Country")));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }
        return addresses;
    }

    /**
     *
     * @param address
     */
    public void updateAddress(Address address) {
        String sql = "UPDATE `Address` SET Street'" + address.getStreetName()
                + "', Street number'" + address.getStreetNumber()
                + "', Zipcode'" + address.getZipCode()
                + "', City'" + address.getCity()
                + "', Country'" + address.getCountry()
                + "' WHERE `Address ID` = '" + address.getAddressId() + "'";

        this.databaseManager.insertQuery(sql);
    }

    /**
     * Deletes address with given ID.
     *
     * @param addressId
     */
    public void deleteAddress(int addressId) {
        String sql = "DELETE FROM `Address` WHERE `Address ID` = '" + addressId + "'";
        this.databaseManager.insertQuery(sql);
    }

    /**
     *
     * @param addressId
     * @return address requested
     */
    public Address getAddress(int addressId) {
        Address address = new Address();
        try {
            String sql = "SELECT * FROM Address WHERE Address ID = '" + addressId + "'";
            ResultSet result = databaseManager.doQuery(sql);
            if (result.next()) {
                address = new Address(result.getInt("Address ID"),
                        result.getString("Street"),
                        result.getInt("Street Number"),
                        result.getString("Zipcode"),
                        result.getString("City"),
                        result.getString("Country"));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }

        return address;
    }

    /**
     * adds luggage to DB.
     *
     * @param luggage
     */
    public void addLuggage(Luggage luggage) {
        String sql = "INSERT INTO `Luggage` (Luggage ID, Description, Storage Location, Luggage Status)"
                + "VALUES: ('" + luggage.getLuggageId() + "', '" + luggage.getDescription() + "', '" + luggage.getStorageLocation() + "', '" + luggage.getStatus() + "')";
        this.databaseManager.insertQuery(sql);
    }

    /**
     * Gets luggage.
     *
     * @param luggageId
     * @return
     */
    public Luggage getLuggage(int luggageId) {
        Luggage luggage = new Luggage();
        try {
            String sql = "SELECT * FROM Luggage WHERE Luggage ID = '" + luggageId + "'";
            ResultSet result = databaseManager.doQuery(sql);
            if (result.next()) {
                luggage = new Luggage(result.getInt("Luggage ID"),
                        result.getString("Description"),
                        result.getString("Storage Location"),
                        result.getString("Luggage Status"));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }

        return luggage;
    }

    public void updateLuggage(Luggage luggage) {
        String sql = "UPDATE `Luggage` SET Description = '" + luggage.getDescription()
                + "', Storage Location'" + luggage.getStorageLocation()
                + "', Luggage Status'" + luggage.getStatus()
                + "' WHERE Luggage ID = '" + luggage.getLuggageId() + "'";

        this.databaseManager.insertQuery(sql);
    }

    /**
     *
     * @param luggageId
     */
    public void deleteLuggage(int luggageId) {
        String sql = "DELETE FROM `Luggage` WHERE `Luggage ID` = '" + luggageId + "'";
        this.databaseManager.insertQuery(sql);
    }

    /**
     * Gets a list which includes every piece of luggage.
     *
     * @return
     */
    public List<Luggage> getLuggages() {
        List<Luggage> luggages = new ArrayList<Luggage>();

        try {
            String sql = "SELECT * FROM Luggage";
            ResultSet result = databaseManager.doQuery(sql);
            while (result.next()) {
                luggages.add(new Luggage(result.getInt("Luggage ID"),
                        result.getString("Description"),
                        result.getString("Storage Location"),
                        result.getString("Luggage Status")));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }

        return luggages;
    }

    /**
     * Add a user.
     *
     * @param user
     */
    public void addUser(User user) {
        String sql = "INSERT INTO `User` (Username, Password, Privileges)"
                + "VALUES: ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getRights() + "')";

        this.databaseManager.insertQuery(sql);
    }

    /**
     * Get a user out of the database by username
     *
     * @param username
     * @return
     */
    public User getUserByName(String username) {
        User user = new User();
        try {
            String sql = "SELECT * FROM `User` WHERE `Username` = '" + username + "'";
            ResultSet result = databaseManager.doQuery(sql);
            if (result.next()) {
                user = new User(result.getString("Username"),
                        result.getString("Password"),
                        result.getInt("Privileges"));
            }

        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }

        return user;
    }

    /**
     *
     * @param user
     */
    public void updateUser(User user) {
        String sql = "UPDATE `User` SET Username = '" + user.getUsername()
                + "', Password = '" + user.getPassword()
                + "', Privileges = '" + user.getRights()
                + "' WHERE Username = '" + user.getUsername() + "'";

        this.databaseManager.insertQuery(sql);
    }

    /**
     * Delete user by its username
     *
     * @param username
     */
    public void deleteUserByName(String username) {
        String sql = "DELETE FROM `User` WHERE `Username` = '" + username + "'";
        this.databaseManager.insertQuery(sql);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();

        try {
            String sql = "SELECT * FROM User";
            ResultSet result = databaseManager.doQuery(sql);
            while (result.next()) {
                users.add(new User(result.getString("Username"),
                        result.getString("Password"),
                        result.getInt("Privileges")));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }

        return users;
    }
}
