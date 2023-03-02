package util;

import loger.Log;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class WatchDirectory implements Runnable {
    private final Path path;

    public WatchDirectory(Path path) {
        this.path = path;

    }

    private void creatWatcher() throws IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, ENTRY_MODIFY);
        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_MODIFY) {
                        //считать с проперти(и создать проперти)

                    } else {
                        String massage = "Unsupported event kind";
                        System.out.println(massage);
                        Log.info(WatchDirectory.class.getName(), massage);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.error(WatchDirectory.class.getName(), "method creatWatcher", e);
                return;
            }
            if (!key.reset()) {
                break;
            }
        }
        Log.debug(WatchDirectory.class.getName(), "method creatWatcher");
    }

    @Override
    public void run() {
        try {
            creatWatcher();
        } catch (IOException e) {
            System.err.println(e.getMessage());

        }
        Log.debug(WatchDirectory.class.getName(), "method run");
    }
}