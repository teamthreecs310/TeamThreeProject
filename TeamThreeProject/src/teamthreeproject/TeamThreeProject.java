
package teamthreeproject;

/**
 *
 * @author Travis
 */
public class TeamThreeProject {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TASDatabase db = new TASDatabase();
        
        /* for Shift testing
        Shift s1 = db.getShift(1);
        Shift s2 = db.getShift(2);
        Shift s3 = db.getShift(3);
        
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString()); */
        
        db.closeConnection();
    
    }
}
