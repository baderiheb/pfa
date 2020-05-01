package Service;

import connectionDB.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
// classe service formateur

public class ServiceFormateur {
    private Connection cnx ;
    public ServiceFormateur(){
        this.cnx= DataSource.getInstance();
    }
    // affichage avec un condition d'id

    public ResultSet afficherID(String ID ) throws SQLException {
        String numberOnly= ID.replaceAll("[^0-9]", "");
String req ="select * from forma where idf="+numberOnly;
        ResultSet rs = cnx.createStatement().executeQuery(req);
   return rs ;
    }

   // affichage avec un condition de cin
    public ResultSet afficherCin (String cin) throws SQLException {
 String req= "select * from forma where cin ="+cin ;
 ResultSet rs=cnx.createStatement().executeQuery(req);
 return rs ;
    }

}
