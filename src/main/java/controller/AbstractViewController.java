package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Objects;

public abstract class AbstractViewController {

    @FXML
    public javafx.scene.control.Button closeButton;

    @FXML
    public Stage stage;

    @FXML
    public void loadView(String view) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/views/main/" + view + ".fxml")));
            Stage stage = this.getStage();
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,  "Failed to load!");
            alert.show();
        }
    }

    public abstract Stage getStage();


    @FXML
    public void loadBudget() {
        loadView("BudgetOverview");
    }

    @FXML
    public void loadShowAllTransactions() {
        loadView("ShowAllTransactions");
    }

    @FXML
    public void loadIndex(){
        loadView("Index");
    }

}
