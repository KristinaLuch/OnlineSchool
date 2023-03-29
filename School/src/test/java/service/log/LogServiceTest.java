package service.log;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.log.LogService;
import loger.Level;
import loger.Log;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogServiceTest {
    private File file;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    @Before
    public void createFile() {
        // Создаем временный файл перед каждым тестом
        file = new File("test.txt");
    }

    @After
    public void deleteFile() {
        // Удаляем временный файл после каждого теста
        file.delete();
    }

    @Test
    void testWriteToFile() throws IOException {
        createFile();
        // Create LogService and Log objects
        LogService logService = new LogService(file.getPath());
        Log log = new Log("Test", Level.INFO, "Test message", LocalDateTime.now(), null);

        // Write to file
        logService.writeToFile(log);

        // Read from file and check content
        List<String> lines = Files.readAllLines(Path.of(file.getPath()));
        deleteFile();
        Assertions.assertEquals(4, lines.size());
        Assertions.assertEquals(log.getDate().format(LogService.DATE_TIME_FORMATTER), lines.get(0));
        Assertions.assertEquals(log.getLevel().toString(), lines.get(1));
        Assertions.assertEquals(log.getName(), lines.get(2));
        Assertions.assertEquals("Message:" + log.getMessage().get(), lines.get(3));
    }

    @Test
    void testReadFile() throws IOException {
        createFile();
        // Create LogService and Log objects
        LogService logService = new LogService(file.getPath());
        Log log = new Log("Test", Level.INFO, "Test message", LocalDateTime.now(), null);

        // Write to file
        logService.writeToFile(log);


        // Read from file
        ArrayList<Log> logs = logService.readFile();
        //deleteFile();
        System.out.println("size = "+logs.size());
        deleteFile();
        Assertions.assertEquals(1, logs.size());

        // Check Log object
        Log logFromFile = logs.get(0);

        Assertions.assertEquals(log.getDate().format(DATE_TIME_FORMATTER), logFromFile.getDate().format(DATE_TIME_FORMATTER));
        Assertions.assertEquals(log.getLevel(), logFromFile.getLevel());
        Assertions.assertEquals(log.getName(), logFromFile.getName());
        Assertions.assertEquals(log.getMessage(), logFromFile.getMessage());
        Assertions.assertEquals(log.getStacktrace(), logFromFile.getStacktrace());
    }

    @Test
    void testShowMessage() throws IOException {
        createFile();
        // Create LogService and Log objects
        LogService logService = new LogService(file.getPath());
        Log log = new Log("Test", Level.INFO, "Test message", LocalDateTime.now(), null);

        // Write to file
        logService.writeToFile(log);

        // Show message
        logService.showMessage();
        deleteFile();
    }

    @Test
    void testPrintInfoCountOfMiddleOfFile() throws IOException {
        createFile();
        // Create LogService and Log objects
        LogService logService = new LogService(file.getPath());
        Log log1 = new Log("Test1", Level.INFO, "Test message 1", LocalDateTime.now(), null);
        Log log2 = new Log("Test2", Level.INFO, "Test message 2", LocalDateTime.now(), null);
        Log log3 = new Log("Test3", Level.ERROR, "Test message 3", LocalDateTime.now(), null);

        // Write to file
        logService.writeToFile(log1);
        logService.writeToFile(log2);
        logService.writeToFile(log3);

        // Print count of info logs
        logService.printInfoCountOfMiddleOfFile();
        deleteFile();
    }
}