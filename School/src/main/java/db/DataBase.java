package db;

import loger.Log;
import models.ResourceType;
import models.school_object.AdditionalMaterials;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/?user=root";
    private static final String USER = "root";

    private static final String PASSWORD = "password";

    public java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void closeConnection(java.sql.Connection connection) throws SQLException {
        connection.close();
    }

    public ArrayList<AdditionalMaterials> getAddMattFromDB() {
        try (java.sql.Connection connection = getConnection()) {
            System.out.println("connection!");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM school.additional_materials");
            ArrayList<AdditionalMaterials> additionalMaterialsArrayList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("add_mat_name");
                ResourceType resourceType = ResourceType.valueOf(rs.getString("resource_type"));
                int lectureId = rs.getInt("lecture_id");
                AdditionalMaterials additionalMaterials = new AdditionalMaterials(id, name, lectureId, resourceType);
                additionalMaterialsArrayList.add(additionalMaterials);
            }
            closeConnection(connection);
            return new ArrayList<>();
        } catch (SQLException e) {
            Log.error(this.getClass().getName(), "getAddMattFromDB mthd", e);
        }
        return new ArrayList<>();
    }

}
