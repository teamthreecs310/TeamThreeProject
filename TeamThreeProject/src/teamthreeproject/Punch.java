
package teamthreeproject;
import java.util.*;
/**
 *
 * @author TeamThree
 */
public class Punch {

    private Calendar adjusted_time_stamp;
    private int id;
    private int terminal_id;
    private int event_type_id;
    private String badge_id;
    private Calendar original_time_stamp;
    private int event_data;
    private Calendar calendar;
    public Punch(int id, int terminal_id, String badge_id, Long original_time_stamp, int event_type_id,
                 int event_data, Long adjusted_time_stamp){
        this.id = id;
        this.terminal_id = terminal_id;
        this.badge_id = badge_id;
        this.original_time_stamp.setTimeInMillis(original_time_stamp);
        this.event_type_id = event_type_id;
        this.adjusted_time_stamp.setTimeInMillis(adjusted_time_stamp);
    }
    
    public void setID(int n){
        id= n;
    }
    public void setTerminalID(int s){
        terminal_id = s;
    }
    
    public void setBadgeID(String s){
        badge_id = s;
    }
    public void setOriginalTimeStamp(Long s){
        original_time_stamp.setTimeInMillis(s);
    }
    public void setEventTypeID(int s){
        event_type_id = s;
    }
    public void setEventData(int s){
        event_data = s;
    }
    public void setAdjustedTimeStamp(Long s){
        adjusted_time_stamp.setTimeInMillis(s);
    }
    public int getID(){
        return id;
    }
    public int getTerminalID(){
        return terminal_id;
    }
    public Calendar getOriginalTimeStamp(){
        return original_time_stamp;
    }
    public int getEventTypeID(){
        return event_type_id;
    }
    public int getEventData(){
        return event_data;
    }
    public Calendar getAdjustedTimeStamp(){
        return adjusted_time_stamp;
    }
    public String getEventType(int event_type_id) {
        if (event_type_id == 0) return "Clocked Out: "; 
        else if (event_type_id == 1) return "Clocked In: ";
        else return "Timed Out: ";
    }
    public String printOriginalTimeStamp(){
        return "";
    }
    
    
    
    
    
}
