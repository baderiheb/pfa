
package Login1;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import connectionDB.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import javax.swing.*;


/**
 *
 * @author JISOO
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer2;
    @FXML
    private JFXButton signin;
    @FXML
    private JFXButton signup;
    @FXML
    private Label a2;
    @FXML
    private Label b2;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private TextField u1;
    @FXML
    private PasswordField u2;
    @FXML
    private PasswordField u3;
    @FXML
    private TextField n1;

    @FXML
    private Label c1;
    @FXML
    private JFXButton c2;

    @FXML
    private AnchorPane layer1;
    @FXML
    private ComboBox choix;
    @FXML
            private StackPane next;
    ObservableList list = FXCollections.observableArrayList();

    public void loadList() {
        list.removeAll(list);
        list.addAll("Administrateur", "Secretaire", "Comptable");
        choix.getItems().addAll(list);

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadList();
        c2.setVisible(false);
        c1.setVisible(true);
        signup.setVisible(false);
        b2.setVisible(false);
        btnsignin.setVisible(true);
        n1.setVisible(false);
        u1.setVisible(true);
        u2.setVisible(false);
        u3.setVisible(true);
    }

    @FXML
    private void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(610);
        slide.play();
        layer1.setTranslateX(-309);
        c2.setVisible(true);
        btnsignin.setVisible(false);
        b2.setVisible(true);
        choix.setVisible(false);
        c1.setVisible(true);
        signup.setVisible(true);

        signin.setVisible(true);
        a2.setVisible(false);
        n1.setVisible(true);
        u1.setVisible(false);
        u2.setVisible(true);
        u3.setVisible(false);
        c1.setVisible(true);

        slide.setOnFinished((e -> {


        }));
    }

    @FXML
    private void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        slide.setToX(0);
        slide.play();
        layer1.setTranslateX(0);
        btnsignin.setVisible(true);
        c1.setVisible(true);
        a2.setVisible(true);
        choix.setVisible(true);
        b2.setVisible(false);
        c2.setVisible(false);
        signup.setVisible(false);

        signin.setVisible(true);
        a2.setVisible(true);
        c1.setVisible(false);
        n1.setVisible(false);
        u1.setVisible(true);
        u2.setVisible(false);
        u3.setVisible(true);

        slide.setOnFinished((e -> {


        }));
    }
Connection con = DataSource.getInstance();
    @FXML
    public void inscription() throws SQLException {
        String username=u1.getText();
        String role= (String) choix.getValue();
        String password =u3.getText();
        System.out.println(role);
        System.out.println(username);
        System.out.println(password);

        if (username.equalsIgnoreCase("")|| role.equalsIgnoreCase(null)|| password.equalsIgnoreCase("")){
            TrayNotification tray = new TrayNotification( );
            AnimationType type = AnimationType.SLIDE ;
            tray.setAnimationType(type);
            tray.setTitle("Erreur");
            tray.setMessage("Verifier les information entrer");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
            return;
        }
        String req1= "insert into users VALUES('"+username+"','"+role+"','"+password+"') ";
        con.createStatement().executeUpdate(req1);
        TrayNotification tray = new TrayNotification( );
        AnimationType type = AnimationType.SLIDE ;
        tray.setAnimationType(type);
        tray.setTitle(role);
        tray.setMessage(" Compte creer ");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
        u1.clear();
        u3.clear();

    }
    @FXML
    private void btnsignup(MouseEvent event) {
    }

    @FXML
    private void sign(MouseEvent event) {

    }
    public void loginPress () throws IOException {

        String NomUt = n1.getText();
        System.out.println(NomUt);
        String Password = u2.getText();
        System.out.println(Password);

        try {
            Connection con = DataSource.getInstance();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users ");
            while (rs.next()) {


                if (rs.getString(1).equals(NomUt)&&(rs.getString(3).equals(Password))) {
               //     JOptionPane.showMessageDialog(null, "Login success");
                    TrayNotification tray = new TrayNotification( );
                    AnimationType type = AnimationType.SLIDE ;
                    tray.setAnimationType(type);
                    tray.setTitle("Login");
                    tray.setMessage(" Connecter avec succés  ");
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));
                    Parent ajou = FXMLLoader.load(getClass().getResource("../Home/home.fxml"));
                    next.getChildren().setAll(ajou);
                    return;
                }



            }TrayNotification tray = new TrayNotification( );
            AnimationType type = AnimationType.SLIDE ;
            tray.setAnimationType(type);
            tray.setTitle("Login");
            tray.setMessage(" Erreur de se conecter  ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
