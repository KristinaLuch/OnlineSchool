package repository.log;

import loger.Level;
import loger.Log;
import service.log.LogService;

import java.util.ArrayList;

public class LogRepository {

    private static ArrayList<Log> logs;

    private static LogService logServiceSt;

    private static Level writeLevel = Level.OFF;

    private static final Level printLevel = Level.INFO;

    public LogRepository(LogService logService) {
        logServiceSt = logService;
        logs = new ArrayList<>();
        loadLogs();
    }


    public static void add(Log log) {
        if (log.getLevel().ordinal() >= writeLevel.ordinal()) {
            logs.add(log);
            //saveInFile(log);
        }
        printLog(log);
    }

    public static void printLog(Log log){
        if(log.getLevel().ordinal() < printLevel.ordinal()){
            return;
        }
        if (log.getLevel() == Level.ERROR) {
            System.err.println(log.getStacktrace());
        } else {
            System.out.println(log.getMessage());
        }
    }

    public static void setWriteLevel(Level level){
        if (isCorrectLevel(level)){writeLevel = level;}
    }

    private static boolean isCorrectLevel(Level level){
        Level [] levels = Level.values();
        for (Level level1 : levels) {
            if(level == level1){
                return true;
            }
        }
        return false;
    }

    private static void saveInFile(Log log) {
        logServiceSt.writeToFile(log);
    }

    public void loadLogs() {
        logs = logServiceSt.readFile();
        if (logs == null){
            logs = new ArrayList<>();
        }
    }

}
