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
import main.java.model.BudgetProgressElement;
import main.java.model.TransactionCategory;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class BudgetOverviewController extends AbstractViewController implements Initializable {

    @FXML
    ScrollBar scrollBarMonth;

    @FXML
    Pane mainPane = new Pane();

    @FXML
    Label labelMonth = new Label();

    ObservableList<Object> allCategories = null;

    @FXML
    ChoiceBox<String> budgetSelect;

    @FXML
    Spinner<Double> budgetSpinner = new Spinner<Double>(Integer.MIN_VALUE, Integer.MAX_VALUE, 1, 1);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupBudgetForm();
        initBudgetOverview();
    }

    @Override
    public Stage getStage() {
        return (Stage) this.scrollBarMonth.getScene().getWindow();
    }

    private String getCurrentMonth(){
        ZoneId timeZone = ZoneId.of("Europe/Berlin"); // TODO: intruduce changeable variable for time zone
        LocalDateTime currentMonthTime = LocalDateTime.ofInstant(new Date().toInstant(), timeZone);

        return YearMonth.from(currentMonthTime).toString();
    }

    private void setupBudgetForm(){
        SpinnerValueFactory<Double> budgetSpinnerValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MAX_VALUE*(-1), Double.MAX_VALUE);
        budgetSpinner.setEditable(true);

        budgetSpinnerValueFactory.setConverter(new StringConverter<Double>() {
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
        budgetSpinner.setValueFactory(budgetSpinnerValueFactory);
        budgetSpinner.valueProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observableValue, Double aDouble, Double t1) {

            }
        });
        budgetSpinnerValueFactory.setValue(0.0);

        ObservableList<String> olTransCategory = FXCollections.observableArrayList();
        DataController dc = new DataController();
        List<Object> CategoryList = dc.selectAll(TransactionCategory.class);
        for (Object item : CategoryList) {
            TransactionCategory thisItem = (TransactionCategory)item;
            olTransCategory.add(thisItem.getCategory());
        }
        budgetSelect.setItems(olTransCategory);
    }

    public void saveNewBudget() {
        DataController dc = new DataController();
        TransactionCategory category = (TransactionCategory) dc.findByIdentifier(TransactionCategory.class, "category", budgetSelect.getValue().toString());
        category.setBudget(budgetSpinner.getValue());

        dc.persist(category);
        AlertHandler.showInfoAlert("New Budget set.");


    }

    private void initBudgetOverview(){
        this.allCategories =  new  DataController().selectAll(TransactionCategory.class);
        mainPane = (Pane) scrollBarMonth.getParent();
        ObservableList<ProgressBar> progressList = FXCollections.observableArrayList();
        labelMonth.setText(getCurrentMonth());
        int offsetCounter = 0;
        try {
            for ( var category : this.allCategories) {

                TransactionCategory thisCategory = (TransactionCategory) category;
                if (thisCategory.getCategory_id() <= 5){ //only get expenditure categories
                    continue;
                }
                BudgetProgressElement bpe = new BudgetProgressElement(
                        thisCategory.getCategory(),
                        thisCategory.getBudget(),
                        thisCategory.getBudgetOfMonth(new Date()),
                        offsetCounter
                );

                bpe.addToPane(mainPane);

                offsetCounter += 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertHandler.showErrorAlert("unable to ");
        }
    }


}
