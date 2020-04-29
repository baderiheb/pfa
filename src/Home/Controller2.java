package Home;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @FXML
    private AnchorPane client;
    @FXML
private ImageView afficher ;
    @FXML
    private ImageView rechercher;
    @FXML
    private ImageView addC;
    public  void rechercher()throws  IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../rechercherC/rechercher.fxml"));
        client.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service rechercher un client");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));

    }
public void afficher() throws  IOException{
    Parent ajou = FXMLLoader.load(getClass().getResource("../AfficherC/afficher.fxml"));
    client.getChildren().setAll(ajou);
    TrayNotification tray = new TrayNotification( );
    AnimationType type = AnimationType.SLIDE ;
    tray.setAnimationType(type);
    tray.setTitle("Information");
    tray.setMessage(" Service afficher un client");
    tray.setNotificationType(NotificationType.INFORMATION);
    tray.showAndDismiss(Duration.millis(3000));
}
    public void ajouter() throws  IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../ajouterC/ajouterC.fxml"));
        client.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service ajouter un client");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
    }
    public void modifier() throws  IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../modifierC/modifier.fxml"));
        client.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service modifier un client");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
    }
    public void supprimer() throws  IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../supprimerC/Supprimer.fxml"));
        client.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service supprimer un client");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
    }
public void retourner() throws IOException{
        Parent root =FXMLLoader.load(getClass().getResource("home2.fxml"));
        client.getChildren().setAll(root);

}




    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}