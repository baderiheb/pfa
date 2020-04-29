package rechercherC;
import  connectionDB.Client;
import com.jfoenix.controls.JFXButton;
import connectionDB.Client;
import connectionDB.DataSource;
import connectionDB.Formateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private TableView<Client> table;
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

public ObservableList<Client> Obs;
    public void rechercher() throws SQLException{
        ObservableList<Client> data = FXCollections.observableArrayList();
    String req1="";

    if(choix.getValue()==null){
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Erreur");
        tray.setMessage(" Vueillez choisir la méthode de recherche ");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));
        return;
    }
    if (element.getText().equalsIgnoreCase("")){
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Erreur");
        tray.setMessage(" Vueillez introduire l'element de recherche ");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));
        return;

    }
if(choix.getValue().equals("Nom") || choix.getValue().equals("Prenom") ) {
     req1 = "select * from client  where " + choix.getValue() + "='" + element.getText() + "'";

} else if (choix.getValue().equals("Carte d'identité")){
    req1 = "select * from client  where cin = " + element.getText() ;
}else if (choix.getValue().equals("Numero de téléphone")){
    req1 = "select * from client  where numtel = " + element.getText() ;

}
else if (choix.getValue().equals("ID")){
    req1 = "select * from client  where icl = " + element.getText() ;

}
ResultSet rs = cnx.createStatement().executeQuery(req1);
    int rowCount = 0;

    while(rs.next())
    {
        data.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
rowCount++;
    }
    if(rowCount == 0){
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Pas de client trouver  ");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));
    }


    ID.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("idC"));
    nom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("nomC"));
    prenom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("prenomC"));
    adresse.setCellValueFactory(new PropertyValueFactory<Formateur,String>("adresseC"));
    cin.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("cinC"));
    Numtel.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("numtelC"));

    table.setItems(data);
    element.clear();

}
    /*methode de retourner a la fenetre précedente */
    public void precedent() throws IOException {
        Parent home3 = FXMLLoader.load(getClass().getResource("../Home/home3.fxml"));
        prec.getChildren().setAll(home3);


    }
    public void afficher(){

        try{
            Obs=FXCollections.observableArrayList();
            Connection con = DataSource.getInstance();
            String req1="select * from client";
            ResultSet rs=con.createStatement().executeQuery(req1);
            while(rs.next()){

                Obs.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ID.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("idC"));
        nom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("nomC"));
        prenom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("prenomC"));
        adresse.setCellValueFactory(new PropertyValueFactory<Formateur,String>("adresseC"));
        cin.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("cinC"));
        Numtel.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("numtelC"));


        table.setItems(Obs);



    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadList();
        afficher();
    }

}