package afficher;

import com.jfoenix.controls.JFXButton;
import connectionDB.DataSource;
import connectionDB.Formateur;
import connectionDB.FormateurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane showf;
    @FXML
    private JFXButton retourner;
    @FXML
    private TextField name ;
    @FXML
    private JFXButton btn ;
    @FXML
    private TableView<Formateur> table;
    @FXML
    private TableColumn<Formateur, Integer>ID;
    @FXML
    private  TableColumn<Formateur, String> nom;
    @FXML
    private  TableColumn<Formateur,String> prenom;
    @FXML
    private  TableColumn<Formateur,String> adresse;
    @FXML
    private  TableColumn<Formateur,Integer> cin;
    @FXML
    private  TableColumn<Formateur,Integer> Numtel;
    @FXML
    private JFXButton test ;
    public ObservableList<Formateur> Obs ;

    public void precedent() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("../home/home2.fxml"));
        showf.getChildren().setAll(home2);


    }

    public void afficher(){

       try{
           Obs=FXCollections.observableArrayList();
        Connection con = DataSource.getInstance();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM forma");
        while(rs.next()){
          Obs.add(new Formateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));


        }

       } catch (SQLException e) {
           e.printStackTrace();
       }

        ID.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("prenomF"));
        adresse.setCellValueFactory(new PropertyValueFactory<Formateur,String>("adresse"));
        cin.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("cin"));
        Numtel.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("numTel"));


        table.setItems(Obs);




    }





    public void initialize(URL url, ResourceBundle resourceBundle) {
afficher();
    }
}