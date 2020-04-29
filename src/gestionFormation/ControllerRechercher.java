package gestionFormation;

import com.jfoenix.controls.JFXButton;
import connectionDB.DataSource;
import connectionDB.Formateur;
import connectionDB.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerRechercher implements Initializable {
    @FXML
        private ComboBox choi ;
    @FXML
    private  ComboBox choix1;
    @FXML
    private TableView<Formation> table;

    @FXML
    private TableColumn<Formation,Integer> ID;

    @FXML
    private TableColumn<Formation, Integer> IDF;

    @FXML
    private TableColumn<Formation, Integer> IDS;

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

    @FXML
            private TextField name;
    @FXML
            private JFXButton btn;


    ObservableList list = FXCollections.observableArrayList();
    public void loadList(){
        list.removeAll(list);
        list.addAll("Nom","Type","Domaine");
        choi.getItems().addAll(list);

    }
    public ObservableList<Formation> Obs ;
    public void rechercher() throws SQLException {
            Obs=FXCollections.observableArrayList();

        String req1 = "";
        if (choi.getValue().equals("Nom")) {
            req1 = "select * from formation where nom ='" + name.getText() + "'";
        }
        if (choi.getValue().equals("Type")) {
            req1 = "select * from formation where typef= '" + choix1.getValue() + "'";

        } if(choi.getValue().equals("Domaine")){
            req1 = "select * from formation where domaine ='" + choix1.getValue() + "'";
        }
        System.out.println(req1);
        Connection cnx = DataSource.getInstance();
        ResultSet rs = cnx.createStatement().executeQuery(req1);

        while (rs.next()) {
            Obs.add(new Formation(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) ,rs.getString(7), rs.getFloat(8)));
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
    public void loadControl(){

        ObservableList list2;
        ObservableList list1;
        btn.setVisible(true);
        if (choi.getValue().equals("Nom")){
            name.setVisible(true);

        }if (choi.getValue().equals("Type")){
            name.setVisible(false);
            choix1.setVisible(true);
             list1= FXCollections.observableArrayList();
            list1.removeAll(list1);
            list1.addAll("Continue","accéléerer","diplômante");
            choix1.getItems().addAll(list1);
        }if(choi.getValue().equals("Domaine")){
            choix1.setVisible(true);
              list2= FXCollections.observableArrayList();
            list2.removeAll(list2);
            list2.addAll("Genie Logiciel","Gestion","Technologie et informatique");
            choix1.getItems().addAll(list2);
        }

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













    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficher();
        loadList();
        btn.setVisible(false);
        choix1.setVisible(false);
        name.setVisible(false);

    }
}