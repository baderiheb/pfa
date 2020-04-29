package Home;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Controller implements Initializable {


    @FXML
    private ImageView home3;
    @FXML
    private ImageView delete ;

    @FXML
    private ImageView gc;
    @FXML
    private AnchorPane ds ;
    @FXML
    private JFXButton dec;
    @FXML
    private AnchorPane dg;
    @FXML
    private ImageView ajouterforma;
    @FXML
    private ImageView rechercher;
    @FXML
    private ImageView show;
    @FXML
    private JFXButton precedent;
    @FXML
    private ImageView rechercherF;
    @FXML
    private ImageView deconnecter;
    @FXML
    private AnchorPane client;

    public void home3()throws IOException{
        Parent home3 = FXMLLoader.load(getClass().getResource("home3.fxml"));
        ds.getChildren().setAll(home3);
    }
    @FXML
    public void showview() throws IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../../../projet/src/afficher/afficher.fxml"));
        dg.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service afficher un formateur ");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));

    }
    public void delete () throws IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../supprimer/Supprimer.fxml"));
        dg.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service supprimer un formateur ");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));


    }
    public void modifierF() throws IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../Modifier/modifier.fxml"));
        dg.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service modifier un formateur ");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));

    }
    @FXML
    public void LogOut1() throws IOException {
        Parent ajou = FXMLLoader.load(getClass().getResource("../Login1/FXMLDocument.fxml"));
        ds.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;
        tray.setAnimationType(type);
        tray.setTitle("Logout");
        tray.setMessage(" Deconnecter avec succés ");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

    }
    public void LogOut() throws IOException {
        Parent ajou = FXMLLoader.load(getClass().getResource("../Login1/FXMLDocument.fxml"));
        dg.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;
        tray.setAnimationType(type);
        tray.setTitle("Logout");
        tray.setMessage(" Deconnecter avec succés ");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

    }
    /*methode de retourner a la fenetre précedente */
    public void precedent() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("home.fxml"));
        dg.getChildren().setAll(home2);


    }
    @FXML
    public void rechercheView() throws IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../rechercher/rechercher.fxml"));
        dg.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service rechercher un formateur ");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));

    }

    @FXML
    private void gestionFormateur() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("home2.fxml"));
ds.getChildren().setAll(home2);

    }
    @FXML
    private void gestionFormation() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("home4.fxml"));
        ds.getChildren().setAll(home2);

    }
    @FXML
    private void gestionSession() throws IOException {
        Parent home2 = FXMLLoader.load(getClass().getResource("home5.fxml"));
        ds.getChildren().setAll(home2);

    }
@FXML
    private void setSceneaAjouter() throws  IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../ajouter/ajouter.fxml"));
        dg.getChildren().setAll(ajou);
    TrayNotification tray = new TrayNotification( );
    AnimationType type = AnimationType.SLIDE ;
    tray.setAnimationType(type);
    tray.setTitle("Information");
    tray.setMessage(" Service Ajouter un formateur ");
    tray.setNotificationType(NotificationType.INFORMATION);
    tray.showAndDismiss(Duration.millis(3000));

    }





    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}