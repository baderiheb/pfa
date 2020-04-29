package connectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class FormateurService {
	private Connection connection ; 
	private PreparedStatement ps;
public FormateurService () { 
	connection = DataSource.getInstance();
}


public void ajouterFormateur( Formateur f) {
	PreparedStatement ps = null ;
	String req = "INSERT INTO forma (IDF ,nom,prenom,adresse,cin,numtel) " + "VALUES (IDF.nextval,?,?,?,?,?)";
	try { 

        ps = connection.prepareStatement(req);

        ps.setString(1,f.getNom());
        ps.setString(2, f.getPrenomF());
        ps.setString(3, f.getAdresse());
        ps.setInt(4, f.getCin());
        ps.setInt(5, f.getNumTel());
        ps.executeUpdate();
	}catch (SQLException ex) {
        ex.printStackTrace();
    }
}
public void consulterFormateur() { 

	ResultSet rst ; 
	String req ="SELECT * FROM forma"; 
	try{
		Statement ps=null;
	 ps= connection.createStatement() ; 

	rst=ps.executeQuery(req);

	while(rst.next()) {
		System.out.println(rst.getInt("ICL") + "\t" );
		System.out.println(rst.getString("NOM")+ "\t" );
		System.out.println(rst.getString("PRENOM")+ "\t" );
		System.out.println(rst.getString("ADRESSE")+ "\t" );
		System.out.println(rst.getInt("CIN")+ "\t" );
		System.out.println(rst.getInt("NUMTEL")+ "\t" );
		System.out.println(rst.getString("FORMATION")+ "\t" );
		System.out.println(rst.getString("DATEDEBUT")+ "\t" );


	}


	}catch (SQLException ex) {
		ex.printStackTrace(); 
	}
}


}
