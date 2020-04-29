package gestionFormation;

import com.jfoenix.controls.JFXButton;
import connectionDB.DataSource;
import connectionDB.Formateur;
import connectionDB.FormateurService;
import connectionDB.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
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

public class ControllerAjouter implements Initializable {
   @FXML
   private TextField nom ;
    @FXML
    private ComboBox type ;
    @FXML
    private ComboBox type1 ;
    @FXML
    private ComboBox type2 ;


    @FXML
            private TextField tarif ;
    @FXML
            private TextField duree;
    @FXML
            private ComboBox domaine;
    @FXML
    private TableView<Formation> table;

    @FXML
    private TableColumn<Formation,Integer> ID;

    @FXML
    private TableColumn<Formation, String> names;

    @FXML
    private TableColumn<Formation, String> typ;

    @FXML
    private TableColumn<Formation, String> niveau;

    @FXML
    private TableColumn<Formation, String> nature;

    @FXML
    private TableColumn<Formation, String> domain;

    @FXML
    private TableColumn<Formation, Float> tarifs;

    @FXML
    private TableColumn<Formation, String> Duree;
    public ObservableList<Formation> Obs ;
    @FXML
    private AnchorPane prec;
    @FXML
    private ComboBox temps;

    public void precedent() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("../Home/home4.fxml"));
        prec.getChildren().setAll(home2);


    }


    public void afficher(){

        try{
            Obs=FXCollections.observableArrayList();
            Connection con = DataSource.getInstance();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM  formation");
            while(rs.next()){
                Obs.add(new Formation(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) ,rs.getString(7), rs.getFloat(8)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ID.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("idfor"));
        names.setCellValueFactory(new PropertyValueFactory<Formation,String>("nom"));
        typ.setCellValueFactory(new PropertyValueFactory<Formation,String>("type"));
        niveau.setCellValueFactory(new PropertyValueFactory<Formation,String>("niveau"));
        nature.setCellValueFactory(new PropertyValueFactory<Formation,String>("nature"));
        domain.setCellValueFactory(new PropertyValueFactory<Formation,String>("domaine"));
        Duree.setCellValueFactory(new PropertyValueFactory<Formation,String>("duree"));
        tarifs.setCellValueFactory(new PropertyValueFactory<Formation,Float>("tarif"));


        table.setItems(Obs);





    }





















    ObservableList list = FXCollections.observableArrayList();
    public void loadList(){
        list.removeAll(list);
        list.addAll("Continue","accéléerer","diplômante");
        type.getItems().addAll(list);

    }
    ObservableList list1 = FXCollections.observableArrayList();
    public void loadList1(){
        list1.removeAll(list1);
        list1.addAll("Avec BAC","Sans BAC");
        type1.getItems().addAll(list1);

    }
    ObservableList list2= FXCollections.observableArrayList();
    public void loadList2(){
        list2.removeAll(list2);
        list2.addAll("BTP","BTS","CAP");
        type2.getItems().addAll(list2);

    }
    ObservableList list3= FXCollections.observableArrayList();
    public void loadList3(){
        list3.removeAll(list3);
        list3.addAll("Genie Logiciel","Gestion","Technologie et informatique");
        domaine.getItems().addAll(list3);

    }
    ObservableList list4= FXCollections.observableArrayList();
    public void loadList4(){
        list4.removeAll(list4);
        list4.addAll("Tous les jours","Les jours paires seulement", "Les jours impaires seulement");
        temps.getItems().addAll(list4);

    }

    public void typeControl(){
        if (type.getValue().equals("diplômante")){
            type1.setVisible(true);
            type2.setVisible(true);
            tarif.setVisible(false);
            duree.setVisible(false);
            domaine.setVisible(false);
            temps.setVisible(false);
            loadList1();
            loadList2();

        }

    }


    public void typeControl2(){
        type1.setVisible(false);
        type2.setVisible(false);
        tarif.setVisible(true);
        duree.setVisible(true);
        domaine.setVisible(true);
        temps.setVisible(true);


    }
    public void ajouter()throws SQLException{
        Connection cnx = DataSource.getInstance();







        String req3= "insert into formation Values (idf.nextval,'"+nom.getText()+"','"+type.getValue()+"','"+type1.getValue()+"','"+type2.getValue()+"','"+domaine.getValue()+"','"+duree.getText()+"',"+tarif.getText()+",'"+temps.getValue()+"')";
            System.out.println(req3);
            cnx.createStatement().executeUpdate(req3);
        System.out.println(req3);
        afficher();
        type1.getItems().clear();
        type1.setValue(null);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Success");
        tray.setMessage(" Formation ajouter ! ");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }




    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadList();
        loadList3();
        loadList4();

        type1.setVisible(false);
        type2.setVisible(false);
    afficher();

    }
}