module Project {
    requires javafx.fxml;
    requires javafx.controls;
    requires  javafx.graphics;
    requires  javafx.base;
    requires java.sql;
    requires com.jfoenix;
    requires fontawesomefx;
    requires  javafx.swing;
    requires  TrayTester;

    opens Main;
    opens ajouter;
}