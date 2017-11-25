import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;
    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationContext child = new ClassPathXmlApplicationContext("loggers.xml", "spring.xml");
        App app = (App) ctx.getBean("app");
        Event event1 = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");
        app.logEvent(EventType.INFO, event1, "Hello");
        app.logEvent(EventType.ERROR, event2, "Second");
        ctx.close();
    }
    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = eventLogger;
        }
        logger.logEvent(event);
    }
}

