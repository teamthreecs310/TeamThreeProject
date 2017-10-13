/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teamthreeproject;
import java.util.*;

/**
 *
 * @author Team Three
 */
public class Badge {
    private String id;
    private String description;
    
    public void setID(String s) {
        this.id = s;
    }

    public void setDescription(String d) {
        this.description = d;
    }
    
    public String getID() {
        return this.id;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public String toString() {
        return "# " + getID() + "(" + getDescription() + ")";
    }
}