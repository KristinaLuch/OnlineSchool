package dao;

import models.school_object.Materials;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBMaterialsService {
    ConnectionToDb connectionToDb = new ConnectionToDb();

    public ArrayList<Materials> getAllFromDB() {
        try (java.sql.Connection connection = connectionToDb.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM school.materials");
            ArrayList<Materials> materialsArrayList = new ArrayList<>();

            int idMax = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                if(id>idMax){idMax = id;}
                String material = rs.getString("material");
                Materials materials = new Materials(id, material);
                materialsArrayList.add(materials);
            }
            Materials.setCount(idMax);
            connectionToDb.closeConnection(connection);
            return materialsArrayList;
        } catch (SQLException e) {
            //Log.error(this.getClass().getName(), "getAddMattFromDB mthd", e);
        }
        return new ArrayList<>();
    }

    public Materials getMaterialsForLecture(int lectureId) {
        try (java.sql.Connection connection = connectionToDb.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM school.materials WHERE lecture_id = "+ lectureId+";");
            if(rs.next()) {
                int id = rs.getInt("id");
                String material = rs.getString("material");
                Materials materials = new Materials(id, material);
                connectionToDb.closeConnection(connection);
                return materials;
            }
        } catch (SQLException e) {
            System.out.println("QWERTY!!!!");
            e.printStackTrace();
            //Log.error(this.getClass().getName(), "getAddMattFromDB mthd", e);
        }
        return null;
    }


}
