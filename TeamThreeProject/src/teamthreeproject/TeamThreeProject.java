
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
        
        /* testing for adjust START */
        Shift s1 = db.getShift(2);
        
        Punch p1 = db.getPunch(4943);
        Punch p2 = db.getPunch(5004);
        
        p1.adjust(s1);
        p2.adjust(s1);
        
        System.out.println(p1.printOriginalTimestamp());
        System.out.println(p1.printAdjustedTimestamp());
        System.out.println(p2.printOriginalTimestamp());
        System.out.println(p2.printAdjustedTimestamp());
        
        Punch p1_new = db.getPunch(4943);
        Punch p2_new = db.getPunch(5004);
        
        System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(p1_new.getAdjustedTimestamp().getTime()));
        
        /* testing for adjust END */
        
        db.closeConnection();
    
    }
}
