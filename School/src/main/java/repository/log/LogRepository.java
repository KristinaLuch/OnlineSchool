package repository.log;

import loger.Level;
import loger.Log;
import service.log.LogService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LogRepository {

    private static ArrayList<Log> logs;

    private static LogService logService;

    public LogRepository(LogService logService) {
        this.logService = logService;
        this.logs = new ArrayList<>();
        loadLogs();
    }


    public void create(String name, Level level, String message, LocalDateTime date, String stacktrace) {
        Log log = new Log(name, level, message, date, stacktrace);
        logs.add(log);
        saveInFile(log);
        if (level == Level.WARNING || level == Level.ERROR) {
            System.out.println(stacktrace);
        } else {
            System.out.println(message);
        }
    }

    public static void create(String className, Exception e) {
        Log log = new Log(className, Level.WARNING, e.getMessage(), LocalDateTime.now(), getStringStackTrace(e));
        logs.add(log);
        saveInFile(log);
        e.printStackTrace();
    }

    public void create(Log log) {
        logs.add(log);
        saveInFile(log);
        if (log.getLevel() == Level.WARNING || log.getLevel() == Level.ERROR) {
            System.out.println(log.getStacktrace());
        } else {
            System.out.println(log.getMessage());
        }
    }

    private static String getStringStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    private static void saveInFile(Log log) {
        logService.writeToFile(log);
    }

    public void loadLogs() {
        logs = logService.readFile();
    }

}
