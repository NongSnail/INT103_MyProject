
package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    static private String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static private String URI = "jdbc:derby://localhost:1527/wishlist";
    static private String USERNAME = "pooripro";
    static private String PASSWORD = "Puwa@99236487";
    
    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URI, USERNAME, PASSWORD);
        }catch(ClassNotFoundException ex){
            System.err.println("Can't connect driver");
        }
        catch(SQLException ex){
            System.err.println("Can't connect Database");
        }
        return conn;
    }
}
