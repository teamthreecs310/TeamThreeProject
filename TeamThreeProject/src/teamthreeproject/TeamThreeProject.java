
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
        
        Punch p1 = db.getPunch(42);
        
        System.out.println(p1.printOriginalTimeStamp());
        
        db.closeConnection();
    
    }
}
