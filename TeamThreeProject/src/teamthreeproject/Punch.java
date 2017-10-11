
package teamthreeproject;
import java.util.*;
/**
 *
 * @author Travis
 */
public class Punch {
    private Date scheduleTime;
    private Date clockTime;
    private Date adjustedTime;
    
    public void setScheduleTime(Date n){
        this.scheduleTime = n;
    }
    public void setClockTime(Date n){
        this.clockTime = n;
        //this.adjustedTime = clockTime.getShift();
    }
    public void setAdjustedTime(Date n){
        this.adjustedTime = n;
    }
    public Date getSchdelueTime(){
        return scheduleTime;
        
    }
    public Date getClockTime(){
        return clockTime;
        
    }
    public Date getAdjustedTime(){
        return adjustedTime;
       
    }
}
