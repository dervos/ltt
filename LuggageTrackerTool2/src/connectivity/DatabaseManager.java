package connectivity;

import java.sql.*;

/**
 *
 * @author Reinhard van Apeldoorn
 */
public class DatabaseManager {

    public static final String JDBC_EXCEPTION = "JDBC Exception: ";
    public static final String SQL_EXCEPTION = "SQL Exception: ";

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost/lttDatabase";
    public static final String DBUSER = "root";
    public static final String DBPASS = "toor";

    private ResultSet result = null;
    private int affectedRows = -1;
    private  Connection connection = null;

    public DatabaseManager() {
    }

    /**
     *
     */
    public void openConnection() {
        try {
            Class.forName(DRIVER);

            // Open Connection
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (ClassNotFoundException e) {
            System.err.println(JDBC_EXCEPTION + e);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
    }

    /**
     *
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        connection = null;
    }

    public ResultSet performSelect(PreparedStatement prdstmt) throws SQLException {
        result = prdstmt.executeQuery();

        return result;
    }

    public int performUpdate(PreparedStatement prdstmt) throws SQLException {

        affectedRows = prdstmt.executeUpdate();

        return affectedRows;
    }

    public Connection getConnection() {
        if(connection == null)
            openConnection();
        return connection;
    }

    /**
     * Executes a query without result.
     *
     * @param query, the SQl query
     */
    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
    }

    /**
     * Executes a query with result.
     *
     * @param query, the SQL query
     * @return
     */
    public ResultSet doQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return result;
    }

    /**
     * Executes a query with result.
     *
     * @param query, the SQL query
     * @return
     */
    public ResultSet insertQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            result = statement.getGeneratedKeys();
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return result;
    }
}
