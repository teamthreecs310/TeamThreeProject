
package teamthreeproject;

/**
 *
 * @author TeamThree
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
        return "#" + getID() + " (" + getDescription() + ")";
    }
}