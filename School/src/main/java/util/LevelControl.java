package util;

import loger.Log;
import repository.log.LogRepository;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class LevelControl implements Runnable {
    private final Path path;

    public LevelControl(Path path) {
        this.path = path;

    }
    private void creatWatcher() throws IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        System.out.println(path);
        try {
            path.register(watchService, ENTRY_MODIFY);
        } catch (IOException e) {
            System.out.println("reg");
            e.printStackTrace();
        }
        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_MODIFY) {
                        LogRepository.setWriteLevel(PropertyLevel.getLevel());
                        System.out.println("CHANGE");
                        key.reset();
                    } else {
                        String massage = "Unsupported event kind";
                        System.out.println(massage);
                        Log.info(LevelControl.class.getName(), massage);
                    }
                }
            } catch (Exception e) {
                Log.error(LevelControl.class.getName(), "method creatWatcher", e);
                return;
            }
            if (!key.reset()) {
                break;
            }
        }
        Log.debug(LevelControl.class.getName(), "method creatWatcher");
    }

    @Override
    public void run() {
        try {
            creatWatcher();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());

        }
        Log.debug(LevelControl.class.getName(), "method run");
    }
}