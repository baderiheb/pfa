
package connectionDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;






public class DataSource
{ 
 private static String url = "jdbc:Oracle:thin:@DESKTOP-3EEB7EI:1521:xe";
 private static String login = "system";
 private static String pwd = "bader";
 static Connection instanceConnexion;



	   
public DataSource(){
	   try {
         instanceConnexion = DriverManager.
                 getConnection(url, login, pwd);
         System.out.println("connexion est etablie");
     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
    
 }    
  
    
	public static Connection getInstance()
    {
        if(instanceConnexion == null)
           new DataSource();
        return 
                instanceConnexion;
	}
}

    

