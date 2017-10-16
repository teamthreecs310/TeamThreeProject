
package teamthreeproject;
import java.util.*;
import java.text.*;
/**
 *
 * @author TeamThree
 */
public class Punch {

    //private Calendar adjusted_time_stamp;
    private int punch_id;
    private int terminal_id;
    private int event_type_id;
    private String badge_id;
    private long ots;
    private Calendar original_time_stamp = Calendar.getInstance();
    //private int event_data;
    //private Calendar calendar;
    
    public Punch(int id, int terminal_id, String badge_id, long ots, int event_type_id){
        this.punch_id = id;
        this.terminal_id = terminal_id;
        this.badge_id = badge_id;
        this.ots = ots;
        this.event_type_id = event_type_id;
    }
    
    public int getID(){
        return punch_id;
    }
    public int getTerminalID(){
        return terminal_id;
    }
    public String getBadgeID(){
        return badge_id;
    }
    
    public Calendar getOriginalTimeStamp() {
        original_time_stamp.setTimeInMillis(ots*1000);
        return original_time_stamp;
    }
    
    public String getDay() {
        String day = null;
        switch (getOriginalTimeStamp().get(Calendar.DAY_OF_WEEK)) {
            case 1:
                day = "SUN";
                break;
            case 2:
                day = "MON";
                break;
            case 3:
                day = "TUE";
                break;
            case 4:
                day = "WED";
                break;
            case 5:
                day = "THU";
                break;
            case 6:
                day = "FRI";
                break;
            case 7:
                day = "SAT";
                break;
            default:
                break;
        }
        return day;
    }
    
    public int getEventTypeID(){
        return event_type_id;
    }
    //public int getEventData(){
        //return event_data;
    //}
    //public Calendar getAdjustedTimeStamp(){
        //return adjusted_time_stamp;
    //}
    public String getEventType(int event_type_id) {
        switch (event_type_id) {
            case 0:
                return "CLOCKED OUT: ";
            case 1:
                return "CLOCKED IN: ";
            default:
                return "TIMED OUT: ";
        }
    }
    public String printOriginalTimeStamp(){
        return "#" + badge_id + " " + getEventType(event_type_id) + getDay() +
                (new SimpleDateFormat(" MM/dd/yyyy HH:mm:ss")).format(getOriginalTimeStamp().getTime());
    }
    
    
    
    
    
}
