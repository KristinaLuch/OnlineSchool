package util;

import loger.Level;
import loger.Log;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyLevel {

    private static String path;

    public static void setPath(String path) {
        PropertyLevel.path = path;
    }

    public static Level getLevel(){
        FileInputStream fis;
        Properties property = new Properties();
        Level level = Level.OFF;
        try {
            fis = new FileInputStream(path);
            property.load(fis);
            level = Level.valueOf(property.getProperty("level"));
        } catch (Exception e) {
            Log.error(PropertyLevel.class.getName(), "File not found", e);
        }
        return level;
    }

}
