package Home;

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
import java.io.PipedReader;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller3 implements Initializable {
    @FXML
    private AnchorPane client;
    @FXML
    private ImageView addF;

    public void ajouter() throws  IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../gestionFormation/ajouter.fxml"));
        client.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service ajouter formation");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
    }
    public void rechercher() throws  IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../gestionFormation/rechercher.fxml"));
        client.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service rechercher formation");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
    }
    public void afficher() throws  IOException{
        Parent ajou = FXMLLoader.load(getClass().getResource("../gestionFormation/afficher.fxml"));
        client.getChildren().setAll(ajou);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle("Information");
        tray.setMessage(" Service afficher formation");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
    }
public void retourner() throws IOException{
        Parent root =FXMLLoader.load(getClass().getResource("home.fxml"));
        client.getChildren().setAll(root);

}




    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}