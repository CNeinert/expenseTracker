package main.java.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashSet;
import java.util.Set;

@DatabaseTable
public class Category {
    @DatabaseField( generatedId = true )
    private int category_id;
    @DatabaseField
    private String category;
    @DatabaseField
    private String related_to; //Category for Transactions, Stocks or other

    @ForeignCollectionField(eager = true)
    java.util.Collection<Transaction> transactions;

    public Category() {

    }

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

    public String getRelated_to() {
        return related_to;
    }

    public void setRelated_to(String related_to) {
        this.related_to = related_to;
    }

}
