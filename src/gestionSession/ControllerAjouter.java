package gestionSession;

import Notification.Tray;
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

import javax.swing.text.html.ImageView;
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
import java.util.Vector;

import static java.lang.Integer.parseInt;


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
    private TextArea commentaire ;
    @FXML
    private ComboBox salle ;
    @FXML
    private ComboBox forma1 ;
    @FXML
    private ComboBox forma2;
    @FXML
    private ComboBox forma3;
  @FXML
  private Text t1;
  @FXML
  private Text t2;
  @FXML
  private Text t3;
  @FXML
  private ComboBox temps ;
  Tray t = new Tray();



    public void plus1(){

        forma2.setVisible(true);
        forma1.setVisible(false);
        t2.setText("+");
        t3.setText("");
        forma3.setVisible(true);
        forma2.setVisible(false);
        forma1.setVisible(false);
       commentaire.setLayoutX(7);
       commentaire.setLayoutY(123);



    }
    public void plus2(){

        forma2.setVisible(true);
        forma1.setVisible(false);
        t2.setText("+");
        t3.setText("+");
        forma3.setVisible(true);
        forma2.setVisible(true);
        forma1.setVisible(false);
        commentaire.setVisible(true);
        commentaire.setLayoutX(7);
        commentaire.setLayoutY(165);


    }
    public void plus3(){

        forma2.setVisible(true);
        forma1.setVisible(false);
        t2.setText("+");
        t3.setText("+");
        forma3.setVisible(true);
        forma2.setVisible(true);
        forma1.setVisible(true);
        commentaire.setVisible(true);
        commentaire.setLayoutX(7);
        commentaire.setLayoutY(198);


    }



    ObservableList list4= FXCollections.observableArrayList();
    public void loadtemp(){
        list4.removeAll(list4);
        list4.addAll("Tous les jours","Les jours paires seulement", "Les jours impaires seulement");
        temps.getItems().addAll(list4);

    }

    public void loadF() {
        try {
            String req = "select cin from forma";
            Connection cnx = DataSource.getInstance();
            ResultSet rs = cnx.createStatement().executeQuery(req);
            while (rs.next()) {
                cin.getItems().add(rs.getString(1));
                forma1.getItems().add(rs.getString(1));
                forma2.getItems().add(rs.getString(1));
                forma3.getItems().add(rs.getString(1));
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
        commentaire.setVisible(true);

        String req1 = "select * from forma where cin = " + cin.getValue();
        ResultSet rs = cnx.createStatement().executeQuery(req1);
        while (rs.next()) {

            f1.setText(rs.getString(1));
            f2.setText(rs.getString(2));
            f3.setText(rs.getString(3));
        }
    }



    public void formationController() throws SQLException {

        commentaire.setVisible(true);
        f1.setVisible(true);
        f2.setVisible(true);
        f3.setVisible(true);
        String req2 = "select * from formation where idfor =" + forma.getValue();
        Connection cnx = DataSource.getInstance();
        ResultSet rs2 = cnx.createStatement().executeQuery(req2);
        while (rs2.next()) {
            f1.setText(rs2.getString(2));
            f2.setText(rs2.getString(3));
            f3.setText(rs2.getString(6));

        }
    }





public Formateur f ;
    public Formation form;



    ObservableList<Session> Obs ;
    ObservableList<Formateur> Obs1 ;


//afficher les information session dans un tableau
ServiceSession ss = new ServiceSession();
    ServiceFormateur sf=new ServiceFormateur();
    ServiceFormation sforma = new ServiceFormation();
    public void afficher(){
        try{
        Obs=FXCollections.observableArrayList();


        ResultSet rs =ss.afficher();
        while(rs.next()){
            Vector<Formateur> formats = new Vector<Formateur>() ;
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
    public void ajouter() throws  SQLException{
        if(name.getText().equalsIgnoreCase("")){
            t.trayError("Saisir le nom session");
        return;
        } if(DateS.getValue().equals(null)){
           t.trayError(" Verifier la date de la session  ");
        return ;
        }
        verifierDate();
        if((VerifieHD()==false)&&(VerifieHF()==false)){
            t.trayError("Veuillez verifier l'heure de debut ou l'heure de fin de la session ");
        }
        ResultSet rs = sf.afficherCin((String) cin.getValue());
      String id="";
        while (rs.next()) {
             id=rs.getString(1);
        }

   afficher();

    }
    public void salleDisqpo(){

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
                    t.trayError("Verifier la date de debut et la date de fin de la session");
                }

            } else {
                t.trayError("Verifier la date de debut et la date de fin de la session");
            }


        }else {
            t.trayError("Verifier la date de debut et la date de fin de la session");
        }
        }
public boolean VerifieHD(){
    if ((Integer.parseInt(hd.getText())>=8) && (Integer.parseInt(hd.getText())<=17)){
return true ;

    }else return false ;
}
public boolean VerifieHF(){
    if ((Integer.parseInt(hf.getText())>=9) && (Integer.parseInt(hd.getText())<=22)) {
        return true;
    }else return false ;

    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
afficher();
         loadF();
        loadFor();
        loadSalle();
f1.setVisible(false);
f2.setVisible(false);
f3.setVisible(false);
loadtemp();
        forma2.setVisible(false);
        forma1.setVisible(false);
        forma3.setVisible(false);
        t2.setText("");
        t3.setText("");
        commentaire.setLayoutX(7);
        commentaire.setLayoutY(84);
    }
}