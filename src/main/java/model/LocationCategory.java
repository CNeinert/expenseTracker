package main.java.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class LocationCategory {
    @DatabaseField( generatedId = true )
    private int category_id;
    @DatabaseField
    private String locationCategory;
    @DatabaseField
    private String related_to; //Category for Transactions, Stocks or other

    @ForeignCollectionField(eager = true)
    java.util.Collection<Location> location;

    public LocationCategory() {

    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getLocationCategory() {
        return locationCategory;
    }

    public void setLocationCategory(String locationCategory) {
        this.locationCategory = locationCategory;
    }

    public String getRelated_to() {
        return related_to;
    }

    public void setRelated_to(String related_to) {
        this.related_to = related_to;
    }
}
