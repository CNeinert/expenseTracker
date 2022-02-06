package main.java.run;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controller.AlertHandler;

import java.util.Objects;

public class ExpenseTracker extends Application{

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.close();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/main/Index.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/base.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            AlertHandler.showErrorAlert("Unable to load.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
