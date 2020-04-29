package Service;

import connectionDB.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSession {
private Connection cnx ;
//Constructeur
         public ServiceSession(){
    this.cnx= DataSource.getInstance();
}


// afficher tous les session

    public ResultSet afficher() throws SQLException {
        String req ="select * from sess ";
        ResultSet rs = cnx.createStatement().executeQuery(req);
        return rs ;
    }
}
