package main.java.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "Transactions" )
public class Transaction {



    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "location")
    private String location;

    @Column(name = "product_name")
    private String product_name;

    @ManyToOne(targetEntity=Category.class)
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name = "single_price")
    private double single_price;

    @Column(name = "amount")
    private double amount;

    @Column(name = "total_price")
    private double total_price;

    public Transaction(Date date, String location, String product_name, Category category, double single_price, double amount, double total_price) {
        this.setDate(date);
        this.setLocation(location);
        this.setProduct_name(product_name);
        this.setCategory(category);
        this.setSingle_price(single_price);
        this.setAmount(amount);
        this.setTotal_price(total_price);
    }

    public Transaction(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
