package repository.log;

import loger.Level;
import loger.Log;
import service.log.LogService;

import java.util.ArrayList;

public class LogRepository {

    private static ArrayList<Log> logs;

    private static LogService logService;

    private static Level writeLevel = Level.OFF;



    public LogRepository(LogService logService) {
        this.logService = logService;
        this.logs = new ArrayList<>();
        loadLogs();
    }


    public static void add(Log log) {
        System.out.println("wl "+writeLevel+" "+writeLevel.ordinal());
        System.out.println("lgl "+log.getLevel()+" "+log.getLevel().ordinal());
        System.out.println(log.getLevel().ordinal() >= writeLevel.ordinal());
        if (log.getLevel().ordinal() >= writeLevel.ordinal()) {
            logs.add(log);
            saveInFile(log);
            printLog(log);
        }
    }

    public static void printLog(Log log){
        if (log.getLevel() == Level.ERROR) {
            System.err.println(log.getStacktrace());
        } else {
            System.out.println(log.getMessage());
        }
    }

    public static void setWriteLevel(Level level){
        writeLevel = level;
    }

    private static void saveInFile(Log log) {
        logService.writeToFile(log);
    }

    public void loadLogs() {
        logs = logService.readFile();
    }

}
