package dao;

import exceptions.EntityNotFoundException;
import loger.Log;
import models.Role;
import models.school_object.Materials;
import models.school_object.Person;

import java.sql.*;
import java.util.ArrayList;

public class DBPersonService {

    private ConnectionToDb connectionToDb = new ConnectionToDb();

    public Person getPerson(int findId, Role role) throws EntityNotFoundException {
        // подключение к БД
        try {
            Connection conn = connectionToDb.getConnection();

            String table;

            if(role == Role.STUDENT){
                table = "school.student";
            }else {
                table = "school.teacher";
            }

            // создание Prepared Statement
            String sql = "SELECT * FROM table WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

// установка параметров
            stmt.setInt(1, 1);

        // выполнение запроса
            ResultSet rs = stmt.executeQuery();

            String firstname;
            String lastname;
            String phone;
            String email;
            Person person = null;
// обработка результата
            while (rs.next()) {
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                phone = rs.getString("phone");
                email = rs.getString("email");
                person = new Person(findId, Role.STUDENT, firstname, lastname, phone, email);
            }
// закрытие ресурсов
            rs.close();
            stmt.close();
            conn.close();

            if (person == null){
                throw new EntityNotFoundException();
            }
            return person;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Person> getAllFromDB(Role role) {

        try (java.sql.Connection connection = connectionToDb.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM school.materials");
            ArrayList<Person> persons = new ArrayList<>();

            String table;
            if(role == Role.STUDENT){
                table = "school.student";
            }else {
                table = "school.teacher";
            }

            while (rs.next()) {
                int findId = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                Person person = new Person(findId, Role.STUDENT, firstname, lastname, phone, email);
                persons.add(person);
            }
            connectionToDb.closeConnection(connection);
            return persons;
        } catch (SQLException e) {
            Log.error(this.getClass().getName(), "getPersonFromDB mthd", e);
        }
        return new ArrayList<>();
    }
}
