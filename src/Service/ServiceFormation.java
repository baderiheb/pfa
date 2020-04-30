package Service;

import connectionDB.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceFormation {
    private Connection cnx ;
    public  ServiceFormation(){
        cnx= DataSource.getInstance();
    }
    //afficher conditionné id
    public ResultSet afficherID(String ID) throws SQLException {
        String req = "select * from formation where idfor="+ID;
        ResultSet rs =cnx.createStatement().executeQuery(req);
        return rs ;
    }
}
