import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    @Autowired
    public void setEventLogger(EventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }
    @Autowired
    @Qualifier("fileEventLogger")
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;
    @Autowired
    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationContext ctx1 = new AnnotationConfigApplicationContext(AppConfig.class);
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

