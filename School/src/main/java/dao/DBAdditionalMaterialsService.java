package dao;

import models.ResourceType;
import models.school_object.AdditionalMaterials;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DBAdditionalMaterialsService extends AbsDBService{

    ConnectionToDb connectionToDb = new ConnectionToDb();

    public Map<Integer, ArrayList<AdditionalMaterials>> getAllFromBD() {
        try (java.sql.Connection connection = connectionToDb.getConnection()) {
            System.out.println("connection!");
            Statement statement = connection.createStatement();

            ArrayList<Integer> arrayKey = new ArrayList<>();
            ResultSet rsKey = statement.executeQuery("SELECT distinct lecture_id from school.additional_materials ORDER BY lecture_id;");
            while (rsKey.next()) {
                int lectureId = rsKey.getInt("lecture_id");
                arrayKey.add(lectureId);
            }

            if (arrayKey.size() == 0){
                return new TreeMap<>();
            }
            int key;
            ResultSet rs;
            ArrayList<AdditionalMaterials> value = new ArrayList<>();
            Map<Integer, ArrayList<AdditionalMaterials>> map = new TreeMap<>();
            for (int i = 0; i<arrayKey.size(); i++){
                key = arrayKey.get(i);
                rs = statement.executeQuery("SELECT * FROM school.additional_materials WHERE lecture_id = "+key);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("add_mat_name");

                    ResourceType resourceType = ResourceType.valueOf(rs.getString("resource_type").toUpperCase());
                    int lectureId = rs.getInt("lecture_id");
                    AdditionalMaterials additionalMaterials = new AdditionalMaterials(id, name, lectureId, resourceType);
                    value.add(additionalMaterials);
                }
                map.put(key, value);
                value = new ArrayList<>();
            }
            connectionToDb.closeConnection(connection);
            return map;
        } catch (SQLException e) {
            System.err.println("Shinima hujnya");
            e.printStackTrace();
        }
        return new TreeMap<>();
    }


    @Override
    public void setCount() {
        AdditionalMaterials.setCount(count("school.additional_materials"));
    }
}
