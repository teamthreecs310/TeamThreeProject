
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
            String password = "Tnaecx69!";//Kept empty for now because we will be Creating a project user                 
            conn = DriverManager.getConnection(url, username, password);
        } catch(Exception e){}
        
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {}
    }
    public Punch getPunch(int id){
        Punch punch = new Punch();
        punch.setID(id);
        try{
            prepstate = conn.prepareStatement("SELECT terminalid, badgeid, "
                                               + "originaltimestamp, eventtypeid, "
                                               + "eventdata, adjusttimestamp "
                                               + "FROM punch WHERE id = ?");
            prepstate.setInt(1,id);
            result = prepstate.executeQuery();
            if(result != null){
                result.next();
                punch.setTerminalID(result.getString("terminalid"));
                punch.setBadgeID(result.getString("badgeid"));
                punch.setOriginalTimeStamp(result.getString("originaltimestamp"));
                punch.setEventTypeID(result.getString("eventtypeid"));
                punch.setEventData(result.getString("eventdata"));
                punch.setAdjustedTimeStamp(result.getString("adjustedtimestamp"));
            }
            result.close();
            prepstate.close();
        }catch(Exception e){}
        return punch;
    }
    public Shift getShift(int id) {
        
        Shift shift = null;
        
        //Retrieve shift rules from database and store info in new Shift object       
        try {
            prepstate = conn.prepareStatement("SELECT * FROM shift WHERE id = ?");
            prepstate.setInt(1, id);
            result = prepstate.executeQuery();            
            if (result != null) {
                result.next();
                shift = new Shift(result.getInt("id"), result.getString("description"),
                        result.getString("start"), result.getString("stop"),
                        result.getInt("interval"), result.getInt("graceperiod"),
                        result.getInt("dock"), result.getString("lunchstart"),
                        result.getString("lunchstop"), result.getInt("lunchdeduct"),
                        result.getInt("maxtime"), result.getInt("overtimethreshold"));
            }
            result.close();
            prepstate.close();
  
        } catch (Exception e) {}
        
        return shift;
    }
    
    public Badge getBadge(String id) {
        
        Badge badge = new Badge();
        badge.setID(id);
        
        try {
            prepstate = conn.prepareStatement("SELECT * FROM badge WHERE id = ?");
            prepstate.setString(1, id);
            result = prepstate.executeQuery();            
            if (result != null) {
                result.next();
                badge.setDescription(result.getString("description"));
                
            }
            result.close();
            prepstate.close();
  
        } catch (Exception e) {}
        
        
        return badge;
    }
    
}
