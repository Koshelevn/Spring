
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    Random random = new Random();
    private int id = random.nextInt(10);
    private DateFormat df;

    public Event(Date date) {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private Date date;
    public Event(Date date, DateFormat dateFormat){
        this.date=date;
        this.df=dateFormat;
    }

    public String toString() {
        return Integer.toString(id) + " " + msg + " " + df.format(date);
    }
}
