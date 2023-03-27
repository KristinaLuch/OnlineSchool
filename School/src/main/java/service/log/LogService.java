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
                boolean create = file.createNewFile();
                System.out.println("file create: "+create);
                Log.info(this.getClass().getName(), "createFile mtd, create file");
            } catch (IOException e) {
                System.out.println("create logs file error! File path = " + path);
                //Log.error(this.getClass().getName(), "method createFile", e);
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

    public ArrayList<Log> readFile() {
        Log.info(this.getClass().getName(), "readFile mtd");
        ArrayList<Log> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String dateStr = reader.readLine();
            while ((dateStr!= null)) {
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
                if (stacktraceBegin == null){
                    break;
                }
                if (stacktraceBegin.equals("Stacktrace: {")){
                    while (true){
                        cont = reader.readLine();
                        stacktraceBegin += "\n"+cont;
                        if(cont.equals("}")){
                            cont = "";
                            dateStr = reader.readLine();
                            break;
                        }
                    }
                    stacktrace = stacktraceBegin;
                } else {
                    dateStr = stacktraceBegin;
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

    public void printInfoCountOfMiddleOfFile(){
        List<String> lines;
        try (Stream<String> lineStream = Files.lines(Path.of(file.getPath()))) {
            List<String> fileLines = lineStream.toList();
            if (fileLines.size() == 0){
                System.out.println("Count of logs INFO starting from the middle of the file: 0. File is empty");
            }
            long countInfoLogs = fileLines.stream().skip(fileLines.size()/2).filter(line -> line.contains("INFO")).count();
            System.out.println("Count of logs INFO starting from the middle of the file: "+countInfoLogs);
        } catch (IOException e) {
            Log.error(String.valueOf(LogService.class), "method showMessage", e);
        }
    }
}
