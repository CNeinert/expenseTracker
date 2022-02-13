package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.model.Transaction;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;

public class ShowAllTransactions extends AbstractViewController implements Initializable {

    @FXML
    TableView allTransactionsTable;

    @FXML
    TableColumn ttc_Id = new TableColumn("ID");

    @FXML
    TableColumn ttc_Date = new TableColumn("Datum");

    @FXML
    TableColumn ttc_paymentMethod = new TableColumn("Zahlungsmethode");

    @FXML
    TableColumn ttc_receiver = new TableColumn("Empfänger");

    @FXML
    TableColumn ttc_category = new TableColumn("Kategorie");

    @FXML
    TableColumn ttc_amount = new TableColumn("Betrag");

    @FXML
    TableColumn ttc_notes = new TableColumn("Notizen");

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
        final ObservableList<Transaction> allTransactions = (ObservableList<Transaction>) new  DataController().selectAll(Transaction.class);

        ObservableList<Transaction> transactionObservableList = FXCollections.observableArrayList();
        transactionObservableList.add(new Transaction());


        prepareColumn(ttc_Id, "transaction_id");
        prepareColumn(ttc_Date, "formatedDate");
        prepareColumn(ttc_paymentMethod, "paymentMethodWrapper");
        prepareColumn(ttc_receiver, "receiverWrapper");
        prepareColumn(ttc_category, "categoryWrapper");
        prepareColumn(ttc_amount, "amount");
        prepareColumn(ttc_notes, "notes");

        Comparator<String> columnComparator =
                (String v1, String v2) -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                    try {
                        Date date1 = formatter.parse(v1);
                        Date date2 = formatter.parse(v2);
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return v1.toLowerCase().compareTo(
                            v2.toLowerCase());

                };

        ttc_Date.setComparator(columnComparator);


        //ttc_Date.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<Transaction, Date>>) event -> {
        //    Transaction transaction = event.getRowValue();
        //    transaction.setDate(event.getNewValue());
        //    transactionObservableList.add(new Transaction());
        //});

        allTransactionsTable.getColumns().addAll(ttc_Id, ttc_Date, ttc_paymentMethod, ttc_receiver, ttc_category, ttc_amount, ttc_notes );
        allTransactionsTable.setItems(allTransactions);
        allTransactionsTable.setVisible(true);

    }

    private void prepareColumn(TableColumn tc, String field){
        tc.setMinWidth(150);
        tc.setEditable(true);
        tc.setVisible(true);
        tc.setCellValueFactory(new PropertyValueFactory<>(field));
    }

}
