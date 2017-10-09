/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamthreeproject;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Travis
 */
public class TASConnection {
    private Connection conn;
    private Statement state;
    private ResultSet result;

    public TASConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/tas";
            String username = " ";//Kept empty for now because we will be Creating a project user 
            String password = " ";//Kept empty for now because we will be Creating a project user                 
            conn = DriverManager.getConnection(url, username,
                        password);
            state = conn.createStatement();
            result = state.executeQuery("SELECT * FROM badge WHERE id = '3282F212'");
            if(result != null){
                result.next();
                String id = result.getString("id");
                String desc = result.getString("description");
                System.out.println(id + " " + desc);
            }
            result.close();
            state.close();
            conn.close();
        }catch(Exception e){System.out.println(e.toString());}
        
    }
    
}