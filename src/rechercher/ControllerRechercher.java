package rechercher;

import com.jfoenix.controls.JFXButton;
import connectionDB.DataSource;
import connectionDB.Formateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRechercher implements Initializable {
    Connection cnx = DataSource.getInstance() ;
    @FXML
    private AnchorPane prec;
    @FXML
    private JFXButton retourner;
    @FXML
    private JFXButton rech;
    @FXML
    private TextField element;
    @FXML
    private TableView<Formateur> table;
    @FXML
    private TableColumn<Formateur, Integer> ID;
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
    private ChoiceBox<String> choix;
    ObservableList list = FXCollections.observableArrayList();
    public void loadList(){
        list.removeAll(list);
        list.addAll("ID","Nom", "Prenom","Carte d'identité","Numero de téléphone");
        choix.getItems().addAll(list);

    }
public void rechercher() throws SQLException{
        ObservableList<Formateur> Obs = FXCollections.observableArrayList();;

    String req1="";
    if(choix.getValue()==null){
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Erreur");
        tray.setMessage(" Vueillez choisir la méthode de recherche ");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));
    }
if(choix.getValue().equals("Nom") || choix.getValue().equals("Prenom") ) {
     req1 = "select * from forma  where " + choix.getValue() + "='" + element.getText() + "'";

} else if (choix.getValue().equals("Carte d'identité")){
    req1 = "select * from forma  where cin = " + element.getText() ;
}else if (choix.getValue().equals("Numero de téléphone")){
    req1 = "select * from forma  where numtel = " + element.getText() ;

}
else if (choix.getValue().equals("ID")){
    req1 = "select * from forma  where idf = " + element.getText() ;

}
ResultSet rs = cnx.createStatement().executeQuery(req1);
    int rowCount = 0;

    while(rs.next())
    {
        Obs.add(new Formateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
rowCount++;
    }
    System.out.println(rowCount);
    if(rowCount == 0){
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Pas de formateur trouver  ");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));
    }


    ID.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("id"));
    nom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("prenomF"));
    adresse.setCellValueFactory(new PropertyValueFactory<Formateur,String>("adresse"));
    cin.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("cin"));
    Numtel.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("numTel"));


    table.setItems(Obs);



}
    /*methode de retourner a la fenetre précedente */
    public void precedent() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("../Home/home2.fxml"));
        prec.getChildren().setAll(home2);


    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadList();
    }

}