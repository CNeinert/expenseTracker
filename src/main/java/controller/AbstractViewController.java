package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractViewController {

    @FXML
    public javafx.scene.control.Button closeButton;

    @FXML
    public Stage stage;


    @FXML
    public void loadView(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/views/main/" + view + ".fxml")));
        Stage stage = this.getStage();
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    public abstract Stage getStage();


    @FXML
    private void loadBudget() throws IOException {
        try {
            loadView("BudgetOverview");
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,  "Failed to load!");
            alert.show();
        }

    }

    @FXML
    private void loadShowAllTransactions() throws IOException {
        try {
            loadView("ShowAllTransactions");
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,  "Failed to load!");
            alert.show();
        }

    }

    @FXML
    public void loadIndex(){
        try {
            loadView("Index");
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,  "Failed to load!");
            alert.show();
        }
    }

}
