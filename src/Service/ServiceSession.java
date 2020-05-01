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

// afficher avec condition dID
    public ResultSet afficher(int ID ) throws  SQLException{
             String req="select * from sess where ids="+ID;
             ResultSet rs = cnx.createStatement().executeQuery(req);
             return rs;
    }
// afficher tous les session

    public ResultSet afficher() throws SQLException {
        String req ="select * from sess ";
        ResultSet rs = cnx.createStatement().executeQuery(req);
        return rs ;
    }
    public ResultSet afficherSalle(String numSalle) throws SQLException{
             String req ="select * from sess where numsalle="+numSalle;
             ResultSet rs = cnx.createStatement().executeQuery(req);
             return rs ;
    }
    public void ajouter(String req) throws SQLException {
             cnx.createStatement().executeQuery(req);

    }

    //rechercher les salle dans un intervalle de date
    public ResultSet rechercher(String numsalle ,String d1 , String d2,String hdebut,String hfin) throws SQLException {
            String req=" SELECT * FROM sess WHERE numsalle ="+numsalle+" and datedebut BETWEEN TO_DATE ('"+d1+"', 'yyyy/mm/dd') AND TO_DATE ('"+d2+"', 'yyyy-mm/dd') and hdebut between "+hdebut+" and "+hfin;
ResultSet rs =cnx.createStatement().executeQuery(req);
return rs;
    }
    //rechercher les salle dans un intervalle de tamps
}
