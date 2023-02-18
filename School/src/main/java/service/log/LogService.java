package service.log;

import loger.Level;
import loger.Log;
import repository.log.LogRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LogService {

    private File file;

    private LogRepository logRepository;

    public LogService() {
        createFile();
    }

    public void setLogRepository(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    private void createFile(){
        file = new File("School/src/main/java/file/myFile.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logRepository.create(LogService.class.getName(), e);
            }
        }
    }

    public void writeToFile(Log log){
        try (FileWriter fileWriter = new FileWriter(file, true);){
            fileWriter.write(log.getDate().toString()+"\n");
            fileWriter.write(log.getLevel().toString()+"\n");
            fileWriter.write(log.getName()+"\n");
            fileWriter.write(log.getMessage()+"\n");
            fileWriter.write(log.getStacktrace()+"\n");
        } catch (IOException e) {
            logRepository.create(LogService.class.getName(), e);
        }
    }

    public ArrayList<Log> readFile(){
        ArrayList<Log> logs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String dateStr;
            while ((dateStr = reader.readLine())!= null) {
                LocalDateTime date = getDate(dateStr);
                Level level = Level.valueOf(reader.readLine());
                String name = reader.readLine();
                String message = reader.readLine();
                String stacktrace = reader.readLine();
                Log log = new Log(name, level, message, date, stacktrace);
                logs.add(log);
            }
        } catch(IOException e){
            logRepository.create(LogService.class.getName(), e);
        }
        return logs;
    }

    public LocalDateTime getDate(String dateString){
        String [] dateTime = dateString.split("T");
        String[] date = dateTime[0].split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        String[] time = dateTime[1].split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        String[] secondNano = time[2].split("\\.");
        int second = Integer.parseInt(secondNano[0]);
        int nanosecond = Integer.parseInt(secondNano[1]);
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second, nanosecond);
        return localDateTime;
    }

}
