package ua.room414.spring.core.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.room414.spring.core.beans.Event;

import java.util.List;

/**
 * @author Alexander Melashchenko
 * @version 1.0 27 Apr 2017
 */
@Component("combinedEventLogger")
public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    @Autowired
    public CombinedEventLogger(@Qualifier("eventLoggers") List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(l -> l.logEvent(event));
    }
}
