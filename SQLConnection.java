import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    Connection conn=null;
    public static Connection DbConnector() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abcd1234");
            return conn;
        } 
        catch (ClassNotFoundException | SQLException e) {  
            System.out.println("No CONNECT");
        }
        return null;
    }
} 