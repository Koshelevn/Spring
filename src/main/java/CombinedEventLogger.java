import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger : loggers
                ) {
            logger.logEvent(event);
        }
    }

}