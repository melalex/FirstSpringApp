package ua.room414.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.room414.spring.core.beans.Client;
import ua.room414.spring.core.beans.Event;
import ua.room414.spring.core.enums.EventType;
import ua.room414.spring.core.loggers.EventLogger;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;
    private static ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        App app = (App) applicationContext.getBean("app");
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
