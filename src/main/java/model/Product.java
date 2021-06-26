package main.java.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Product {
    @DatabaseField( generatedId = true )
    private int product_id;

    @DatabaseField
    private String productName;

    @DatabaseField
    private double single_price;

    @DatabaseField
    private double amount;

    @DatabaseField
    private String notes;

    @ForeignCollectionField(eager = true)
    java.util.Collection<JoinTransactionProduct> joinTransactionProducts;

    public Product(){ }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSingle_price() {
        return single_price;
    }

    public void setSingle_price(double single_price) {
        this.single_price = single_price;
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
}
