package main.resources.views.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class IndexController implements Initializable {

    @FXML
    Pane MainPane = new Pane();
    @FXML
    ProgressIndicator scanProgress = new ProgressIndicator();
    @FXML
    Button SettingsButton = new Button();
    @FXML
    Label OneServerLabel = new Label();
    @FXML
    Label TwoServerLabel = new Label();
    @FXML
    Label ThreeServerLabel = new Label();
    @FXML
    Label FourServerLabel = new Label();
    @FXML
    Label FiveServerLabel = new Label();
    @FXML
    Label SixServerLabel = new Label();
    Label[] labelArray = new Label[6];



    /*
     * @FXML private TableColumn<Program, String> programName;
     *
     * @FXML private TableColumn<Program, String> (SimpleStringProperty)
     * programVersion;
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("INITIALIZE! OVERVIEW");
        // programName.setCellValueFactory(new PropertyValueFactory<>("Program Name"));
        // programVersion.setCellValueFactory(new PropertyValueFactory<>("Version"));


    }

    @FXML
    private void loadSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Settings.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Settings");
        stage.show();
    }



    public void showOverview() {

    }

    private void initTables() {

    }


    private void changeMouse(final Cursor cursor) {
        Platform.runLater(() -> MainPane.setCursor(cursor));
    }

}
