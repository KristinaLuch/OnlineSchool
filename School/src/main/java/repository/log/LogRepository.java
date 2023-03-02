package repository.log;

import loger.Level;
import loger.Log;
import service.log.LogService;

import java.util.ArrayList;

public class LogRepository {

    private static ArrayList<Log> logs;

    private static LogService logService;

    public LogRepository(LogService logService) {
        this.logService = logService;
        this.logs = new ArrayList<>();
        loadLogs();
    }


    public static void add(Log log) {

        logs.add(log);
        saveInFile(log);
        printLog(log);
    }

    public static void printLog(Log log){
        if (log.getLevel() == Level.WARNING || log.getLevel() == Level.ERROR) {
            System.out.println(log.getStacktrace());
        } else {
            System.out.println(log.getMessage());
        }
    }

//    public static void create(String className, Exception e) {
//        Log log = new Log(className, Level.WARNING, e.getMessage(), LocalDateTime.now(), getStringStackTrace(e));
//        logs.add(log);
//        saveInFile(log);
//        e.printStackTrace();
//    }

//    public void add(Log log) {
//        logs.add(log);
//        saveInFile(log);
//        if (log.getLevel() == Level.WARNING || log.getLevel() == Level.ERROR) {
//            System.out.println(log.getStacktrace());
//        } else {
//            System.out.println(log.getMessage());
//        }
//    }


    private static void saveInFile(Log log) {
        logService.writeToFile(log);
    }

    public void loadLogs() {
        logs = logService.readFile();
    }

}
