package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public abstract class AbstractViewController {

    @FXML
    public javafx.scene.control.Button closeButton;

    @FXML
    public Stage stage;


    @FXML
    public void loadView(String view, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/views/main/" + view + ".fxml")));
        Parent root = loader.load();
        //Stage stage = stage;
        //stage.setScene(new Scene(root));
        //stage.setTitle(view);
        //stage.show();
        System.out.println("Stage:" + getStage());
        System.out.println("Stage:" + this.stage);
        stage.setScene(new Scene(root));
    }

    public abstract Stage getStage();

//    @FXML
//    private void handleButtonAction (ActionEvent event) throws Exception {
//        Stage stage;
//        Parent root;
//        var btn1;
//
//        if(event.getSource()==btn1){
//            stage = (Stage) btn1.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
//        }
//        else{
//            stage = (Stage) btn2.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        }
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
}
