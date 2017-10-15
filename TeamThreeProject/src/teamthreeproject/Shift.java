
package teamthreeproject;

/**
 * Shift class for TASDatabase.  Contains necessary shift information/rules
 * and methods for returning them in proper format.
 * 
 * @author TeamThree
 */

public class Shift {
    private final int shift_id;
    private final String desc;
    private final String start_time;
    private final int start_time_hour;
    private final int start_time_minute;
    private final String stop_time;
    private final int stop_time_hour;
    private final int stop_time_minute;
    private int min_diff;
    private int shift_length;
    private final int interval;
    private final int grace_period;
    private final int dock;
    private final String lunch_start;
    private final int lunch_start_hour;
    private final int lunch_start_minute;
    private final String lunch_stop;
    private final int lunch_stop_hour;
    private final int lunch_stop_minute;
    private int l_min_diff;
    private int lunch_length;
    private final int lunch_deduct;
    private final int max_time;
    private final int overtime_threshold;
    
    //Constructor
    public Shift(int id, String description, String start, String stop,
                 int interv, int grace_p, int dock, String l_start,
                 String l_stop, int l_deduct, int max, int overtime) {
        
        this.shift_id = id;
        this.desc = description;
        this.start_time = start;
        this.stop_time = stop;
        this.start_time_hour = Integer.parseInt(start_time.substring(0, 2));
        this.start_time_minute = Integer.parseInt(start_time.substring(3, 5));
        this.stop_time_hour = Integer.parseInt(stop_time.substring(0, 2));
        this.stop_time_minute = Integer.parseInt(stop_time.substring(3, 5));
        this.interval = interv;
        this.grace_period = grace_p;
        this.dock = dock;
        this.lunch_start = l_start;
        this.lunch_stop = l_stop;
        this.lunch_start_hour = Integer.parseInt(lunch_start.substring(0, 2));
        this.lunch_start_minute = Integer.parseInt(lunch_start.substring(3, 5));
        this.lunch_stop_hour = Integer.parseInt(lunch_stop.substring(0, 2));
        this.lunch_stop_minute = Integer.parseInt(lunch_stop.substring(3, 5));
        this.lunch_deduct = l_deduct;
        this.max_time = max;
        this.overtime_threshold = overtime;
        
    }
    
    //Returns entire shift description in proper format string
    @Override
    public String toString() {
        
        return desc + ": " + start_time.substring(0, 5) + " - " + stop_time.substring(0, 5) + " ("
                + "510 minutes); Lunch: " + lunch_start.substring(0, 5) + " - "
                + lunch_stop.substring(0, 5) + " (" + "30 minutes)";
        
    }
}
