package main.java.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import javafx.util.converter.DoubleStringConverter;
import main.java.model.Category;
import main.java.model.Transaction;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class IndexController extends AbstractViewController implements Initializable {

    @FXML
    Pane MainPane = new Pane();
    @FXML
    ProgressIndicator saveProgress = new ProgressIndicator();
    @FXML
    Button SettingsButton = new Button();
    @FXML
    Label OneServerLabel = new Label();

    @FXML
    DatePicker transDate = new DatePicker();

    @FXML
    TextField location = new TextField();

    @FXML
    private TableView<Transaction> TransactionTable;// =
    @FXML
    private TableColumn<Transaction, String> product_name_col;

    @FXML
    private TableColumn<Transaction, Double> single_price_col;

    @FXML
    private TableColumn<Transaction, Double> amount_col;

    @FXML
    private TableColumn<Transaction, Double> total_price_col;




    /*
     * @FXML private TableColumn<Program, String> programName;
     *
     * @FXML private TableColumn<Program, String> (SimpleStringProperty)
     * programVersion;
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("INITIALIZE! Index View");
        // programName.setCellValueFactory(new PropertyValueFactory<>("Program Name"));
        // programVersion.setCellValueFactory(new PropertyValueFactory<>("Version"));
        initTableCols();
        transDate.setOnAction(e  -> {
            LocalDate date = transDate.getValue();
            System.err.println("Selected date: " + date);
        });


    }

    @FXML
    private void loadShowAllTransactions() throws IOException {
        try {
            loadView("AllTransactions");
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,  "Failed to load!");
            alert.show();
        }

    }



    public void showOverview() {

    }
    //TODO Make DRY!!
    public void initTableCols() {
        TransactionTable = new TableView<Transaction>();
        //this.TransactionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TransactionTable.setEditable(true);

        product_name_col = new TableColumn("Produktbezeichnung");
        product_name_col.setMinWidth(400);
        product_name_col.setEditable(true);
        //this.product_name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        product_name_col.setCellValueFactory(new PropertyValueFactory<Transaction, String>("product_name"));
        product_name_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Transaction, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Transaction transaction = (Transaction) event.getRowValue();
                transaction.setProduct_name(event.getNewValue().toString());
            }
        });


        single_price_col = new TableColumn<>("Einzelpreis");
        single_price_col.setMinWidth(100);
        single_price_col.setEditable(true);
        single_price_col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));//TODO Handle Exeption
        single_price_col.setCellValueFactory(new PropertyValueFactory<>("single_price"));
        single_price_col.setOnEditCommit(event -> {
            Transaction transaction = event.getRowValue();
            transaction.setSingle_price(event.getNewValue());
        });

        amount_col = new TableColumn<>("Menge");
        amount_col.setMinWidth(100);
        amount_col.setEditable(true);
        amount_col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));//TODO Handle Exeption
        amount_col.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amount_col.setOnEditCommit(event -> {
            Transaction transaction = event.getRowValue();
            transaction.setAmount(event.getNewValue());
        });
        total_price_col = new TableColumn<>("Gesamtpreis");
        total_price_col.setMinWidth(100);
        total_price_col.setEditable(true);
        total_price_col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter())); //TODO Handle Exeption
        total_price_col.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        total_price_col.setOnEditCommit(event -> {
            Transaction transaction = event.getRowValue();
            transaction.setTotal_price(event.getNewValue());
        });

        // Casting the program Array to an ObservableList
        ObservableList<Transaction> transactionObservableList = FXCollections.observableArrayList();
        transactionObservableList.add(new Transaction());

        TransactionTable.setItems(transactionObservableList);
        //TransactionTable.getColumns().removeAll();

        TransactionTable.getColumns().addAll(product_name_col, single_price_col, amount_col,total_price_col );
        TransactionTable.getRowFactory();
        System.out.println("Init Table");
    }


    private void changeMouse(final Cursor cursor) {
        Platform.runLater(() -> MainPane.setCursor(cursor));
    }

    public void saveNewTransaction(){
        System.out.print("Saving...");
        try {
            Date date = Date.from(transDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String locationText = location.getText();
            String product_name = "TestProduct";
            Category category = new Category();
            category.setCategory("Test");
            double single_price = 5.0;
            double amount = 2.0;
            double total_price = 10.0;
            Transaction transaction = new Transaction(date, locationText, product_name, category, single_price, amount, total_price);

            DataController dc = new DataController();
            dc.persist(category);
            dc.persist(transaction);

            System.out.println("Location: "+ locationText);
            System.out.println("done.");
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,  "Date missing");
            alert.show();
            e.printStackTrace();
            System.out.println("---- FAILED! ----");
        }


    }

}
