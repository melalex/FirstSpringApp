package ua.room414.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ua.room414.spring.core.beans.Event;
import ua.room414.spring.core.enums.EventType;
import ua.room414.spring.core.loggers.EventLogger;

import java.text.DateFormat;
import java.util.*;

/**
 * @author Alexander Melashchenko
 * @version 1.0 27 Apr 2017
 */
@Configuration
@ComponentScan("ua.room414.spring.core")
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    @Autowired
    public Map<EventType, EventLogger> eventTypeEventLoggerMap(
            @Qualifier("consoleEventLogger") EventLogger consoleEventLogger,
            @Qualifier("combinedEventLogger") EventLogger combinedEventLogger
    ) {
        Map<EventType, EventLogger> newMap = new HashMap<>();

        newMap.put(EventType.INFO, consoleEventLogger);
        newMap.put(EventType.ERROR, combinedEventLogger);

        return newMap;
    }

    @Bean
    @Autowired
    public List<EventLogger> eventLoggers(
            @Qualifier("consoleEventLogger") EventLogger consoleEventLogger,
            @Qualifier("fileEventLogger") EventLogger combinedEventLogger
    ) {
        List<EventLogger> loggers = new LinkedList<>();

        loggers.add(consoleEventLogger);
        loggers.add(combinedEventLogger);

        return loggers;
    }

    @Bean
    public Event event() {
        return new Event(new Date(), DateFormat.getDateInstance());
    }
}
