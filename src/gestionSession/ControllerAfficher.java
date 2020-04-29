package gestionSession;

import Service.ServiceSession;
import com.jfoenix.controls.JFXButton;
import connectionDB.DataSource;
import connectionDB.Formateur;
import connectionDB.Formation;
import connectionDB.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
import java.util.Vector;

public class ControllerAfficher implements Initializable {
    Connection cnx = DataSource.getInstance();

    @FXML
    private Pane ShowS;

    @FXML
    private JFXButton retourner;

    @FXML
    private TableView<Session> table1;

    @FXML
    private TableColumn<Session, Integer> IDs;
    @FXML
    private TableColumn<Session, Integer> IDf;
    @FXML
    private TableColumn<Session, Integer> IDfor;
    @FXML
    private TableColumn<Session, String> nom;

    @FXML
    private TableColumn<Session,String> ddebut;
    @FXML
    private  TableColumn<Session,Float> duree;

    @FXML
    private TableView<Formation> table2;

    @FXML
    private TableColumn<Formation,Integer> ID;
    @FXML
    private TableColumn<Formation, String> names;

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

    @FXML
    private TableView<Formateur> table3;

    @FXML
    private TableColumn<Formateur, Integer>ID1;
    @FXML
    private  TableColumn<Formateur, String> nom1;
    @FXML
    private  TableColumn<Formateur,String> prenom1;
    @FXML
    private  TableColumn<Formateur,String> adresse;
    @FXML
    private  TableColumn<Formateur,Integer> cin;
    @FXML
    private  TableColumn<Formateur,Integer> Numtel;

    @FXML
    private Label l1;

    @FXML
    private Label l2;
    ObservableList<Formation> Obsfor ;

    public void afficher2(){

        try{
            Obsfor=FXCollections.observableArrayList();
            Connection con = DataSource.getInstance();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM  formation where idfor="+table1.getSelectionModel().getSelectedItem().getIdformation());
            while(rs.next()){
                Obsfor.add(new Formation(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) ,rs.getString(7), rs.getFloat(8)));
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


        table2.setItems(Obsfor);




    }



    public Formateur f ;
    public Formation form;



    ObservableList<Session> Obs ;
    ObservableList<Formateur> Obs1,Obsf ;




    public void afficher1(){
        try{

            Obs=FXCollections.observableArrayList();
        ServiceSession sess =new ServiceSession();
        ResultSet rs = sess.afficher();
        String req2,req3;
        Connection con=DataSource.getInstance();
            while(rs.next()){

                Vector <Formateur> formats = new Vector<Formateur>() ;
                req2="select * from forma where idf="+rs.getString(2);
                ResultSet rs2=con.createStatement().executeQuery(req2);
                while(rs2.next()){

                    f=new Formateur (rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getInt(5),rs2.getInt(6));
                    formats.add(f);
                }
                req3="select * from formation where idfor="+rs.getString(3);
                ResultSet rs3=con.createStatement().executeQuery(req3);
                while (rs3.next()){
                    form=new Formation(rs3.getInt(1),  rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6) ,rs3.getString(7), rs3.getFloat(8));
                }

                Session s =new Session(rs.getInt(1),formats,form,rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getInt(7),rs.getString(8),rs.getString(9));
                Obs.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        IDs.setCellValueFactory(new PropertyValueFactory<Session,Integer>("ids"));
        IDf.setCellValueFactory(new PropertyValueFactory<Session,Integer>("form.getIdfor()"));
        IDfor.setCellValueFactory(new PropertyValueFactory<Session,Integer>("idformation"));
        nom.setCellValueFactory(new PropertyValueFactory<Session,String>("nom"));
        ddebut.setCellValueFactory(new PropertyValueFactory<Session,String>("date"));
        duree.setCellValueFactory(new PropertyValueFactory<Session,Float>("duree"));




        table1.setItems(Obs);



    }


    public void afficher(){

        try{
            Obsf=FXCollections.observableArrayList();
            Connection con = DataSource.getInstance();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM forma where idf ="+table1.getSelectionModel().getSelectedItem().getForma());
            while(rs.next()){
                Obsf.add(new Formateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ID1.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("id"));
        nom1.setCellValueFactory(new PropertyValueFactory<Formateur,String>("nom"));
        prenom1.setCellValueFactory(new PropertyValueFactory<Formateur,String>("prenomF"));
        adresse.setCellValueFactory(new PropertyValueFactory<Formateur,String>("adresse"));
        cin.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("cin"));
        Numtel.setCellValueFactory(new PropertyValueFactory<Formateur,Integer>("numTel"));


        table3.setItems(Obsf);




    }
    public void control(){
        table2.setVisible(true);
        table3.setVisible(true );
        l1.setVisible(true);
        l2.setVisible(true );
        afficher();
        afficher2();
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        table2.setVisible(false);
        table3.setVisible(false );
        l1.setVisible(false);
        l2.setVisible(false);
afficher1();
//loadList();

    }
}