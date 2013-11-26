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

    //addPassenger not done yet.
    public void addPassenger(Passenger passenger) {
        String sql_passengerTable = "INSERT INTO `passenger` (Passenger ID, Surname, Name, Gender, Date of birth, Mobile phone, Home phone, Home address ID, Temporary address ID,  Insertion)"
                + "VALUES:(" + passenger.getPassengerId() + ", `" + passenger.getSurname()
                + "`, `" + passenger.getFirstName() + "`, `" + passenger.getGender() + "`, `"
                + passenger.getDateOfBirth() + "`, `" + passenger.getMobileNumber() + "`, `"
                + passenger.getPrivateNumber() + "`, " + passenger.getHomeAddressId() + ", "
                + passenger.getTemporaryAddressId() + ", `" + passenger.getInsertion() + "`)";

        //Column address ID needs to be handled in the database, should this auto-increment?
        //String sql_addressId = "INSERT INTO `address` (Street, Street Number, Postcode, City, Country)" 
        //        + "VALUES:`";
        this.databaseManager.insertQuery(sql_passengerTable);
        //this.databaseManager.insertQuery(sql_addressId);

    }

    /**
     * Methode om passagiers aan te maken
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

    public Address getAddress(int addressId) {
        Address address = new Address();
        try {
            String sql = "SELECT * FROM Address";
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
}
