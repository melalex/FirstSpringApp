package ua.room414.spring.core.loggers;

import org.springframework.stereotype.Component;
import ua.room414.spring.core.beans.Event;

/**
 * @author Alexander Melashchenko
 * @version 1.0 26 Apr 2017
 */
@Component("eventLogger")
public interface EventLogger {
    void logEvent(Event event);
}
