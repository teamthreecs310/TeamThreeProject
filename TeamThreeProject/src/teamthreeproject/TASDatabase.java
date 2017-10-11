
package teamthreeproject;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Travis
 */
public class TASDatabase {
    private Connection conn;
    private Statement state;
    private PreparedStatement prepstate;
    private ResultSet result;

    public TASDatabase(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/tas";
            String username = "";//Kept empty for now because we will be Creating a project user 
            String password = "";//Kept empty for now because we will be Creating a project user                 
            conn = DriverManager.getConnection(url, username, password);
        } catch(Exception e){}
        
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {}
    }
    
    public Shift getShift(int id) {
        
        //Create new shift rules object according to shift ID entered
        Shift shift = new Shift();
        shift.setShiftID(id);
        
        //Retrieve shift rules from database and store info in new object
        
        try {
            prepstate = conn.prepareStatement("SELECT description, start, stop, "
                    + "`interval`, graceperiod, dock, lunchstart, lunchstop, lunchdeduct, "
                    + "maxtime, overtimethreshold FROM shift WHERE id = ?");
            prepstate.setInt(1, id);
            result = prepstate.executeQuery();            
            if (result != null) {
                result.next();
                shift.setShiftDesc(result.getString("description"));
                shift.setFormattedStart(result.getString("start"));
                shift.setFormattedStop(result.getString("stop"));
                shift.setInterval(result.getInt("interval"));
                shift.setGracePeriod(result.getInt("graceperiod"));
                shift.setDock(result.getInt("dock"));
                shift.setFormattedLunchStart(result.getString("lunchstart"));
                shift.setFormattedLunchStop(result.getString("lunchstop"));
                shift.setLunchDeduction(result.getInt("lunchdeduct"));
                shift.setMaxTime(result.getInt("maxtime"));
                shift.setOverTimeThreshold(result.getInt("overtimethreshold"));
            }
            result.close();
            prepstate.close();
  
        } catch (Exception e) {}
        
        //Retrieve times as unix_timestamps for manipulating later
        
        try {
            prepstate = conn.prepareStatement("SELECT unix_timestamp(start), unix_timestamp(stop), "
                    + "unix_timestamp(lunchstart), unix_timestamp(lunchstop) FROM shift WHERE id = ?");
            prepstate.setInt(1, id);
            result = prepstate.executeQuery();            
            if (result != null) {
                result.next();
                shift.setShiftStartTime(result.getLong("unix_timestamp(start)"));
                shift.setShiftStopTime(result.getLong("unix_timestamp(stop)"));
                shift.setLunchStartTime(result.getLong("unix_timestamp(lunchstart)"));
                shift.setLunchStopTime(result.getLong("unix_timestamp(lunchstop)"));
            }
            result.close();
            prepstate.close();
            
        } catch (Exception e) {}
        
        return shift;
    }
    
}
