package gestionSession;

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

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerModifier implements Initializable {
    Connection cnx = DataSource.getInstance();

    @FXML
    private TableView<Session> table;

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
            private Label txt11 ;
    @FXML
            private  Label txt12;
    @FXML
            private DatePicker d1;
    @FXML
            private DatePicker d2 ;
    @FXML
            private ComboBox choix;
    @FXML
            private TextField element ;
    @FXML
            private JFXButton cnf;
    @FXML
            private JFXButton rechercher ;
    @FXML
            private Label txt1 ;
    @FXML
            private TextField name ;




    public Formateur f ;
    public Formation form;



    ObservableList<Session> Obs1 ;

    public void afficher(){
        try{
            Obs1=FXCollections.observableArrayList();
            Connection con = DataSource.getInstance();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sess");
            String req2="";
            String req3="";

            while(rs.next()){
                req2="select * from forma where idf="+rs.getString(2);
                ResultSet rs2=con.createStatement().executeQuery(req2);
                while(rs2.next()){
                    f=new Formateur(rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getInt(5),rs2.getInt(6));
                }
                req3="select * from formation where idfor="+rs.getString(3);
                ResultSet rs3=con.createStatement().executeQuery(req3);
                while (rs3.next()){
                    form=new Formation(rs3.getInt(1),  rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6) ,rs3.getString(7), rs3.getFloat(8));
                }

              /*  Session s =new Session(rs.getInt(1),f,form,rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getInt(7),rs.getString(8),rs.getString(9));
                Obs1.add(s);*/
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        IDs.setCellValueFactory(new PropertyValueFactory<Session,Integer>("ids"));
        IDf.setCellValueFactory(new PropertyValueFactory<Session,Integer>("idformateur"));
        IDfor.setCellValueFactory(new PropertyValueFactory<Session,Integer>("idformation"));
        nom.setCellValueFactory(new PropertyValueFactory<Session,String>("nom"));
        ddebut.setCellValueFactory(new PropertyValueFactory<Session,String>("date"));
        duree.setCellValueFactory(new PropertyValueFactory<Session,Float>("duree"));




        table.setItems(Obs1);



    }


    ObservableList list = FXCollections.observableArrayList();
    public void loadList(){
        list.removeAll(list);
        list.addAll("Nom", "Date de debut");
        choix.getItems().addAll(list);
        if (choix.getValue() == "Nom"){
            element.setVisible(true);
        }
    }
    public void confirmer (){
        if (choix.getValue().equals("Nom")){
            txt11.setVisible(true);
            element.setVisible(true);
            txt1.setVisible(false);
            choix.setVisible(false);

            rechercher.setVisible(true);
        }else {
            txt12.setVisible(true);
            d1.setVisible(true);
            txt1.setVisible(false);
            choix.setVisible(false);
            rechercher.setVisible(true);

        }
    }
public void getSelected(){
       name.setText(table.getSelectionModel().getSelectedItem().getNom());
    d2.setValue(LOCAL_DATE(table.getSelectionModel().getSelectedItem().getDate()));
}



public void rechercher () throws SQLException {
    ObservableList<Session> Obs = FXCollections.observableArrayList();
    ;

    String req1 = "";

    if (choix.getValue().equals("Nom")) {
        req1 = "select * from sess  where nom ='" +element.getText() + "'";

    } else {
        req1 = "select * from sess  where dateDebut ='" +d1.getValue()+ "'";
    }

    ResultSet rs = cnx.createStatement().executeQuery(req1);
    int rowCount = 0;

    while (rs.next()) {
       /* Session s =new Session(rs.getInt(1),f,form,rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getInt(7),rs.getString(8),rs.getString(9));
        Obs.add(s);
        rowCount++;*/
    }
    System.out.println(rowCount);
    if (rowCount == 0) {
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Pas de session  ");
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));

    }
    IDs.setCellValueFactory(new PropertyValueFactory<Session,Integer>("ids"));
    IDf.setCellValueFactory(new PropertyValueFactory<Session,Integer>("idformateur"));
    IDfor.setCellValueFactory(new PropertyValueFactory<Session,Integer>("idformation"));
    nom.setCellValueFactory(new PropertyValueFactory<Session,String>("nom"));
    ddebut.setCellValueFactory(new PropertyValueFactory<Session,String>("date"));
    duree.setCellValueFactory(new PropertyValueFactory<Session,Float>("duree"));




    table.setItems(Obs);



}

public void modifier() throws SQLException{
        String res1 = "update  sess set nom ='"+name.getText()+"',datedebut= '"+d2.getValue()+"' where ids="+table.getSelectionModel().getSelectedItem().getIds();
        cnx.createStatement().executeUpdate(res1);
        afficher();
    TrayNotification tray = new TrayNotification();
    AnimationType type = AnimationType.SLIDE;
    tray.setAnimationType(type);
    tray.setTitle("Information");
    tray.setMessage(" Session modifier  ");
    tray.setNotificationType(NotificationType.SUCCESS);
    tray.showAndDismiss(Duration.millis(3000));
        name.clear();
}



    public void initialize(URL url, ResourceBundle resourceBundle) {
afficher();
loadList();
txt11.setVisible(false);
txt12.setVisible(false);
d1.setVisible(false);
element.setVisible(false);
rechercher.setVisible(false);

    }
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}