
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
    //private Long ats;
    private GregorianCalendar original_time_stamp = new GregorianCalendar();
    private GregorianCalendar adjusted_time_stamp = new GregorianCalendar();
    private String event_data;
    private String day;
    private int unrounded_min;
    private int mod;
    
    //Constructor for retrieving existing punches in the database
    public Punch(int id, int terminal_id, String badge_id, long ots, int event_type_id, String event_data){
        this.punch_id = id;
        this.terminal_id = terminal_id;
        this.badge_id = badge_id;
        this.ots = ots*1000;
        this.event_type_id = event_type_id;
        original_time_stamp.setTime(new Date(this.ots));
        this.event_data = event_data;
        //this.ats = ats*1000;
    }
    
    //Constructor for inserting new punches into the database
    public Punch(String badgeid, int terminalid, int punchtypeid) {
        this.original_time_stamp = new GregorianCalendar();
        adjusted_time_stamp = null;
        punch_id = 0;
        this.badge_id = badgeid;
        this.terminal_id = terminalid;
        this.event_type_id = punchtypeid;
        this.event_data = null;
    }
    public void setID(int id){
        this.punch_id = id;
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
    
    public GregorianCalendar getOriginalTimestamp() {
        return original_time_stamp;
    }
    
    public GregorianCalendar getAdjustedTimestamp() {
        return adjusted_time_stamp;
    }
    
    public String getDay() {
        switch (getOriginalTimestamp().get(GregorianCalendar.DAY_OF_WEEK)) {
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
    
    public String getEventData(){
        return event_data;
    }
    
    
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
        
        TASDatabase db = new TASDatabase();
        
        if (getDay() == "SAT" || getDay() == "SUN") {
            //interval rounding for weekend shifts
            intervalRounding(s);
        }
        else {
            // checks for clock-in or clock-out and adjusts time as necessary
            if(event_type_id == 1){
                if (this.ots < s.getLunchStartInMillis(getOriginalTimestamp())) {
                    adjustShiftStart(s);
                }
                else if (this.ots > s.getLunchStartInMillis(getOriginalTimestamp())) {
                    adjustLunchStop(s);
                }
            } 
            else if(event_type_id == 0){
                if (this.ots < s.getLunchStopInMillis(getOriginalTimestamp())) {
                    adjustLunchStart(s);
                }
                else if (this.ots > s.getLunchStopInMillis(getOriginalTimestamp())) {
                    adjustShiftStop(s);
                }
            }
        }
        
        db.insertAdjusted(getAdjustedTimestamp(), this.punch_id);
        
    }
    
    public void intervalRounding(Shift s){
        unrounded_min = getOriginalTimestamp().get(Calendar.MINUTE);
        mod = unrounded_min % s.getInterval();
        getAdjustedTimestamp().setTimeInMillis(ots);
        
        if (mod == 0) {
            event_data = "(None)";
        }
        else if (mod < 8) {
            getAdjustedTimestamp().set(Calendar.MINUTE, unrounded_min - mod);
            getAdjustedTimestamp().set(Calendar.SECOND, 0);
            getAdjustedTimestamp().set(Calendar.MILLISECOND, 0);
            event_data = "(Interval Round)";
        }
        else {
            getAdjustedTimestamp().set(Calendar.MINUTE, (unrounded_min + (s.getInterval() - mod)));
            getAdjustedTimestamp().set(Calendar.SECOND, 0);
            getAdjustedTimestamp().set(Calendar.MILLISECOND, 0); 
            event_data = "(Interval Round)";
        }
    }
    public void adjustShiftStart(Shift s){
        unrounded_min = getOriginalTimestamp().get(Calendar.MINUTE);
        mod = unrounded_min % s.getInterval();
        getAdjustedTimestamp().setTimeInMillis(ots);
        event_data = "(Shift Start)";
        
        if (this.ots < s.getStartTimeInMillis(getOriginalTimestamp())) {
            //Time is before shift start
            if (this.ots > s.getStartTimeIntervalInMillis(getOriginalTimestamp())) {
                //Time is 15 minutes or less before shift start and gets pushed forward
                getAdjustedTimestamp().setTimeInMillis(s.getStartTimeInMillis(getOriginalTimestamp()));
            }
            else {
                //Time is 15 minutes or more before shift start and gets rounded to nearest 15 minute interval
                intervalRounding(s);
                }
        }
        
        else if (this.ots > s.getStartTimeInMillis(getOriginalTimestamp())) {
            //Time is after shift start
            if (this.ots < s.getStartTimeGraceInMillis(getOriginalTimestamp())) {
                //Time falls within grace period (5 min or less after shift start) and is pushed back to start
                getAdjustedTimestamp().setTimeInMillis(s.getStartTimeInMillis(getOriginalTimestamp()));
            }
            else if (this.ots < s.getStartTimeDockInMillis(getOriginalTimestamp())) {
                //Time falls outside of grace period but within 15 minutes after start and is pushed ahead
                getAdjustedTimestamp().setTimeInMillis(s.getStartTimeDockInMillis(getOriginalTimestamp()));
                event_data = "(Shift Start(Dock))";
            }
            else {
                //Time is more than 15 minutes after start of shift and is rounded to nearest 15 minute interval
                intervalRounding(s);
            }      
        }               
    }
    
    private void adjustShiftStop(Shift s){
        unrounded_min = getOriginalTimestamp().get(Calendar.MINUTE);
        mod = unrounded_min % s.getInterval();
        getAdjustedTimestamp().setTimeInMillis(ots);
        event_data = "(Shift Stop)";
        
        if (this.ots > s.getStopTimeInMillis(getOriginalTimestamp())) {
            //Time is after shift stop
            if (this.ots < s.getStopTimeIntervalInMillis(getOriginalTimestamp())) {
                //Time is 15 minutes or less after shift stop and gets pushed back
                getAdjustedTimestamp().setTimeInMillis(s.getStopTimeInMillis(getOriginalTimestamp()));
            }
            else {
                //Time is 15 minutes or more after shift stop and gets rounded to nearest interval
                intervalRounding(s);
            }
        }   
        
        else if (this.ots < s.getStopTimeInMillis(getOriginalTimestamp())) {
            //Time is before shift stop
            if (this.ots > s.getStopTimeGraceInMillis(getOriginalTimestamp())) {
                //Time falls within grace period (5 min or less before shift stop) and gets pushed forward
                getAdjustedTimestamp().setTimeInMillis(s.getStopTimeInMillis(getOriginalTimestamp()));
            }
            else if (this.ots > s.getStopTimeDockInMillis(getOriginalTimestamp())) {
                //Time falls outside of grace period but within 15 minutes before shift stop and gets pushed back
                getAdjustedTimestamp().setTimeInMillis(s.getStopTimeDockInMillis(getOriginalTimestamp()));
                event_data = "(Shift Stop(Dock)";
            }
            else {
                //Time is more than 15 minutes before end of shift and gets rounded to nearest interval
                intervalRounding(s);
            }
        }
    }   
           
    public void adjustLunchStart(Shift s) {
        unrounded_min = getOriginalTimestamp().get(Calendar.MINUTE);
        mod = unrounded_min % s.getInterval();
        getAdjustedTimestamp().setTimeInMillis(ots);
        event_data = "(Lunch Start)";
        
        if (this.ots < s.getLunchStartInMillis(getOriginalTimestamp())) {
            //Check if they clock out early for lunch 
          getAdjustedTimestamp().setTimeInMillis(s.getLunchStartInMillis(getOriginalTimestamp()));
        }    
        else if (this.ots > s.getLunchStartInMillis(getOriginalTimestamp()) &&
                 this.ots < (s.getLunchStartInMillis(getOriginalTimestamp()) + (s.getInterval() * 60000))) {
            //Check if they clock out late for lunch 
          getAdjustedTimestamp().setTimeInMillis(s.getLunchStartInMillis(getOriginalTimestamp()));
        }
        
        
    }
    
    public void adjustLunchStop(Shift s) {
        unrounded_min = getOriginalTimestamp().get(Calendar.MINUTE);
        mod = unrounded_min % s.getInterval();
        getAdjustedTimestamp().setTimeInMillis(ots);
        event_data = "(Lunch Stop)";
        
        if (this.ots < s.getLunchStopInMillis(getOriginalTimestamp()) && 
            this.ots > s.getLunchStartInMillis(getOriginalTimestamp()) + (s.getInterval() * 60000)) {
           //Check if they clocked back in before their lunch stop
          getAdjustedTimestamp().setTimeInMillis(s.getLunchStopInMillis(getOriginalTimestamp()));
        }
        else if (this.ots > s.getLunchStopInMillis(getOriginalTimestamp())) {
           //Check if they clocked back in after their lunch stop  
          getAdjustedTimestamp().setTimeInMillis(s.getLunchStopInMillis(getOriginalTimestamp()));
        }
    }
    
    
    public String printAdjustedTimestamp() {
        return "#" + badge_id + " " + getEventType(event_type_id) + getDay() +
                (new SimpleDateFormat(" MM/dd/yyyy HH:mm:ss")).format(getAdjustedTimestamp().getTime()) + " " + event_data;
    }
    
    
    
}
