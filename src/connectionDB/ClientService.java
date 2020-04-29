package connectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientService {
    private Connection connection ;
    private PreparedStatement ps;
    public ClientService () {
        connection = DataSource.getInstance();
    }


    public void ajouterClient( Client f) {
        PreparedStatement ps = null ;
        String req = "INSERT INTO client (idcl ,nom,prenom,adresse,cin,numtel)" + "VALUES (idcl.nextval,?,?,?,?,?)";
        try {

            ps = connection.prepareStatement(req);

            ps.setString(1,f.getNomC());
            ps.setString(2, f.getPrenomC());
            ps.setString(3, f.getAdresseC());
            ps.setInt(4, f.getCinC());
            ps.setInt(5, f.getNumtelC());
            ps.executeQuery();

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
