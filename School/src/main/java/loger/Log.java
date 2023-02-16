package loger;

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
