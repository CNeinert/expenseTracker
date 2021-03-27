package main.java.model;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Transaction {

    @DatabaseField( generatedId = true )
    private int transaction_id;

    @DatabaseField
    private Date date;

    @DatabaseField
    private String product_name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "category_id")
    private TransactionCategory transactionCategory;

    @ForeignCollectionField(eager = true)
    java.util.Collection<JoinTransactionProduct> joinTransactionProducts;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "location_id")
    private Location location;

    @DatabaseField
    private double total_price;

    public Transaction(Date date, String product_name, TransactionCategory transactionCategory, double total_price) {
        this.setDate(date);
        this.setProduct_name(product_name);
        this.setCategory(transactionCategory);
        this.setTotal_price(total_price);
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public TransactionCategory getCategory() {
        return transactionCategory;
    }

    public void setCategory(TransactionCategory transactionCategory) {
        this.transactionCategory = transactionCategory;
    }



    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }


}
