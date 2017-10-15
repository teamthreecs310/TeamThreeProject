
package teamthreeproject;
import java.util.*;
/**
 *
 * @author TeamThree
 */
public class Punch {

    private String adjusted_time_stamp;
    private int id;
    private String terminal_id;
    private String event_type_id;
    private String badge_id;
    private String original_time_stamp;
    private String event_data;
    private Calendar calendar;
    
    private Calendar toGregorian(String original_time_stamp) {
        
        // fix formatting using Gregorian Calendar objects and getTimeInMillis()
        int year = Integer.parseInt(original_time_stamp.substring(0,4));
        int month = Integer.parseInt(original_time_stamp.substring(5,6));
        int day = Integer.parseInt(original_time_stamp.substring(8,10));
        int hour = Integer.parseInt(original_time_stamp.substring(11,13));
        int minute = Integer.parseInt(original_time_stamp.substring(14,16));
        int second = Integer.parseInt(original_time_stamp.substring(17,19));
        
        calendar = GregorianCalendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
        return calendar;
    }
    public void setID(int n){
        id= n;
    }
    public void setTerminalID(String s){
        terminal_id = s;
    }
    
    public void setBadgeID(String s){
        badge_id = s;
    }
    public void setOriginalTimeStamp(String s){
        original_time_stamp = s;
    }
    public void setEventTypeID(String s){
        event_type_id = s;
    }
    public void setEventData(String s){
        event_data = s;
    }
    public void setAdjustedTimeStamp(String s){
        adjusted_time_stamp = s;
    }
    public int getID(){
        return id;
    }
    public String getTerminalID(){
        return terminal_id;
    }
    public String getOriginalTimeStamp(){
        return original_time_stamp;
    }
    public String getEventTypeID(){
        return event_type_id;
    }
    public String getEventData(){
        return event_data;
    }
    public String getAdjustedTimeStamp(){
        return adjusted_time_stamp;
    }
    public String getEventType(String event_type_id) {
        if (event_type_id.equals("0")) 
            return "Clocked Out: ";
        else if (event_type_id.equals("1"))
            return "Clocked In: ";
        else 
            return "Timed Out: ";
    }
    public String printOriginalTimeStamp(){
        return badge_id + " CLOCKED IN: " + toGregorian(getOriginalTimeStamp());
    }
    
    
    
    
    
}
