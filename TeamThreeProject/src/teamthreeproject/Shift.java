
package teamthreeproject;
import java.util.*;

/**
 * Shift class for TASDatabase.  Contains necessary shift information/rules
 * and methods for returning them in proper format.
 * 
 * @author Christopher Owen
 */

public class Shift {
    private int shift_id;
    private String desc;
    private long start_time;
    private long stop_time;
    private String formatted_start;
    private String formatted_stop;
    private long shift_length;
    private int interval;
    private int grace_period;
    private int dock;
    private long lunch_start;
    private long lunch_stop;
    private String formatted_lunch_start;
    private String formatted_lunch_stop;
    private long lunch_length;
    private int lunch_deduct;
    private int max_time;
    private int overtime_threshold;
    
    //Getters and setters
    
    public int getShiftID() {
        return shift_id;
    }
    
    public void setShiftID(int shift_id) {
        this.shift_id = shift_id;
    }
    
    public String getShiftDesc() {
        return desc;
    }
    
    public void setShiftDesc(String desc) {
        this.desc = desc;
    }
    
    public long getShiftStartTime() {
        return start_time;
    }
    
    public void setShiftStartTime(long start_time) {
        this.start_time = start_time;
    }
    
    public long getShiftStopTime() {
        return stop_time;
    }
    
    public void setShiftStopTime(long stop_time) {
        this.stop_time = stop_time;
    }
    
    public int getInterval() {
        return interval;
    }
    
    public void setInterval(int interval) {
        this.interval = interval;
    }
    
    public int getGracePeriod() {
        return grace_period;
    }
    
    public void setGracePeriod(int grace_period) {
        this.grace_period = grace_period;
    }
    
    public int getDock() {
        return dock;
    }
    
    public void setDock(int dock) {
        this.dock = dock;
    }
    
    public long getLunchStartTime() {
        return lunch_start;
    }
    
    public void setLunchStartTime(long lunch_start) {
        this.lunch_start = lunch_start;
    }
    
    public long getLunchStopTime() {
        return lunch_stop;
    }
    
    public void setLunchStopTime(long lunch_stop) {
        this.lunch_stop = lunch_stop;
    }
    
    public int getLunchDeduction() {
        return lunch_deduct;
    }
    
    public void setLunchDeduction(int lunch_deduct) {
        this.lunch_deduct = lunch_deduct;
    }
    
    public int getMaxTime() {
        return max_time;
    }
    
    public void setMaxTime(int max_time) {
        this.max_time = max_time;
    }
    
    public int getOvertimeThreshold() {
        return overtime_threshold;
    }
    
    public void setOverTimeThreshold(int overtime_threshold) {
        this.overtime_threshold = overtime_threshold;
    }
    
    public long getShiftLength(long start_time, long stop_time) {
        shift_length = ((stop_time - start_time) / 60);
        return shift_length;
    }
    
    public long getLunchLength(long lunch_start, long lunch_stop) {
        lunch_length = ((lunch_stop - lunch_start) / 60);
        return lunch_length;
    }
    
    /*  These methods take the unix_timestamp format and convert them to HH:MM
        format using GregorianCalendar. */
    
    public void setFormattedStart(String f_start) {
        
        /*  for converting later with GregorianCalendar
        Calendar start = new GregorianCalendar();
        start.setTimeInMillis(start_time);
        System.out.println("Start time: " + start);
        
        formatted_start = start.get(Calendar.HOUR) + ":" + start.get(Calendar.MINUTE);  */
        
        this.formatted_start  = f_start.substring(0,5);
    }
    
    public String getFormattedStart() {
        
        return formatted_start;
    }
    
    public void setFormattedStop(String f_stop) {
        
        /*  for converting later with GregorianCalendar
        Calendar stop = new GregorianCalendar();
        stop.setTimeInMillis(stop_time);
        System.out.println("Stop time: " + stop);
        
        formatted_stop = stop.get(Calendar.HOUR) + ":" + stop.get(Calendar.MINUTE);  */
        
        this.formatted_stop  = f_stop.substring(0,5);
    }
    
    public String getFormattedStop() {
        
        return formatted_stop;
    }
    
    public void setFormattedLunchStart(String f_lunchstart) {
        
        /*  for converting later with GregorianCalendar
        Calendar l_start = new GregorianCalendar();
        l_start.setTimeInMillis(stop_time);
        System.out.println("Stop time: " + l_start);
        
        formatted_lunch_start = l_start.get(Calendar.HOUR) + ":" + l_start.get(Calendar.MINUTE);  */
        
        this.formatted_lunch_start = f_lunchstart.substring(0,5);
    }
    
    public String getFormattedLunchStart() {
        
        return formatted_lunch_start;
    }
    
    public void setFormattedLunchStop(String f_lunchstop) {
        
        /*  for converting later with GregorianCalendar
        Calendar l_stop = new GregorianCalendar();
        l_stop.setTimeInMillis(stop_time);
        System.out.println("Stop time: " + l_stop);
        
        formatted_lunch_stop = l_stop.get(Calendar.HOUR) + ":" + l_stop.get(Calendar.MINUTE);  */
        
        this.formatted_lunch_stop = f_lunchstop.substring(0,5);
    }
    
    public String getFormattedLunchStop() {
        
        return formatted_lunch_stop;
    }
 
    
    /* This method converts shift rules to proper string format */
    
    @Override
    public String toString() {
        
        return getShiftDesc() + ": " + getFormattedStart() + " - "
                + getFormattedStop() + " (" + getShiftLength(start_time, stop_time)
                + " minutes); Lunch: " + getFormattedLunchStart() + " - "
                + getFormattedLunchStop() + " (" + getLunchLength(lunch_start, lunch_stop)
                + " minutes)";
        
    }
}
