package repository.log;

import loger.Level;
import loger.Log;
import service.log.LogService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LogRep {

    private ArrayList<Log> logs;

    private LogService logService;

    public LogRep(LogService logService) {
        this.logService = logService;
        this.logs = new ArrayList<>();
        loadLogs();
    }

    public void create(String name, Level level, String message, LocalDateTime date, String stacktrace){
        Log log = new Log(name, level, message, date, stacktrace);
        logs.add(log);
        saveInFile(log);
    }

    public void create(Log log){
        logs.add(log);
        saveInFile(log);
    }

    private void saveInFile(Log log){
        logService.writeToFile(log);
    }

    public void loadLogs(){
        logs = logService.readFile();
    }

}
