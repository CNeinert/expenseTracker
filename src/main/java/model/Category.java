package main.java.model;

public class Category {

    private int category_id;
    private String category;
    private String related_to; //Category for Transactions, Stocks or other

    public Category(int category_id) {
        this.category_id = category_id;
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
