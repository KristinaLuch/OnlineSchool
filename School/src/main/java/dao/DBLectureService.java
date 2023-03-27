package dao;

import org.example.models.school_object.AdditionalMaterials;
import org.example.models.school_object.Homework;
import org.example.models.school_object.Lecture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class DBLectureService {

    private final DBAdditionalMaterialsService dbAdditionalMaterialsService;

    private final DBHomeworkService dbHomeworkService;

    private final DBMaterialsService dbMaterialsService;

    public DBLectureService(DBAdditionalMaterialsService dbAdditionalMaterialsService, DBHomeworkService dbHomeworkService, DBMaterialsService dbMaterialsService) {
        this.dbAdditionalMaterialsService = dbAdditionalMaterialsService;
        this.dbHomeworkService = dbHomeworkService;
        this.dbMaterialsService = dbMaterialsService;
    }

    ConnectionToDb connectionToDb = new ConnectionToDb();

    public ArrayList<Lecture> getAllFromDB() {
        try (java.sql.Connection connection = connectionToDb.getConnection()) {
            System.out.println("connection!");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM school.lecture");
            ArrayList<Lecture> lectures = new ArrayList<>();
            Map<Integer, ArrayList<AdditionalMaterials>> mapAddM = dbAdditionalMaterialsService.getAllFromBD();
            Map<Integer, ArrayList<Homework>> mapHw = dbHomeworkService.getAllFromBD();
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDateTime createDate = rs.getObject(2, LocalDateTime.class);
                LocalDateTime lectureDate = rs.getObject(3, LocalDateTime.class);
                int courseId = rs.getInt("course_id");
                int teacherId = rs.getInt("teacher_id");
                String name = rs.getString("lecture_name");
                String description = rs.getString("lecture_description");
                Lecture lecture = new Lecture(id, courseId, teacherId, name, description,
                        mapHw.get(id), dbMaterialsService.getMaterialsForLecture(id),
                        createDate, lectureDate);
                lectures.add(lecture);
            }
            connectionToDb.closeConnection(connection);
            return lectures;
        } catch (SQLException e) {
            //Log.error(this.getClass().getName(), "getAddMattFromDB mthd", e);
        }
        return new ArrayList<>();
    }

}
