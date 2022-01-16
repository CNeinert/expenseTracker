package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAllTransactions extends AbstractViewController implements Initializable {

    @FXML
    TreeTableView tbl_showAllTransactions;

    @FXML
    Label lblTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tbl_showAllTransactions = new TreeTableView();

        // programName.setCellValueFactory(new PropertyValueFactory<>("Program Name"));
        // programVersion.setCellValueFactory(new PropertyValueFactory<>("Version"));
        //initTableCols();
    }


    @Override
    public Stage getStage() {
        return (Stage) this.lblTitle.getScene().getWindow();
    }

    //TODO Make DRY!! or delete
    /*
    public void initTableCols() {

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
            }
        });

        TableColumn single_price_col = new TableColumn("Einzelpreis");
        single_price_col.setMinWidth(100);
        single_price_col.setEditable(true);
        single_price_col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));//TODO Handle Exeption
        single_price_col.setCellValueFactory(new PropertyValueFactory<Product, Double>("single_price"));
        single_price_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                Product thisProduct = productObservableList.get(productObservableList.size()-2);
                thisProduct.setSingle_price((Double) cellEditEvent.getNewValue());
            }
        });


        TableColumn amount_col = new TableColumn("Menge");
        amount_col.setMinWidth(100);
        amount_col.setEditable(true);
        amount_col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));//TODO Handle Exeption
        amount_col.setCellValueFactory(new PropertyValueFactory<Product, Double>("amount"));
        amount_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                Product thisProduct = productObservableList.get(productObservableList.size()-2);
                thisProduct.setAmount((Double) cellEditEvent.getNewValue());
                TransactionTable.refresh();
            }
        });


        TableColumn notes_col = new TableColumn("Notizen");
        notes_col.setMinWidth(200);
        notes_col.setEditable(true);
        notes_col.setCellFactory(TextFieldTableCell.forTableColumn()); //TODO Handle Exeption
        notes_col.setCellValueFactory(new PropertyValueFactory<Product, String>("notes"));
        notes_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Product thisProduct = productObservableList.get(productObservableList.size()-2);
                thisProduct.setNotes(event.getNewValue().toString());
                TransactionTable.refresh();
            }
        });




        TransactionTable.setItems(productObservableList);
        //TransactionTable.getColumns().removeAll();

        TransactionTable.getColumns().addAll(product_name_col, single_price_col, amount_col,notes_col );
        TransactionTable.getRowFactory();
        System.out.println("Init Table");
    }
    */
}
