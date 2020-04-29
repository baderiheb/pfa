/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionSalle;

import java.net.URL;
import java.util.ResourceBundle;

import connectionDB.DataSource;
import connectionDB.Salle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class ControllerAjouter implements Initializable{
       @FXML
    private AnchorPane salle;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;
        @FXML
    private TextField t3;

    @FXML
    private JFXButton ajouter;

    @FXML
    private TableView<Salle> table;

    @FXML
    private TableColumn<Salle, Integer> numS;

    @FXML
    private TableColumn<Salle, Integer> capacite;

    @FXML
    private TableColumn<Salle, String> dispo;

    @FXML
    private JFXButton retourner;
    @FXML
    private ComboBox temps;

       public ObservableList<Salle> Obs ;

    
    public void ajouter() throws SQLException{
         Connection cnx = DataSource.getInstance();
         String req = "insert into salle values("+t1.getText()+","+t2.getText()+",'"+t3.getText()+"')";
         cnx.createStatement().executeUpdate(req);
         clearFieled();
         afficher();
    }

    public void clearFieled(){
        t1.clear();
        t2.clear();
        t3.clear();
        
        
    }
    public void afficher(){
     try{
           Obs=FXCollections.observableArrayList();
        Connection con = DataSource.getInstance();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM salle");
        while(rs.next()){
          Obs.add(new Salle(rs.getInt(1),rs.getInt(2),rs.getString(3)));


        }

       } catch (SQLException e) {
           e.printStackTrace();
       }
       
        numS.setCellValueFactory(new PropertyValueFactory<Salle,Integer>("numSalle"));
        capacite.setCellValueFactory(new PropertyValueFactory<Salle,Integer>("capaciteSalle"));
       dispo.setCellValueFactory(new PropertyValueFactory<Salle,String>("etat" ));
       

        table.setItems(Obs);    
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficher();
    }
    
}
