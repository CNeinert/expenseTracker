package main.java.run;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ExpenseTracker extends Application{

    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/main/Index.fxml")));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/base.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
