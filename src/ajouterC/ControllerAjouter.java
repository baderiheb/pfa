package ajouterC;

import com.jfoenix.controls.JFXButton;
import connectionDB.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerAjouter implements Initializable {
    @FXML
    private AnchorPane prec;
    @FXML
    private JFXButton retourner;
    @FXML
    private TextField name;
    @FXML
    private TextField numt;

    @FXML
    private TextField pname;

    @FXML
    private TextField add;

    @FXML
    private TextField cartecin;

    @FXML
    private DatePicker date;
    @FXML
    private Text nomError;

    @FXML
    private JFXButton btn ;
    @FXML
    private Text prenomError;
    @FXML
    private Text cinError;
    @FXML
    private Text numTelError ;
    @FXML
    private Text exist;
    @FXML
    private TableView<Client> table;
    @FXML
    private TableColumn<Client, Integer>ID;
    @FXML
    private  TableColumn<Client, String>nom;
    @FXML
    private  TableColumn<Client,String> prenom;
    @FXML
    private  TableColumn<Client,String> adresse;
    @FXML
    private  TableColumn<Client,Integer> cin;
    @FXML
    private  TableColumn<Client,Integer> Numtel;


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



 /*La methode ajouter qui nous permet d'ajouter un formateur au niveau de notre base de donnée */
 @FXML
 Connection cnx=DataSource.getInstance();
    public void ajouter() throws SQLException{
     Client c =new Client() ;
if(name.getText().matches("[a-zA-Z]+") && name.getText().length()<=30 )
     {
     c.setNomC(name.getText());}
     else{
     nomError.setText("* le nom doit etre composé par des lettres");
     return ;}
     if(pname.getText().matches("[a-zA-Z]+") && name.getText().length()<=30 ){
     c.setPrenomC(pname.getText());}else{
         prenomError.setText("*le prenom doit etre composée que par des lettres ");
     }

     c.setAdresseC(add.getText());
     if(cartecin.getText().matches("[0-9]+")&& cartecin.getText().length()==8){
     c.setCinC(Integer.parseInt(cartecin.getText()));}else{
         cinError.setText("*Verifier le numero de votre cin");
         return;
     }
     if(numt.getText().matches("[0-9]+")&& numt.getText().length()==8) {

         c.setNumtelC(Integer.parseInt(numt.getText()));
     }else{
         numTelError.setText("*Verifier le numero de votre Telephone");
         return;

     }

     String req1="select * from forma where cin="+c.getCinC();
     ResultSet rs = cnx.createStatement().executeQuery(req1);
     int i=0;
     while(rs.next()){
         i++;
     }
     if (i>0){
         exist.setText("*Formateur déja existant ");
         clearField();
     return;
     }
        ClientService s = new ClientService();
     s.ajouterClient(c);
afficher();
clearField();
     TrayNotification tray = new TrayNotification( );
     AnimationType type = AnimationType.SLIDE ;
     tray.setAnimationType(type);
     tray.setTitle("Etat");
     tray.setMessage("Formateur ajouter avec Succès ");
     tray.setNotificationType(NotificationType.SUCCESS);
     tray.showAndDismiss(Duration.millis(3000));




 }
 /*methode de retourner a la fenetre précedente */
 public void precedent() throws IOException {
     Parent home2 = FXMLLoader.load(getClass().getResource("../Home/home2.fxml"));
     prec.getChildren().setAll(home2);


 }
 /*methode nous permet de vider les case */
    public void clearField (){
        name.clear();
        pname.clear();
        add.clear();
        numt.clear();
        cartecin.clear();
        numt.clear();
        nomError.setText("");
        prenomError.setText("");
        cinError.setText("");
        nomError.setText("");
    }










    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficher();

    }
}