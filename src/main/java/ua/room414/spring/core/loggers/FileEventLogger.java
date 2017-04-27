package ua.room414.spring.core.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ua.room414.spring.core.beans.Event;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author Alexander Melashchenko
 * @version 1.0 26 Apr 2017
 */
@Component
@PropertySource("classpath:log.properties")
public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;


    @Autowired
    FileEventLogger(@Value("${log.file}") String filename) {
        this.filename = filename;
    }

    @PostConstruct
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
