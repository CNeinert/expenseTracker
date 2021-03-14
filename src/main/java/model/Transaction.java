package main.java.model;

public class Transaction {

    private String date;
    private String location, product_name;
    private Category category;
    private double single_price, amount, total_price;

    public Transaction(String date, String location, String product_name, Category category, double single_price, double amount, double total_price) {
        this.setDate(date);
        this.setLocation(location);
        this.setProduct_name(product_name);
        this.setCategory(category);
        this.setSingle_price(single_price);
        this.setAmount(amount);
        this.setTotal_price(total_price);
    }

    public Transaction(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }


}
