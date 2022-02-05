package main.java.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.collections.ObservableList;
import main.java.controller.DataController;

import java.time.*;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@DatabaseTable
public class TransactionCategory implements Persistable {
    @DatabaseField( generatedId = true )
    private int category_id;

    @DatabaseField
    private String category;

    @DatabaseField
    private double budget;


    public TransactionCategory(String category) {
        this.setCategory(category);
    }

    public TransactionCategory(){}

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public Boolean isEmpty() {
        return this.getCategory().isEmpty();
    }

    @Override
    public Class<?> getThisClass() {
        return this.getClass();
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudgetOfMonth(Date date) throws SQLException {

        DataController dc = new DataController();
        Dao<Transaction, String> dao = DaoManager.createDao(dc.getConnectionSource(), Transaction.class);
        List<Transaction> transactionsOfSelectedBudget = (List<Transaction>) dc.findAllByIdentifier(Transaction.class, "category_id", ""+this.getCategory_id());
        if (transactionsOfSelectedBudget == null){
            return 0.0;
        }
        return transactionsOfSelectedBudget.stream()
                .filter(
                        transaction -> inMonth(transaction.getDate(), date)
                )
                .mapToDouble(
                        Transaction::getAmount
                )
                .sum();

    }

    private boolean inMonth(Date dateOfObject, Date dateOfTimeInterval) {
        ZoneId timeZone = ZoneId.of("Europe/Berlin"); // TODO: intruduce changeable variable for time zone
        LocalDateTime givenLocalDateTimeObject = LocalDateTime.ofInstant(dateOfObject.toInstant(), timeZone);
        LocalDateTime givenLocalDateTimeInterval = LocalDateTime.ofInstant(dateOfTimeInterval.toInstant(), timeZone);

        YearMonth currentMonth = YearMonth.from(givenLocalDateTimeInterval);

        return currentMonth.equals(YearMonth.from(givenLocalDateTimeObject));
    }
}
