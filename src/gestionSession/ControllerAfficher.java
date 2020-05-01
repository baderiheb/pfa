package gestionSession;

import Service.ServiceFormateur;
import Service.ServiceFormation;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
import java.util.ResourceBundle;
import java.util.Vector;

public class ControllerAfficher implements Initializable {
    @FXML
    private Text text ;
    @FXML
    private TextField nomF;
    @FXML
    private TextField jourS;
    @FXML
    private  TextArea commen ;
    @FXML
    private TextField hd;
    @FXML
    private TextField hf;
    @FXML
    private TableView<Session> table;
@FXML
private TextField a;
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
    private TableColumn<Session,String> dateFin;
    @FXML
    private TableColumn<Session,Integer> numsalle;
    ObservableList<Formation> Obsfor ;
    ObservableList<Session> Obs;
    public Formateur f ;
    public Formation form;
    ServiceSession ss = new ServiceSession();
    ServiceFormateur sf=new ServiceFormateur();
    ServiceFormation sforma = new ServiceFormation();
static  int s ;
public void detail() throws IOException,SQLException {
 s=table.getSelectionModel().getSelectedItem().getIds();
System.out.printf(String.valueOf(s));
    Parent root = FXMLLoader.load(getClass().getResource("AffichageDetails.fxml"));
   Stage primaryStage = new Stage();
    primaryStage.setTitle("Details");
    Scene scene = new Scene(root, 456 ,619);
//        scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
}

public void remplissage() throws SQLException{
System.out.printf(String.valueOf(s));
    ResultSet rs =ss.afficher(s);
    while (rs.next()){

        ResultSet rs2= sforma.afficherID(rs.getString(3));
        while(rs2.next()){

            String s1=rs2.getString(2);
            System.out.printf(s1);
            nomF.setText(s1);
        }
        jourS.setText(rs.getString(10));
        commen.setText(rs.getString(9));
        hd.setText(rs.getString(11));
        hf.setText(rs.getString(12));
        String text1 ="© Information detaillés de la session "+rs.getString(4);
        text.setText(text1);
    }


}

    public void afficher(){
        try{
            Obs=FXCollections.observableArrayList();


            ResultSet rs =ss.afficher();
            while(rs.next()){
                Vector<Formateur> formats = new Vector<Formateur>();
                ResultSet rs2=sf.afficherID(rs.getString(2));
                while(rs2.next()){

                    f=new Formateur (rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getInt(5),rs2.getInt(6));
                    formats.add(f);
                }
                ResultSet rs3=sforma.afficherID(rs.getString(3));
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
        IDf.setCellValueFactory(new PropertyValueFactory<Session,Integer>("forma"));
        IDfor.setCellValueFactory(new PropertyValueFactory<Session,Integer>("idformation"));
        nom.setCellValueFactory(new PropertyValueFactory<Session,String>("nom"));
        ddebut.setCellValueFactory(new PropertyValueFactory<Session,String>("date"));
        duree.setCellValueFactory(new PropertyValueFactory<Session,Float>("duree"));
        numsalle.setCellValueFactory(new PropertyValueFactory<Session,Integer>("numSalle"));
        dateFin.setCellValueFactory(new PropertyValueFactory<Session,String>("dateFin"));
        table.setItems(Obs);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //afficher();

    }
}