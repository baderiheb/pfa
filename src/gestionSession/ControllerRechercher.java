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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRechercher implements Initializable {
    Connection cnx = DataSource.getInstance();


    @FXML
    private JFXButton retourner;

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
    @FXML
    private AnchorPane prec;

    @FXML
    private JFXButton rech;

    @FXML
    private TextField element;

    @FXML
    private ChoiceBox choix;
    @FXML
    private Label txt1;
    @FXML
    private Label txt2;
    @FXML
    private Label txt3;
    @FXML
    private JFXButton confirmer;
    @FXML
    private DatePicker DateS;
    @FXML
    private JFXButton ret;

    public void afficher() {
        try {
            Obs = FXCollections.observableArrayList();
            Connection con = DataSource.getInstance();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sess");
            String req2 = "";
            String req3 = "";

            while (rs.next()) {
                req2 = "select * from forma where idf=" + rs.getString(2);
                ResultSet rs2 = con.createStatement().executeQuery(req2);
                while (rs2.next()) {
                    f = new Formateur(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getInt(5), rs2.getInt(6));
                }
                req3 = "select * from formation where idfor=" + rs.getString(3);
                ResultSet rs3 = con.createStatement().executeQuery(req3);
                while (rs3.next()) {
                    form = new Formation(rs3.getInt(1), rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6), rs3.getString(7), rs3.getFloat(8));
                }

             /*   Session s =new Session(rs.getInt(1),f,form,rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getInt(7),rs.getString(8),rs.getString(9));
                Obs.add(s);*/
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        IDs.setCellValueFactory(new PropertyValueFactory<Session, Integer>("ids"));
        IDf.setCellValueFactory(new PropertyValueFactory<Session, Integer>("idformateur"));
        IDfor.setCellValueFactory(new PropertyValueFactory<Session, Integer>("idformation"));
        nom.setCellValueFactory(new PropertyValueFactory<Session, String>("nom"));
        ddebut.setCellValueFactory(new PropertyValueFactory<Session, String>("date"));
        duree.setCellValueFactory(new PropertyValueFactory<Session, Float>("duree"));


        table.setItems(Obs);


    }

    ObservableList list = FXCollections.observableArrayList();

    public void loadList() {
        list.removeAll(list);
        list.addAll("Nom", "Date de debut");
        choix.getItems().addAll(list);

    }

    public void confirmer() {

        if (choix.getValue() == null) {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Erreur");
            tray.setMessage(" Vueillez choisir la méthode de recherche ");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
            return;
        }
        if (choix.getValue().equals("Nom")) {
            txt1.setVisible(false);
            choix.setVisible(false);
            txt2.setVisible(true);
            element.setVisible(true);
            rech.setVisible(true);
            txt3.setVisible(false);
            DateS.setVisible(false);
            confirmer.setVisible(false);
            ret.setVisible(true);
        } else {
            txt1.setVisible(false);
            choix.setVisible(false);
            txt2.setVisible(false);
            element.setVisible(false);
            rech.setVisible(true);
            txt3.setVisible(true);
            DateS.setVisible(true);
            confirmer.setVisible(false);
            ret.setVisible(true);


        }

    }

    public void retourner() {
        txt1.setVisible(true);
        choix.setVisible(true);
        txt2.setVisible(false);
        element.setVisible(false);
        rech.setVisible(false);
        txt3.setVisible(false);
        DateS.setVisible(false);
        confirmer.setVisible(true);
        ret.setVisible(false);

    }

    public void rechercher() throws SQLException {
        ObservableList<Session> Obs = FXCollections.observableArrayList();
        ;

        String req1 = "";

        if (choix.getValue().equals("Nom")) {
            req1 = "select * from sess  where nom ='" + element.getText() + "'";

        } else {
            req1 = "select * from sess  where dateDebut ='" + DateS.getValue() + "'";
        }

        ResultSet rs = cnx.createStatement().executeQuery(req1);
        int rowCount = 0;

        String req2 = "";
        String req3 = "";

        while (rs.next()) {
            req2 = "select * from forma where idf=" + rs.getString(2);
            ResultSet rs2 = cnx.createStatement().executeQuery(req2);
            while (rs2.next()) {
                f = new Formateur(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getInt(5), rs2.getInt(6));
            }
            req3 = "select * from formation where idfor=" + rs.getString(3);
            ResultSet rs3 = cnx.createStatement().executeQuery(req3);
            while (rs3.next()) {
                form = new Formation(rs3.getInt(1), rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6), rs3.getString(7), rs3.getFloat(8));
            }

          /*  Session s =new Session(rs.getInt(1),f,form,rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getInt(7),rs.getString(8),rs.getString(9));
            Obs.add(s);
            rowCount++;*/
        }




        System.out.println(rowCount);
        if(rowCount ==0){
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Pas de session  ");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));

    }
        IDs.setCellValueFactory(new PropertyValueFactory<Session, Integer>("ids"));
        IDf.setCellValueFactory(new PropertyValueFactory<Session, Integer>("idformateur"));
        IDfor.setCellValueFactory(new PropertyValueFactory<Session, Integer>("idformation"));
        nom.setCellValueFactory(new PropertyValueFactory<Session, String>("nom"));
        ddebut.setCellValueFactory(new PropertyValueFactory<Session, String>("date"));
        duree.setCellValueFactory(new PropertyValueFactory<Session, Float>("duree"));






        table.setItems(Obs);

}

    public void initialize(URL url, ResourceBundle resourceBundle) {
afficher();
loadList();
element.setVisible(false);
txt3.setVisible(false);
txt2.setVisible(false);
rech.setVisible(false);
DateS.setVisible(false);
ret.setVisible(false);
    }
}