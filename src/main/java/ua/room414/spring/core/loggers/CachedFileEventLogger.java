package ua.room414.spring.core.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.room414.spring.core.beans.Event;

import javax.annotation.PreDestroy;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexander Melashchenko
 * @version 1.0 26 Apr 2017
 */
@Component("cachedFileLogger")
public class CachedFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache = new LinkedList<>();

    @Autowired
    public CachedFileEventLogger(@Value("${log.file}") String filename, @Value("${cache.size}") int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @PreDestroy
    private void destroy() {
        if (!cache.isEmpty()) {
            cache.forEach(super::logEvent);
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            cache.forEach(super::logEvent);
            cache.clear();
        }
    }
}
