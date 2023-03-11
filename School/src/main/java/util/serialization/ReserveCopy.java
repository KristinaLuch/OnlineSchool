package util.serialization;

import constants.FilesAddresses;
import loger.Log;
import models.school_object.Course;

import java.io.*;

public class ReserveCopy {

    public ReserveCopy() {
        createFile();
    }

    private File file;

    private void createFile() {
        file = new File(FilesAddresses.BACKUP_FILE_ADDRESS);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.error(this.getClass().getName(), "method createFile", e);
            }
        }
    }


    public void backupCourse(Course course) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Log.error(this.getClass().getName(), "backupCourse method, FileOutputStream", e);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(course);
        }catch (Exception e){
            Log.error(this.getClass().getName(), "backupCourse method, write object", e);
        }
    }

    public Course deserializationCourse(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Log.error(this.getClass().getName(), "backupCourse method, FileInputStream", e);
        }
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Course) ois.readObject();
        }catch (Exception e){
            Log.error(this.getClass().getName(), "backupCourse method, write object", e);
        }
        return null;
    }


}
