package main.java.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class TransactionCategory {
    @DatabaseField( generatedId = true )
    private int category_id;

    @DatabaseField
    private String category;


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

}
