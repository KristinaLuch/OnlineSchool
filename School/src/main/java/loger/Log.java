package loger;

import repository.log.LogRepository;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

public class Log {

    private String name;
    private Level level;
    private String message;
    private LocalDateTime date;
    private String stacktrace;

    public Log(String name, Level level, String message, LocalDateTime date, String stacktrace) {
        this.name = name;
        this.level = level;
        this.message = message;
        this.date = date;
        this.stacktrace = stacktrace;
    }

    public Log(String name, Level level, String message, LocalDateTime date) {
        this.name = name;
        this.level = level;
        this.message = message;
        this.date = date;
    }

    public static void debug(String name, String message){
        Log log = new Log(name, Level.DEBUG, message, LocalDateTime.now());
        LogRepository.add(log);
    }
    public static void info(String name, String message){
        Log log = new Log(name, Level.INFO, message, LocalDateTime.now());
        LogRepository.add(log);
    }
    public static void warning (String name, String message){
        Log log = new Log(name, Level.WARNING, message, LocalDateTime.now());
        LogRepository.add(log);
    }
    public static void error(String name, String message, Exception e){
        Log log = new Log(name, Level.ERROR, message, LocalDateTime.now());
        LogRepository.add(log);
    }

    private static String getStringStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    @Override
    public String toString() {
        return "Log{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", stacktrace='" + stacktrace + '\'' +
                '}';
    }
}
