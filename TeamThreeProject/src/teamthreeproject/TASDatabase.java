
package teamthreeproject;
import java.sql.*;
import java.util.*;

/**
 *
 * @author TeamThree
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
            String username = "root";//Kept empty for now because we will be Creating a project user 
            String password = "CS310";//Kept empty for now because we will be Creating a project user                 
            conn = DriverManager.getConnection(url, username, password);
        } catch(Exception e){}
        
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {}
    }
    public Punch getPunch(int id){
        Punch punch = null;
        
        try{
            prepstate = conn.prepareStatement("SELECT id, terminalid, badgeid, unix_timestamp(originaltimestamp) AS ots,"
                                            + "eventtypeid FROM event WHERE id = ?");
            prepstate.setInt(1,id);
            result = prepstate.executeQuery();
            if(result != null){
                result.next();
                punch = new Punch(id, result.getInt("terminalid"), result.getString("badgeid"), 
                                  result.getLong("ots"), result.getInt("eventtypeid"));
            }
            result.close();
            prepstate.close();
        } catch(Exception e){}
        
        return punch;
    }
    public int insertPunch(Punch punch) {
        int id = 0;
    try {
           String badgeid = punch.getBadgeID();
           int terminalid = punch.getTerminalID();
           int eventtypeid = punch.getEventTypeID();
           prepstate = conn.prepareStatement("INSERT INTO event(badgeid, terminalid, eventtypeid)"
                   + "VALUES (?, ?, ?)");
           prepstate.setString(1, badgeid);
           prepstate.setInt(2, terminalid);
           prepstate.setInt(3, eventtypeid);
           prepstate.executeUpdate();
       result.close();
       prepstate.close();
       }
       catch(Exception e){}
       try{
           prepstate = conn.prepareStatement("SELECT last_insert_id() FROM event");
           result = prepstate.executeQuery();
           id = result.getInt(1);
           result.close();
           prepstate.close();
       }
       catch(Exception e){}
       //punch.setID(id);
       System.out.println(""+id);
       return id;
    }
    public Shift getShift(int id) {
        
        Shift shift = null;
        
        //Retrieve shift rules from database and store info in new Shift object       
        try {
            prepstate = conn.prepareStatement("SELECT *, unix_timestamp(start) AS shift_start,"
                    + "unix_timestamp(stop) AS shift_stop, unix_timestamp(lunchstart) AS l_start,"
                    + "unix_timestamp(lunchstop) AS l_stop FROM shift WHERE id = ?");
            prepstate.setInt(1, id);
            result = prepstate.executeQuery();            
            if (result != null) {
                result.next();
                shift = new Shift(id, result.getString("description"),
                        result.getLong("shift_start"), result.getLong("shift_stop"),
                        result.getInt("interval"), result.getInt("graceperiod"),
                        result.getInt("dock"), result.getLong("l_start"),
                        result.getLong("l_stop"), result.getInt("lunchdeduct"),
                        result.getInt("maxtime"), result.getInt("overtimethreshold"));
            }
            result.close();
            prepstate.close();
  
        } catch (Exception e) {}
        
        return shift;
    }
    
    public Badge getBadge(String id) {
        
        Badge badge = null;
        
        try {
            prepstate = conn.prepareStatement("SELECT * FROM badge WHERE id = ?");
            prepstate.setString(1, id);
            result = prepstate.executeQuery();            
            if (result != null) {
                result.next();
                badge = new Badge(result.getString("id"), result.getString("description"));               
            }
            result.close();
            prepstate.close();
  
        } catch (Exception e) {}
        
        
        return badge;
    }
        
}
