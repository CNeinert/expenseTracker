package main.java.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import main.java.model.PaymentMethod;
import main.java.model.Receiver;
import main.java.model.Transaction;
import main.java.model.TransactionCategory;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class IndexController extends AbstractViewController implements Initializable {

    @FXML
    Pane MainPane = new Pane();

    @FXML
    ProgressBar statusbar_001 = new ProgressBar();


    @FXML
    DatePicker transDate = new DatePicker();

    @FXML
    TextField location = new TextField();

    @FXML
    ChoiceBox transactionCategorieField;

    @FXML
    ChoiceBox paymentMethodField;

    @FXML
    private TextField tx_newPaymentMethod = new TextField();

    @FXML
    private TextField tx_newCategory = new TextField();

    @FXML
    private RadioButton radioMoneyOutcome = new RadioButton();

    @FXML
    private RadioButton radioMoneyIncome = new RadioButton();

    @FXML
    private final ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private ComboBox<String> tx_receiver = new ComboBox<String>();

    @FXML
    private Spinner<Double> amount = new Spinner<>(Double.MIN_VALUE, Double.MAX_VALUE, 0.00, 0.01);
    private final DecimalFormat df = new DecimalFormat("#.##");

    @FXML
    private TextArea txf_notes = new TextArea();
    /*
     * @FXML private TableColumn<Program, String> programName;
     *
     * @FXML private TableColumn<Program, String> (SimpleStringProperty)
     * programVersion;
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        initChoiceBoxes();

        this.radioMoneyOutcome.setToggleGroup(toggleGroup);
        this.radioMoneyOutcome.setSelected(true);
        this.radioMoneyIncome.setToggleGroup(toggleGroup);

        transDate.setOnAction(e  -> {
            LocalDate date = transDate.getValue();
            System.err.println("Selected date: " + date);
        });

        amount.setEditable(true);
        SpinnerValueFactory<Double> amountValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE);

        amountValueFactory.setConverter(new StringConverter<Double>() {
            private final DecimalFormat df = new DecimalFormat("##.##");

            @Override
            public String toString(Double aDouble) {
                // If the specified value is null, return a zero-length String
                return this.df.format(aDouble);
            }

            @Override
            public Double fromString(String s) {
                try {
                    // If the specified value is null or zero-length, return null
                    if (s == null) {
                        return null;
                    }
                    s = s.trim();
                    if (s.length() < 1) {
                        return null;
                    }
                    // Perform the requested parsing
                    return df.parse(s).doubleValue();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        amount.setValueFactory(amountValueFactory);
        amount.valueProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observableValue, Double aDouble, Double t1) {

                //System.out.println("observableValue: " +observableValue.getValue());
                //System.out.println("aDouble: " +aDouble.toString().toString());
                //System.out.println("t1: " +t1.toString());
                //System.out.println("Amount: " + amount.getValue().toString());
            }
        });

        amountValueFactory.setValue(0.01);
    }


    private void initChoiceBoxes(){
        ObservableList<String> olTransCategory = FXCollections.observableArrayList();
        ObservableList<String> olPaymentMethod = FXCollections.observableArrayList();
        ObservableList<String> olReceivers = FXCollections.observableArrayList();

        DataController dc = new DataController();

        List<Object> CategoryList = dc.selectAll(TransactionCategory.class);
        List<Object> PaymentMethodList = dc.selectAll(PaymentMethod.class);
        List<Object> ReceiversList = dc.selectAll(Receiver.class);

        for (Object item : CategoryList) {
            TransactionCategory thisItem = (TransactionCategory)item;
            olTransCategory.add(thisItem.getCategory());
        }
        for (Object item : PaymentMethodList) {
            PaymentMethod thisItem = (PaymentMethod)item;
            olPaymentMethod.add(thisItem.getPaymentMethod());
        }
        for (Object item : ReceiversList) {
            Receiver thisItem = (Receiver) item;
            olReceivers.add(thisItem.getReceiverName());
        }

        transactionCategorieField.setItems(olTransCategory);
        paymentMethodField.setItems(olPaymentMethod);
        tx_receiver.setItems(olReceivers);

    }

    private void saveNewTransaction(){
        System.out.print("Saving...");

        try {
            setStatusbar_001(0.0);
            DataController dc = new DataController();
            Date date = Date.from(transDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            setStatusbar_001(0.2);
            var paymentMethods = dc.findByIdentifier(PaymentMethod.class, "paymentMethod", paymentMethodField.getValue().toString());
            System.out.println(paymentMethods);
            var category = dc.findByIdentifier(TransactionCategory.class, "category", transactionCategorieField.getValue().toString());
            var receiver = dc.findByIdentifier(Receiver.class, "receiverName", tx_receiver.getValue().toString());
            setStatusbar_001(0.4);

            Transaction transaction = new Transaction();
            transaction.setDate(date);
            transaction.setPaymentMethod((PaymentMethod) paymentMethods);
            transaction.setTransactionCategory((TransactionCategory) category);
            setStatusbar_001(0.6);
            if (receiver == null){
                Receiver newreceiver = new Receiver(tx_receiver.getValue().toString());
                dc.persist(newreceiver);
                receiver = newreceiver;
            }

            transaction.setReceiver((Receiver) receiver);
            transaction.setAmount(amount.getValue());
            transaction.setCorrectValue(toggleGroup.getSelectedToggle().toString().equals("radioMoneyIncome"));
            transaction.setNotes(txf_notes.getText());
            setStatusbar_001(0.8);
            dc.persist(transaction);
            setStatusbar_001(1.0);
            System.out.println("done.");
            resetView();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,  "Failed to save transaction");
            alert.show();
            e.printStackTrace();
            System.out.println("---- FAILED! ----");
        }


    }

    private void saveNewPaymentMethod(){
        DataController dc = new DataController();
        PaymentMethod newPayment = new PaymentMethod();
        newPayment.setPaymentMethod(tx_newPaymentMethod.getText());
        dc.persist(newPayment);
        initChoiceBoxes();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "New Pament Method: '" +tx_newPaymentMethod.getText()+"' created");
        alert.show();
    }

    private void saveNewCategory(){
        DataController dc = new DataController();
        TransactionCategory newCategory = new TransactionCategory();
        newCategory.setCategory(tx_newCategory.getText());
        dc.persist(newCategory);
        initChoiceBoxes();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "New Category: '"+newCategory.getCategory().toString()+"' created");
        alert.show();
    }

    public void resetView(){
        tx_newPaymentMethod.setText("");
        tx_newCategory.setText("");
        radioMoneyOutcome.setSelected(true);
        radioMoneyIncome.setSelected(false);
        txf_notes.setText("");
        setStatusbar_001(0.0);
    }

    private void setStatusbar_001(double value){
        this.statusbar_001.setProgress(value);
    }

    public Stage getStage(){
        return (Stage) this.transDate.getScene().getWindow();
    }


}
