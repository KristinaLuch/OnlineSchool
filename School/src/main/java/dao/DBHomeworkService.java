package dao;

import models.school_object.Homework;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DBHomeworkService extends AbsDBService {

    ConnectionToDb connectionToDb = new ConnectionToDb();

    public Map<Integer, ArrayList<Homework>> getAllFromBD() {
        try (java.sql.Connection connection = connectionToDb.getConnection()) {
            System.out.println("connection!");
            Statement statement = connection.createStatement();

            ArrayList<Integer> arrayKey = new ArrayList<>();
            ResultSet rsKey = statement.executeQuery("SELECT distinct lecture_id from school.homework ORDER BY lecture_id;");
            while (rsKey.next()) {
                int lectureId = rsKey.getInt("lecture_id");
                arrayKey.add(lectureId);
            }
            if (arrayKey.size() == 0){
                return new TreeMap<>();
            }
            int key;
            int idMax = 0;
            ResultSet rs;
            ArrayList<Homework> value = new ArrayList<>();
            Map<Integer, ArrayList<Homework>> map = new TreeMap<>();
            for (int i = 0; i<arrayKey.size(); i++){
                key = arrayKey.get(i);
                rs = statement.executeQuery("SELECT * FROM school.homework WHERE lecture_id = "+key);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    if (id>idMax){idMax = id;}
                    String task = rs.getString("task");
                    int lectureId = rs.getInt("lecture_id");
                    Homework homework = new Homework(id, lectureId, task);
                    value.add(homework);
                }
                map.put(key, value);
                value = new ArrayList<>();
            }
            Homework.setCount(idMax);
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
        Homework.setCount(count("school.homework"));
    }
}
