package gestionSession;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.text.DateFormat;


public class ControllerAjouter implements Initializable {
    Connection cnx = DataSource.getInstance();

    @FXML
    private Pane ShowS;

    @FXML
    private JFXButton retourner;

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
    private TextField hd;
    @FXML
    private TextField hf ;
    @FXML
    private TableColumn<Session,String> ddebut;
    @FXML
    private  TableColumn<Session,Float> duree;
    @FXML
    private TableColumn<Session,String> dateFin;
    @FXML
    private TableColumn<Session,Integer> numsalle;
    @FXML
    private TableColumn<Session,String> comm;
    @FXML
    private AnchorPane prec;

    @FXML
            private DatePicker DateS;
    @FXML
            private TextField name;
    @FXML
            private ComboBox cin ;
    @FXML
            private ComboBox forma;
    @FXML
    private TextField nbreh;

    @FXML
    private TextField f1;
    @FXML
    private TextField f2;
    @FXML
    private TextField f3;
    @FXML
    private JFXButton Cconfirmer;
    @FXML
    private JFXButton btn;
    @FXML
    private DatePicker DateF ;
    @FXML
    private TextField commentaire ;
    @FXML
    private ComboBox salle ;

    public void verifieDisspo() throws ParseException {
        String HD  =hd.getText();
                  DateFormat sdf  = new SimpleDateFormat("HH:mm");
        Date d = sdf.parse(HD);
        System.out.println(d);
    }
    public void setCommentaire() throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("comm.fxml"));
        Scene scene = new Scene(root, 920 ,500);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
}





    public void loadF() {
        try {
            String req = "select cin from forma";
            Connection cnx = DataSource.getInstance();
            ResultSet rs = cnx.createStatement().executeQuery(req);
            while (rs.next()) {
                cin.getItems().add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadSalle(){
        try {
            String req1 = "select numsalle from salle";
            Connection cnx = DataSource.getInstance();

            ResultSet rs = cnx.createStatement().executeQuery(req1);
            while (rs.next()) {
                salle.getItems().add(rs.getString(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
        public void loadFor(){
            try {
                String req1 = "select idfor from formation";
                Connection cnx = DataSource.getInstance();

                ResultSet rs = cnx.createStatement().executeQuery(req1);
                while (rs.next()) {
                    forma.getItems().add(rs.getString(1));
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
    }
    public void formateurController () throws SQLException {
        forma.setVisible(false);
        nbreh.setVisible(false);
        DateS.setVisible(false);
        btn.setVisible(false);
        DateF.setVisible(false);
        salle.setVisible(false);
        commentaire.setVisible(false);
        f1.setVisible(true);
        f2.setVisible(true);
        f3.setVisible(true);
        Cconfirmer.setVisible(true);
        String req1 = "select * from forma where cin = " + cin.getValue();
        ResultSet rs = cnx.createStatement().executeQuery(req1);
        while (rs.next()) {

            f1.setText(rs.getString(1));
            f2.setText(rs.getString(2));
            f3.setText(rs.getString(3));
        }
    }


    public void formationController() throws SQLException {
        forma.setVisible(false);
        nbreh.setVisible(false);
        DateS.setVisible(false);
        btn.setVisible(false);
        DateF.setVisible(false);
        salle.setVisible(false);
        commentaire.setVisible(false);
        f1.setVisible(true);
        f2.setVisible(true);
        f3.setVisible(true);
        Cconfirmer.setVisible(true);
        String req2 = "select * from formation where idfor =" + forma.getValue();
        Connection cnx = DataSource.getInstance();
        ResultSet rs2 = cnx.createStatement().executeQuery(req2);
        while (rs2.next()) {
            f1.setText(rs2.getString(2));
            f2.setText(rs2.getString(3));
            f3.setText(rs2.getString(6));

        }
    }




public void confirmer(){
        f1.setVisible(false);
        f2.setVisible(false);
        f3.setVisible(false);
        DateS.setVisible(true);
        nbreh.setVisible(true);
        forma.setVisible(true);
        btn.setVisible(true);
        Cconfirmer.setVisible(false);
    DateF.setVisible(true);
    salle.setVisible(true);
    commentaire.setVisible(true);

}

public Formateur f ;
    public Formation form;



    ObservableList<Session> Obs ;
    ObservableList<Formateur> Obs1 ;



    public void afficher(){
        try{
        Obs=FXCollections.observableArrayList();
        Connection con = DataSource.getInstance();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sess");
        String req2="";
        String req3="";

        while(rs.next()){
            req2="select * from forma where idf="+rs.getString(2);
            ResultSet rs2=con.createStatement().executeQuery(req2);
            while(rs2.next()){
                f=new Formateur (rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getInt(5),rs2.getInt(6));
            }
            req3="select * from formation where idfor="+rs.getString(3);
            ResultSet rs3=con.createStatement().executeQuery(req3);
            while (rs3.next()){
                form=new Formation(rs3.getInt(1),  rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6) ,rs3.getString(7), rs3.getFloat(8));
            }

           /*Session s =new Session(rs.getInt(1),f,form,rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getInt(7),rs.getString(8),rs.getString(9));
Obs.add(s);*/
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
        numsalle.setCellValueFactory(new PropertyValueFactory<Session,Integer>("numSalle"));
        dateFin.setCellValueFactory(new PropertyValueFactory<Session,String>("dateFin"));






        table.setItems(Obs);



    }
    public void ajouter() throws  SQLException{
        if(name.getText().equalsIgnoreCase("")){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Erreur");
            tray.setMessage(" Saisir le nom session ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        return;
        } if(DateS.getValue().equals(null)){
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Erreur");
            tray.setMessage(" Verifier la date de la session  ");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        return ;
        }
        verifierDate();
        String req1 = "select * from forma where cin = " + cin.getValue();
        ResultSet rs = cnx.createStatement().executeQuery(req1);
      String id="",id2="";
        while (rs.next()) {

             id=rs.getString(1);
        }
        String req2 = "select * from formation where idfor =" + forma.getValue();
        Connection cnx = DataSource.getInstance();
        ResultSet rs2 = cnx.createStatement().executeQuery(req2);
        while (rs2.next()) {
             id2=rs2.getString(1);
        }



        String req3 = "insert into sess VALUES (ids.nextval,"+id+","+id2+",'"+name.getText()+"','"+DateS.getValue()+"',"+nbreh.getText()+","+salle.getValue()+",'"+DateF.getValue()+"','"+commentaire.getText()+"')";
    System.out.println(req3);
    Connection cnc=DataSource.getInstance();
        cnc.createStatement().executeUpdate(req3);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;
        tray.setAnimationType(type);
        tray.setTitle("Success");
        tray.setMessage(" Ajouter avex succés");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
   afficher();

    }
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    public void verifierDate() {
        LocalDate datedebut = DateS.getValue();
        LocalDate datefin = DateF.getValue();
        System.out.println(DateS.getValue());

        if (datedebut.getYear() - datefin.getYear() <= 0) {
            System.out.println("SUCESS");
            if (datedebut.getMonthValue() - datefin.getMonthValue() <= 0) {
                System.out.println("SUCESS");

                if (datedebut.getDayOfMonth() - datefin.getDayOfMonth() <= 0) {
                    System.out.println("SUCESS");
                } else {
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.SLIDE;
                    tray.setAnimationType(type);
                    tray.setTitle("Error");
                    tray.setMessage(" Verifier la date de debut et la date de fin de la session");
                    tray.setNotificationType(NotificationType.ERROR);
                    tray.showAndDismiss(Duration.millis(9000));
                    return;


                }

            } else {
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.SLIDE;
                tray.setAnimationType(type);
                tray.setTitle("Error");
                tray.setMessage(" Verifier la date de debut et la date de fin de la session");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(9000));
                return;

            }


        }else {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Error");
            tray.setMessage(" Verifier la date de debut et la date de fin de la session");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(9000));
            return;
        }
        }

    public void initialize(URL url, ResourceBundle resourceBundle) {
afficher();
         loadF();
        loadFor();
        loadSalle();
f1.setVisible(false);
f2.setVisible(false);
f3.setVisible(false);
Cconfirmer.setVisible(false);
//loadList();

    }
}