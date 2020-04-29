package supprimer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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

import javax.swing.text.html.ImageView;
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
    @FXML
    private JFXTextArea text;


    ObservableList list = FXCollections.observableArrayList();
    public void loadList(){
        list.removeAll(list);
        list.addAll("ID","Nom", "Prenom","Carte d'identité","Numero de téléphone");
        choix.getItems().addAll(list);

    }
    public ObservableList<Formateur> data ;
    public void afficher(){

        try{
              data=FXCollections.observableArrayList();
            Connection con = DataSource.getInstance();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM forma");
            while(rs.next()){
                data.add(new Formateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));


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


        table.setItems(data);


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
element.clear();


}
    /*methode de retourner a la fenetre précedente */
    public void precedent() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("../Home/home2.fxml"));
       delete.getChildren().setAll(home2);


    }
    public void supprimer() throws SQLException{
        Connection cnx = DataSource.getInstance();
        String req1= "delete from forma where cin="+table.getSelectionModel().getSelectedItem().getCin();
        cnx.createStatement().executeUpdate(req1);
        text.setText("Le formateur d'ID = "+table.getSelectionModel().getSelectedItem().getId()+" a été supprimer");
        afficher();


    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficher();
        loadList();
    }


}