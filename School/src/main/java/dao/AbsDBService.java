package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbsDBService implements IDBService{
    ConnectionToDb connectionToDb = new ConnectionToDb();

    public int count(String tableName){
        try (java.sql.Connection connection = connectionToDb.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) as count FROM " + tableName);
            int count = rs.getInt("count");
          connectionToDb.closeConnection(connection);
          return count;
        } catch (SQLException e) {
            System.out.println("gfyhijokpl");
        }
        return 0;
    }

}
