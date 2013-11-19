package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Passenger;

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

    public Passenger getPassenger(int passengerId) {
        Passenger passenger = new Passenger();
        try {
            String sql = "SELECT * FROM passenger WHERE passenger_id='" + passengerId + "'";
            ResultSet result = databaseManager.doQuery(sql);
            if (result.next()) {
                passenger = new Passenger(
                        result.getString("passenger_id"),
                        result.getString(sql),
                        result.getString(sql),
                        result.getString(sql),
                        result.getString(sql),
                        result.getString(sql),
                        result.getString(sql),
                        result.getString(sql)
                );
            }
        } catch (SQLException e) {
            System.err.println(DatabaseManager.SQL_EXCEPTION + e.getMessage());
        }
        return passenger;
    }

}
