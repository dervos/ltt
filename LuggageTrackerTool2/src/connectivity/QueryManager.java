package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Passenger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Reinhard van Apeldoorn
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
     * Methode om passagiers aan te maken
     * @param passengerId
     * @return 
     */
    public Passenger getPassenger(int passengerId) {
        Passenger passenger = new Passenger();
        try {
            String sql = "SELECT * FROM passenger WHERE passenger_id='" + passengerId + "'";
            ResultSet result = databaseManager.doQuery(sql);
            if (result.next()) {
                passenger = new Passenger( // Dit werkt nog niet, kolommen zijn nog niet compleet
                        result.getString("Passenger ID"),
                        result.getString("Name"),
                        result.getString("Insertion"),
                        result.getString("Surname"),
                        result.getString("Gender"),
                        result.getString("Date of birth"),
                        result.getString("Mobile phone"),
                        result.getString("Home phone"));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }
        return passenger;
    }

    /**
     * Returned alle passagiers als een list
     * @return 
     */
    public List<Passenger> getPassengers() {
        List<Passenger> passengers = new ArrayList<Passenger>();

        try {
            String sql = "SELECT * FROM passenger";
            ResultSet result = databaseManager.doQuery(sql);
            while (result.next()) {
                passengers.add(new Passenger( // Dit werkt nog niet, kolommen zijn nog niet compleet
                        result.getString("Passenger ID"),
                        result.getString("Name"),
                        result.getString("Insertion"),
                        result.getString("Surname"),
                        result.getString("Gender"),
                        result.getString("Date of birth"),
                        result.getString("Mobile phone"),
                        result.getString("Home phone")));
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }
        return passengers;
    }
    
    
}
