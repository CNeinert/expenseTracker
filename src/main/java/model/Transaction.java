package main.java.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
                       double amountPayed, String notes) {
        this.setDate(date);
        this.setPaymentMethod(paymentMethod);
        this.setReceiver(receiver);
        this.setTransactionCategory(transactionCategory);
        this.setAmount(amountPayed);
        this.setNotes(notes);

    }

    public Transaction(){}

    public Transaction(Map<String, String> map) throws ParseException {
        this.setDateFromString(map.get("date"));//todo ...
        this.setPaymentMethod(PaymentMethod.loadFromDatabase(map.get("paymentMethod")));
        this.setReceiver(Receiver.loadFromDatabase(map.get("receiver")));
        this.setTransactionCategory(TransactionCategory.loadFromDatabase(map.get("transactionCategory")));
        this.setAmount(Double.parseDouble(map.get("date")));
        this.setNotes(notes);
    }

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

    public void setDateFromString(String date) throws ParseException {
        this.date = new SimpleDateFormat("dd.mm.yyyy").parse(date);
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

    private String paymentMethodWrapper;

    private String receiverWrapper;

    private String categoryWrapper;

    private String formatedDate;

    public String getPaymentMethodWrapper() {
        return this.getPaymentMethod().getPaymentMethod();
    }

    public String getReceiverWrapper(){
        return this.getReceiver().getReceiverName();
    }

    public String getCategoryWrapper(){
        return this.getTransactionCategory().getCategory();
    }

    public String getFormatedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(this.date);
    }
}
