
package teamthreeproject;
import java.util.*;
import java.text.*;
/**
 *
 * @author TeamThree
 */
public class Punch {

    private int punch_id;
    private int terminal_id;
    private int event_type_id;
    private String badge_id;
    private Long ots;
    private Calendar original_time_stamp;
    private Calendar adjusted_time_stamp;
    //private int event_data;
    
    //Constructor for retrieving existing punches in the database
    public Punch(int id, int terminal_id, String badge_id, Long ots, int event_type_id){
        this.punch_id = id;
        this.terminal_id = terminal_id;
        this.badge_id = badge_id;
        this.ots = ots*1000;
        this.event_type_id = event_type_id;
    }
    
    //Constructor for inserting new punches into the database
    public Punch(String badgeid, int terminalid, int punchtypeid) {
        original_time_stamp = Calendar.getInstance();
        adjusted_time_stamp = null;
        punch_id = 0;
        this.badge_id = badgeid;
        this.terminal_id = terminalid;
        this.event_type_id = punchtypeid;
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
    
    public Calendar getOriginalTimestamp() {
        original_time_stamp = Calendar.getInstance();
        original_time_stamp.setTimeInMillis(ots);
        return original_time_stamp;
    }
    
    public Calendar getAdjustedTimestamp() {
        adjusted_time_stamp = Calendar.getInstance();
        return adjusted_time_stamp;
    }
    
    public String getDay() {
        String day = null;
        switch (getOriginalTimestamp().get(Calendar.DAY_OF_WEEK)) {
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
    
    //public Calendar getAdjustedTimestamp(){
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
    public String printOriginalTimestamp(){
        return "#" + badge_id + " " + getEventType(event_type_id) + getDay() +
                (new SimpleDateFormat(" MM/dd/yyyy HH:mm:ss")).format(getOriginalTimestamp().getTime());
    }
    
    public void adjust(Shift s) {
        
        /* for testing purposes only, will need to be remodeled (possibly with recursion for checking for timestamps way outside range
        and adjusting to correct interval */
        
        if (this.ots < s.getStartTimeInMillis(getOriginalTimestamp())) {
            if (this.ots < s.getStartTimeIntervalInMillis(getOriginalTimestamp())) {
                if (this.ots < (s.getStartTimeIntervalInMillis(getOriginalTimestamp())-(s.getInterval()*60000))) {
                    getAdjustedTimestamp().setTimeInMillis(s.getStartTimeIntervalInMillis(getOriginalTimestamp())-(s.getInterval()*60000));
                }
                else {
                    getAdjustedTimestamp().setTimeInMillis(s.getStartTimeIntervalInMillis(getOriginalTimestamp()));
                }
            }
            else {
                getAdjustedTimestamp().setTimeInMillis(s.getStartTimeInMillis(getOriginalTimestamp()));
            }    
        }
        else if (this.ots < s.getStartTimeGraceInMillis(getOriginalTimestamp())) {
            getAdjustedTimestamp().setTimeInMillis(s.getStartTimeInMillis(getOriginalTimestamp()));
        }
        else if (this.ots < s.getStartTimeDockInMillis(getOriginalTimestamp())) {
            getAdjustedTimestamp().setTimeInMillis(s.getStartTimeDockInMillis(getOriginalTimestamp()));
        }
        else if (this.ots < (s.getStartTimeDockInMillis(getOriginalTimestamp())+(s.getDock()*60000))) {
            getAdjustedTimestamp().setTimeInMillis(s.getStartTimeIntervalInMillis(getOriginalTimestamp())+(s.getDock()*60000));
        }  
    }
    
    public String printAdjustedTimestamp() {
        return "#" + badge_id + " " + getEventType(event_type_id) + getDay() +
                (new SimpleDateFormat(" MM/dd/yyyy HH:mm:ss")).format(getAdjustedTimestamp().getTime());
    }
    
    
    
}
