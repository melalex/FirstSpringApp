package ua.room414.spring.core.loggers;

import ua.room414.spring.core.beans.Event;

import java.util.List;

/**
 * @author Alexander Melashchenko
 * @version 1.0 27 Apr 2017
 */
public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(l -> l.logEvent(event));
    }
}
