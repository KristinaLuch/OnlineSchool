package dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDb {
    private static final String URL = "jdbc:mysql://localhost:3306/?user=root";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void closeConnection(java.sql.Connection connection) throws SQLException {
        connection.close();
    }
}
