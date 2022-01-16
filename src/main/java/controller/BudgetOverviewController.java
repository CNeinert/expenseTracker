package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BudgetOverviewController extends AbstractViewController implements Initializable {

    @FXML
    ScrollBar scrollBarMonth;

    @FXML
    ProgressBar progress01;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public Stage getStage() {
        return (Stage) this.progress01.getScene().getWindow();
    }
}
