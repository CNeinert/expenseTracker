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
import java.util.ResourceBundle;

public abstract class AbstractViewController {



    @FXML
    public void loadView(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + view + ".fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(view);
        stage.show();
    }
}
