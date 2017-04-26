package ua.room414.spring.core.loggers;

import org.apache.commons.io.FileUtils;
import ua.room414.spring.core.beans.Event;

import java.io.File;
import java.io.IOException;

/**
 * @author Alexander Melashchenko
 * @version 1.0 26 Apr 2017
 */
public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    protected void init() throws IOException {
        file = new File(filename);
        file.createNewFile();
        if (!file.canWrite()) {
            throw new IOException("Can't write to file " + filename);
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
