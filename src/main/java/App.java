import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private ConsoleEventLogger eventLogger;
    public App(Client client, ConsoleEventLogger logger){
        this.client = client;
        this.eventLogger = logger;
    }
    void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
    //   eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent("Event for 1");
        app.logEvent("Event for 2");
        ctx.close();
    }
}
