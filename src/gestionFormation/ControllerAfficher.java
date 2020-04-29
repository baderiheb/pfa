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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerAfficher implements Initializable {

    @FXML
    private Pane showf;

    @FXML
    private JFXButton retourner;

    @FXML
    private TableView<Formation> table;

    @FXML
    private TableColumn<Formation,Integer> ID;

    @FXML
    private TableColumn<Formation, Integer> IDF;

    @FXML
    private TableColumn<Formation, Integer> IDS;

    @FXML
    private TableColumn<Formation, String> nom;

    @FXML
    private TableColumn<Formation,String > prenom;

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

    public void precedent() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("../Home/home4.fxml"));
        showf.getChildren().setAll(home2);


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
        nom.setCellValueFactory(new PropertyValueFactory<Formation,String>("nom"));
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
    }
}