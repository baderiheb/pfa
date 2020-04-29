package supprimerC;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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

public class ControllerSupprimer implements Initializable {
    Connection cnx = DataSource.getInstance() ;

    @FXML
    private JFXButton supprimer;
    @FXML
    private AnchorPane delete ;
    @FXML
    private TextField element;
    @FXML
    private TableView<Client> table;
    @FXML
    private TableColumn<Client, Integer> ID;
    @FXML
    private  TableColumn<Client, String> nom;
    @FXML
    private  TableColumn<Client,String> prenom;
    @FXML
    private  TableColumn<Client,String> adresse;
    @FXML
    private  TableColumn<Client,Integer> cin;
    @FXML
    private  TableColumn<Client,Integer> Numtel;

    @FXML
    private ChoiceBox<String> choix;
    @FXML
    private JFXTextArea text;


    ObservableList list = FXCollections.observableArrayList();
    public void loadList(){
        list.removeAll(list);
        list.addAll("ID","Nom", "Prenom","Carte d'identité","Numero de téléphone");
        choix.getItems().addAll(list);

    }
    public ObservableList<Client> Obs ;
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

            ID.setCellValueFactory(new PropertyValueFactory<Client,Integer>("idC"));
            nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nomC"));
            prenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenomC"));
            adresse.setCellValueFactory(new PropertyValueFactory<Client,String>("adresseC"));
            cin.setCellValueFactory(new PropertyValueFactory<Client,Integer>("cinC"));
            Numtel.setCellValueFactory(new PropertyValueFactory<Client,Integer>("numtelC"));


            table.setItems(Obs);



        }
public void rechercher() throws SQLException{
        ObservableList<Client> Obs = FXCollections.observableArrayList();;

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
     req1 = "select * from client  where " + choix.getValue() + "='" + element.getText() + "'";

} else if (choix.getValue().equals("Carte d'identité")){
    req1 = "select * from client  where cin = " + element.getText() ;
}else if (choix.getValue().equals("Numero de téléphone")){
    req1 = "select * from client  where numtel = " + element.getText() ;

}
else if (choix.getValue().equals("ID")){
    req1 = "select * from client  where idcl = " + element.getText() ;

}
ResultSet rs = cnx.createStatement().executeQuery(req1);
    int rowCount = 0;

    while(rs.next())
    {
        Obs.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
rowCount++;
    }
    System.out.println(rowCount);
    if(rowCount == 0){
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Pas de Client trouver  ");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));
    }


    ID.setCellValueFactory(new PropertyValueFactory<Client,Integer>("idC"));
    nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nomC"));
    prenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenomC"));
    adresse.setCellValueFactory(new PropertyValueFactory<Client,String>("adresseC"));
    cin.setCellValueFactory(new PropertyValueFactory<Client,Integer>("cinC"));
    Numtel.setCellValueFactory(new PropertyValueFactory<Client,Integer>("numtelC"));


    table.setItems(Obs);
element.clear();


}
    /*methode de retourner a la fenetre précedente */
    public void precedent() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("../Home/home3.fxml"));
       delete.getChildren().setAll(home2);


    }
    public void supprimer() throws SQLException{
        Connection cnx = DataSource.getInstance();
        String req1= "delete from client where cin="+table.getSelectionModel().getSelectedItem().getCinC();
        cnx.createStatement().executeUpdate(req1);
        text.setText("Le Client d'ID = "+table.getSelectionModel().getSelectedItem().getIdC()+" a été supprimer");
        afficher();


    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficher();
        loadList();
    }


}