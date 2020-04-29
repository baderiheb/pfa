package rechercherC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("rechercher.fxml"));
        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root, 905, 452));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
