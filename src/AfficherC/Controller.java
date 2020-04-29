package AfficherC;

import com.jfoenix.controls.JFXButton;
import connectionDB.Client;
import connectionDB.DataSource;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;




    public class Controller implements Initializable {
        @FXML
        private Pane showc;
        @FXML
        private JFXButton btn ;
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
        public void precedent() throws IOException {
            Parent home3 = FXMLLoader.load(getClass().getResource("../Home/home3.fxml"));
            showc.getChildren().setAll(home3);


        }





        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
    }