package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.model.Transaction;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ShowAllTransactions extends AbstractViewController implements Initializable {

    @FXML
    TableView allTransactionsTable;

    @FXML
    TableColumn ttc_Id = new TableColumn("ID");

    @FXML
    TableColumn ttc_Date = new TableColumn("Date");

    @FXML
    TableColumn ttc_paymentMethod = new TableColumn("payment method");

    @FXML
    TableColumn ttc_receiver = new TableColumn("Receiver");

    @FXML
    TableColumn ttc_category = new TableColumn("Category");

    @FXML
    TableColumn ttc_amount = new TableColumn("Amount");

    @FXML
    TableColumn ttc_notes = new TableColumn("Notes");

    @FXML
    Label lblTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //programName.setCellValueFactory(new PropertyValueFactory<>("Program Name"));
        //programVersion.setCellValueFactory(new PropertyValueFactory<>("Version"));
        initTableCols();
    }


    @Override
    public Stage getStage() {
        return (Stage) this.lblTitle.getScene().getWindow();
    }

    //TODO Make DRY!! or delete
    
    public void initTableCols() {

        //get Data from DB
        final ObservableList<Object> allTransactions =  new  DataController().selectAll(Transaction.class);

        ObservableList<Transaction> transactionObservableList = FXCollections.observableArrayList();
        transactionObservableList.add(new Transaction());


        prepareColumn(ttc_Id, "transaction_id");
        prepareColumn(ttc_Date, "date");
        prepareColumn(ttc_paymentMethod, "paymentMethod");
        prepareColumn(ttc_receiver, "receiver");
        prepareColumn(ttc_category, "transactionCategory");
        prepareColumn(ttc_amount, "amount");
        prepareColumn(ttc_notes, "notes");

        ttc_Date.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<Transaction, Date>>) event -> {
            Transaction transaction = event.getRowValue();
            transaction.setDate(event.getNewValue());
            transactionObservableList.add(new Transaction());
        });

        allTransactionsTable.getColumns().addAll(ttc_Id, ttc_Date, ttc_paymentMethod, ttc_receiver, ttc_category, ttc_amount, ttc_notes );
        allTransactionsTable.setItems(allTransactions);
        allTransactionsTable.setVisible(true);
//
//
        //TableColumn amount_col = new TableColumn("Preis");
        //amount_col.setMinWidth(100);
        //amount_col.setEditable(true);
        //amount_col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));//TODO Handle Exeption
        //amount_col.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
        //amount_col.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) cellEditEvent -> {
        //    Transaction thisTransaction = transactionObservableList.get(transactionObservableList.size()-2);
        //    thisTransaction.setAmount((Double) cellEditEvent.getNewValue());
        //    allTransactionsTable.refresh();
        //});
//
//
        //TableColumn notes_col = new TableColumn("Notizen");
        //notes_col.setMinWidth(200);
        //notes_col.setEditable(true);
        //notes_col.setCellFactory(TextFieldTableCell.forTableColumn()); //TODO Handle Exeption
        //notes_col.setCellValueFactory(new PropertyValueFactory<Transaction, String>("notes"));
        //notes_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Transaction, String>>() {
        //    @Override
        //    public void handle(TableColumn.CellEditEvent event) {
        //        Transaction thisTransaction = transactionObservableList.get(transactionObservableList.size()-2);
        //        thisTransaction.setNotes(event.getNewValue().toString());
        //        allTransactionsTable.refresh();
        //    }
        //});
//
        //allTransactionsTable.setItems(transactionObservableList);
        ////allTransactionsTable.getColumns().removeAll();
//
        //allTransactionsTable.getColumns().addAll(transaction_name_col, amount_col,notes_col );
        //allTransactionsTable.getRowFactory();
        System.out.println(allTransactionsTable.getColumns().size());

        System.out.println("Init Table");
    }

    private void prepareColumn(TableColumn tc, String field){
        tc.setMinWidth(300);
        tc.setEditable(true);
        tc.setVisible(true);
        tc.setCellValueFactory(new PropertyValueFactory<>(field));
    }

}
