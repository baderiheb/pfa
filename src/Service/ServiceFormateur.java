package Service;

import connectionDB.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceFormateur {
    private Connection cnx ;
    public ServiceFormateur(){
        this.cnx= DataSource.getInstance();
    }
    // affichage avec un condition d'id
    public ResultSet afficherID(String ID ) throws SQLException {
String req ="select * from forma where idf="+ID;
        ResultSet rs = cnx.createStatement().executeQuery(req);
   return rs ;
    }

}
