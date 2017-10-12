
package teamthreeproject;
import java.util.*;
/**
 *
 * @author Travis , Kyle 
 */
public class Punch {

    private String adjusted_time_stamp;
    private String id;
    private String terminal_id;
    private String event_type_id;
    private String badge_id;
    private String original_time_stamp;
    private String event_data;
    private Calendar calendar;
    
    
    public Punch(String id, String terminal_id, String badge_id,
                 String original_time_stamp, String event_type_id, 
                 String event_data, String adjusted_time_stamp) {
        
        calendar = toGregorian(original_time_stamp);
        this.id = id;
        this.terminal_id = terminal_id;
        this.badge_id = badge_id;
        
        this.adjusted_time_stamp = adjusted_time_stamp;
        this.badge_id = badge_id;
        this.event_type_id = event_type_id;
        this.id = id;
        this.original_time_stamp = original_time_stamp;
        this.terminal_id = terminal_id;
        this.event_data = event_data;
        
        
    }
    
    private Calendar toGregorian(String orignal_time_stamp) {
        
        int year = Integer.parseInt(orignal_time_stamp.substring(0,4));
        int month = Integer.parseInt(orignal_time_stamp.substring(5,7));
        int day = Integer.parseInt(orignal_time_stamp.substring(8,10));
        int hour = Integer.parseInt(orignal_time_stamp.substring(11,13));
        int minute = Integer.parseInt(orignal_time_stamp.substring(14,16));
        int second = Integer.parseInt(orignal_time_stamp.substring(17,19));
        
        calendar = GregorianCalendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
        return calendar;
    }
    
    private String getEventType(String event_type_id) {
        if (event_type_id.equals("0")) 
            return "Clocked Out: ";
        else if (event_type_id.equals("1")) 
            return "Clocked In: ";
        else 
            return "Timed Out: ";
    }
    
    public String printOrignalTimeStamp() {
        return "";
    }
    
    public void setAdjustedTime(String adj_time){
        adjusted_time_stamp = adj_time;
    }
   
    public String printAdjustedTime(){
        return adjusted_time_stamp;
       
    }
    
    
    
    
}
