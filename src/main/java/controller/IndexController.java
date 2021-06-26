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
import main.java.model.LocationCategory;
import main.java.model.Product;
import main.java.model.Transaction;
import main.java.model.TransactionCategory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
    ChoiceBox transactionCategorieField;

    @FXML
    ChoiceBox locationCategorieField;

    @FXML
    private TableView<Product> TransactionTable;// =
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
        initChoiceBoxes();
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
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        productObservableList.add(new Product());
        TransactionTable.setEditable(true);
        TableColumn product_name_col = new TableColumn("Produktbezeichnung");
        product_name_col.setMinWidth(300);
        product_name_col.setEditable(true);
        product_name_col.setCellFactory(TextFieldTableCell.forTableColumn());
        product_name_col.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        product_name_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Product product = (Product) event.getRowValue();
                product.setProductName(event.getNewValue().toString());
                productObservableList.add(new Product());
                TransactionTable.refresh();

            }
        });

        TableColumn single_price_col = new TableColumn("Einzelpreis");
        single_price_col.setMinWidth(100);
        single_price_col.setEditable(true);
        single_price_col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));//TODO Handle Exeption
        single_price_col.setCellValueFactory(new PropertyValueFactory<Product, Double>("single_price"));


        TableColumn amount_col = new TableColumn("Menge");
        amount_col.setMinWidth(100);
        amount_col.setEditable(true);
        amount_col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));//TODO Handle Exeption
        amount_col.setCellValueFactory(new PropertyValueFactory<Product, Double>("amount"));


        TableColumn notes_col = new TableColumn("Notizen");
        notes_col.setMinWidth(200);
        notes_col.setEditable(true);
        notes_col.setCellFactory(TextFieldTableCell.forTableColumn()); //TODO Handle Exeption
        notes_col.setCellValueFactory(new PropertyValueFactory<Product, String>("notes"));
        notes_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Product transaction = (Product) event.getRowValue();
                transaction.setNotes(event.getNewValue().toString());
                TransactionTable.refresh();
            }
        });




        TransactionTable.setItems(productObservableList);
        //TransactionTable.getColumns().removeAll();

        TransactionTable.getColumns().addAll(product_name_col, single_price_col, amount_col,notes_col );
        TransactionTable.getRowFactory();
        System.out.println("Init Table");
    }


    private void changeMouse(final Cursor cursor) {
        Platform.runLater(() -> MainPane.setCursor(cursor));
    }

    private void initChoiceBoxes(){
        ObservableList<String> olTransCategory = FXCollections.observableArrayList();
        DataController dc = new DataController();
        List<Object> list = dc.selectAll(TransactionCategory.class);
        for (Object item : list) {
            TransactionCategory thisItem = (TransactionCategory)item;
            olTransCategory.add(thisItem.getCategory());
        }

        transactionCategorieField.setItems(olTransCategory);

        ObservableList<String> olLocationCategory = FXCollections.observableArrayList();
        DataController dc2 = new DataController();
        List<Object> list1 = dc2.selectAll(LocationCategory.class);
        for (Object item : list1) {
            LocationCategory thisItem = (LocationCategory)item;
            olLocationCategory.add(thisItem.getLocationCategory());
        }

        locationCategorieField.setItems(olLocationCategory);
    }

    public void saveNewTransaction(){
        System.out.print("Saving...");
        System.err.println("Hello World!");
        try {
            DataController dc = new DataController();
            Date date = Date.from(transDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String locationText = location.getText();
            String product_name = "TestProduct";
            TransactionCategory transactionCategory = new TransactionCategory();
            transactionCategory.setCategory(transactionCategorieField.getValue().toString());
            LocationCategory locationCategorie = new LocationCategory();
            locationCategorie.setLocationCategory(locationCategorieField.getValue().toString());
            double single_price = 5.0;
            double amount = 2.0;
            double total_price = 10.0;
            Transaction transaction = new Transaction(date, product_name, transactionCategory, total_price);

            //region HANDLE CATEGORIES
            // Persist if not exists... why

            TransactionCategory transCatFromDb = (TransactionCategory) dc.selectAllByField
                    (transactionCategory.getClass(), "category", transactionCategory.getCategory()).get(0);

            if (transCatFromDb == null){
                dc.persist(transactionCategory);
            }else{
                transactionCategory = transCatFromDb;
            }

            LocationCategory locationCategorieFromDb = (LocationCategory) dc.selectAllByField
                    (locationCategorie.getClass(), "locationCategory", locationCategorie.getLocationCategory()).get(0);
            //Persist if not exists
            if (locationCategorieFromDb == null){
                dc.persist(transactionCategory);
            }else{
                locationCategorie = locationCategorieFromDb;
            }
            //endregion

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
