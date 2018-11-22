package chatapplicationjava;

/**
 *
 * @author Group-11
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnect {
    public static Connection getConnection(){
      Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection("jdbc:mysql://localhost/chatdb","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Your Computer is Not Connected To The Server Please Try Again");
        }
      
      return con;
    }
    
 }
