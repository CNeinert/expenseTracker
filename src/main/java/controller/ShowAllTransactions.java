package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeTableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAllTransactions extends AbstractViewController implements Initializable {

    @FXML
    public TreeTableView tbl_showAllTransactions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadIndex(){
        try {
            loadView("Index");
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,  "Failed to load!");
            alert.show();
        }
    }
}
