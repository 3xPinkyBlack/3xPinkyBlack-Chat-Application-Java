package chatapplicationjava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class TestDatabase {
    public static void main(String args[]) {
        String query = "SELECT * FROM userdb";
        Connection conn = DBConnect.getConnection();
        char chAge;
        
        for(int i=0; i<121; i++) {
            chAge = (char)i;
            System.out.println(i+": "+chAge);
        }
        
//        try {
//            Statement stmt = conn.createStatement();
//             ResultSet rs;
//            rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                String FullName = rs.getString("FName") + " " + rs.getString("MName") + " " + rs.getString("LName");
//                JOptionPane.showMessageDialog(null,FullName);            
//            }
//            
//            
//        } catch (SQLException e ) {
//            JOptionPane.showMessageDialog(null,e.getMessage());
//        }

    }
}
