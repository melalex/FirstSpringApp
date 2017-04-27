package ua.room414.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ua.room414.spring.core.beans.Client;
import ua.room414.spring.core.beans.Event;
import ua.room414.spring.core.enums.EventType;
import ua.room414.spring.core.loggers.EventLogger;

import java.util.Map;

@Component("app")
public class App {
    private static final ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    @Autowired
    public App(Client client, @Qualifier("cachedFileLogger") EventLogger defaultLogger, @Qualifier("eventTypeEventLoggerMap") Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
        this.client = client;
    }

    public static void main(String[] args) {
        App app = applicationContext.getBean("app", App.class);
        applicationContext.registerShutdownHook();

        app.logEvent("Some event from user 1");
        app.logEvent("Some event from user 2", EventType.INFO);
        app.logEvent("Some event from user 3", EventType.ERROR);
    }

    private void logEvent(String msg) {
        logEvent(msg, null);
    }

    private void logEvent(String msg, EventType type) {
        String message = msg.replaceAll("" + client.getId(), client.getFullName());
        Event event = applicationContext.getBean("event", Event.class);
        event.setMessage(message);

        EventLogger logger = loggers.get(type);
        if (type == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
