package ua.room414.spring.core.loggers;

import ua.room414.spring.core.beans.Event;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexander Melashchenko
 * @version 1.0 26 Apr 2017
 */
public class CachedFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache = new LinkedList<>();

    public CachedFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    public CachedFileEventLogger(String filename) {
        super(filename);
    }

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
