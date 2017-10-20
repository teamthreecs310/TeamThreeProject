
package teamthreeproject;
import java.sql.*;
import java.util.*;
import java.text.*;

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
            String password = "norris699601";//Kept empty for now because we will be Creating a project user                 
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
        int punchid = 0;
        int result = 0;
        ResultSet keys;
    try {
           String badgeid = punch.getBadgeID();
           int terminalid = punch.getTerminalID();
           int eventtypeid = punch.getEventTypeID();
           String sql = "INSERT INTO event(badgeid, originaltimestamp, terminalid, eventtypeid) VALUES (?, ?, ?, ?)";
           prepstate = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
           prepstate.setString(1, badgeid);
           prepstate.setString(2, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(punch.getOriginalTimestamp().getTime()));
           prepstate.setInt(3, terminalid);
           prepstate.setInt(4, eventtypeid);
           result = prepstate.executeUpdate();
           if(result == 1){
               keys = prepstate.getGeneratedKeys();
               if(keys.next()){
                   punchid = keys.getInt(1);
               }
           }
           prepstate.close();
       }
       catch(Exception e){}

       //punch.setID(id);
       System.out.println(""+punchid);
       return punchid;
    }
    public Shift getShift(int id) {
        ResultSet result;
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
        ResultSet result;
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
