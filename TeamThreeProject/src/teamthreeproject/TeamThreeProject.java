
package teamthreeproject;

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
        Shift s1 = db.getShift(1);
        Punch p1 = db.getPunch(3634);
        db.insertPunch(p1);
        
        p1.adjust(s1);
        
        System.out.println(p1.printOriginalTimestamp());
        System.out.println(p1.printAdjustedTimestamp());
        /* testing for adjust END */
        
        db.closeConnection();
    
    }
}
