package main.java.run;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.controller.AlertHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ExpenseTracker extends Application{



    public final String VERSION = getAppVersion();
    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.close();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/main/Index.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/base.css");
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("icon.png"));
            primaryStage.setTitle("Haushaltsbuch - "+VERSION);

            primaryStage.show();
        }catch (Exception e){
            AlertHandler.showErrorAlert("Unable to load.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private String getAppVersion(){
        /*
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("META-INF/MANIFEST.MF");
        final Map<String, String> retMap = new HashMap<String, String>();
        try {
            Manifest manifest = new Manifest(stream);
            final Attributes attributes = manifest.getMainAttributes();
            for (Map.Entry attribute : attributes.entrySet()) {
                if (attribute.getKey().toString().equals("Build-Label")){
                    return attribute.getValue().toString();
                }else{
                    System.err.println(attribute.getKey().toString());
                    System.err.println(attribute.getValue().toString());
                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }
        */
        return "v0.9.2";
    }
}
