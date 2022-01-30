package main.java.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertHandler {

    public static void showInfoAlert(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info);
        alert.show();
    }

    public static void showErrorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR,  message);
        alert.show();
    }

    public static boolean getChoiceOfChoiceBox(String description){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(description);
        alert.setContentText("Please choose an option.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == yesButton;
    }
}
