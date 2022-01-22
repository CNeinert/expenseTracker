package main.java.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Transaction implements Persistable {

    @DatabaseField( generatedId = true )
    private int transaction_id;

    @DatabaseField
    private Date date;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "payment_method_id")
    private PaymentMethod paymentMethod;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "receiver_id")
    private Receiver receiver;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "category_id")
    private TransactionCategory transactionCategory;

    @DatabaseField
    private double amount;

    @DatabaseField
    private String notes;

    public Transaction(Date date, PaymentMethod paymentMethod,Receiver receiver , TransactionCategory transactionCategory,
                       double amountPayed, double amountReceived, String notes) {
        this.setDate(date);

    }


    public Transaction(){}

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public TransactionCategory getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategory transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public void setCorrectValue(Boolean isIncome){
        if (isIncome){
            if (this.getAmount() < 0){
                this.setAmount(this.getAmount()*(-1));
            }
        }else{
            if (this.getAmount() > 0){
                this.setAmount(this.getAmount()*(-1));
            }
        }
    }

    @Override
    public Boolean isEmpty() {
        return this.getPaymentMethod().isEmpty() || this.getTransactionCategory().isEmpty() || this.getReceiver().isEmpty();
    }

    @Override
    public Class<?> getThisClass() {
        return  this.getClass();
    }
}
