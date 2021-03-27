package main.java.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class JoinTransactionProduct {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Transaction transaction;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Product product;

    public JoinTransactionProduct() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
