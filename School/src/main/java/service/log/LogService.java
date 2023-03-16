package service.log;

import loger.Level;
import loger.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LogService {

    private File file;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    public LogService(String path) {
        createFile(path);
    }


    private void createFile(String path) {
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.error(this.getClass().getName(), "method createFile", e);
            }
        }
    }

    public void writeToFile(Log log) {

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(log.getDate().format(DATE_TIME_FORMATTER) + "\n");
            fileWriter.write(log.getLevel().toString() + "\n");
            fileWriter.write(log.getName() + "\n");
            if (log.getMessage().isPresent()) {
                fileWriter.write("Message:" + log.getMessage().get() + "\n");
            }
            if(log.getStacktrace().isPresent()) {
                fileWriter.write("Stacktrace: {\n" + log.getStacktrace().get() + "\n}\n");
            }
        } catch (IOException e) {
            Log.error(this.getClass().getName(), "method writeToFile", e);
        }
    }

//    public boolean existFile(){
//
//    }

    public ArrayList<Log> readFile() {
        ArrayList<Log> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String dateStr;
            while ((dateStr = reader.readLine()) != null) {
                LocalDateTime date = getDate(dateStr);
                Level level = Level.valueOf(reader.readLine());
                String name = reader.readLine();
                String message = reader.readLine();
                String stacktraceBegin;
                if(message.contains("Message:")){
                    stacktraceBegin= reader.readLine();
                } else {
                    stacktraceBegin = message;
                    message = null;
                }
                String stacktrace = "";
                String cont = "";

                if (stacktraceBegin.equals("Stacktrace: {")){
                    while (true){
                        cont = reader.readLine();
                        stacktraceBegin += "\n"+cont;
                        if(cont.equals("}")){
                            cont = "";
                            break;
                        }
                    }
                    stacktrace = stacktraceBegin;
                    stacktraceBegin = "";
                }
                Log log = new Log(name, level, message, date, stacktrace);
                logs.add(log);
            }
        } catch (Exception e) {
            Log.error(this.getClass().getName(), "method readFile", e);
        }
        return logs;
    }

    //2023-03-02T22:12:02.496786100
    public LocalDateTime getDate(String dateString) {

        return LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
    }

    public void showMessage(){
        List<String> lines;
        try (Stream<String> lineStream = Files.lines(Path.of(file.getPath()))) {
            lineStream.filter(line -> line.contains("Message:")).forEach(System.out::println);
        } catch (IOException e) {
            Log.error(String.valueOf(LogService.class), "method showMessage", e);
        }
    }
}
