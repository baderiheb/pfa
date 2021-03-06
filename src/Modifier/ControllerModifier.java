package Modifier;

import com.jfoenix.controls.JFXButton;
import connectionDB.DataSource;
import connectionDB.Formateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerModifier implements Initializable {
    @FXML
    private AnchorPane search;
    @FXML
    private TextField element;

    @FXML
    private TextField numt;

    @FXML
    private TextField pname;

    @FXML
    private TextField add;

    @FXML
    private TextField cartecin;



    @FXML
    private JFXButton btn;

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<String> choix;

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

    private ImageView rechercher;
    public void getSelected (){
        name.setText(table.getSelectionModel().getSelectedItem().getNom());
        pname.setText(table.getSelectionModel().getSelectedItem().getPrenomF());
        add.setText(table.getSelectionModel().getSelectedItem().getAdresse());
        cartecin.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getCin()));
        numt.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getNumTel()));
    }
    public ObservableList<Formateur> Obs ;
    Connection con = DataSource.getInstance();

    public void afficher(){
        try{
            Obs=FXCollections.observableArrayList();
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
    ResultSet rs = con.createStatement().executeQuery(req1);
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
    prenom.setCellValueFactory(new PropertyValueFactory<Formateur,String>("prenom"));
    adresse.setCellValueFactory(new PropertyValueFactory<Formateur,String>("adresse"));
    cin.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("cin"));
    Numtel.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("numTel"));

    table.setItems(Obs);



}
    /*methode nous permet de vider les case */
    public void clearField (){
        name.clear();
        pname.clear();
        add.clear();
        numt.clear();
        cartecin.clear();
        numt.clear();
    }
    /*methode de retourner a la fenetre précedente */
    public void precedent() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("../Home/home2.fxml"));
        search.getChildren().setAll(home2);


    }
/*methode de modifier*/
    public void modifier()throws SQLException {
        String req1="update forma set nom = '"+name.getText()+"' ,prenom='"+pname .getText()+"', adresse= '"+add.getText()+"',NumTel="+numt.getText()+" where cin="+table.getSelectionModel().getSelectedItem().getCin();
       System.out.println(req1);
        con.createStatement().executeUpdate(req1);
        System.out.println("sucess");
        clearField();

        afficher();
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Formateur modifié ! ");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }


    ObservableList list = FXCollections.observableArrayList();
    public void loadList(){
        list.removeAll(list);
        list.addAll("ID","Nom", "Prenom","Carte d'identité","Numero de téléphone");
        choix.getItems().addAll(list);

    }









    public void initialize(URL url, ResourceBundle resourceBundle) {
loadList();
afficher();

    }


    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }



}