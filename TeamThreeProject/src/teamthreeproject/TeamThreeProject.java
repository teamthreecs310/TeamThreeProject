
package teamthreeproject;

import java.text.SimpleDateFormat;

/**
 *
 * @author TeamThree
 */
public class TeamThreeProject {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TASDatabase db = new TASDatabase();
        
        Punch p = db.getPunch(3634);
        
        String json = db.getPunchListAsJSON(p);
        
        System.out.print(json);
        
        db.closeConnection();
    
    }
}
