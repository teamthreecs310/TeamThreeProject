
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
            String password = "";//Kept empty for now because we will be Creating a project user                 
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
    
    public int insertPunch(Punch p) {
        int punch_id = 0;
        
        //TODO: Insert new punch in database and retrieve generated ID
        
        return punch_id;
    }
    
}
