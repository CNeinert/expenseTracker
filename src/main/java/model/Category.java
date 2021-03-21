package main.java.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int category_id;
    @Column(name = "category")
    private String category;
    @Column(name = "related_to")
    private String related_to; //Category for Transactions, Stocks or other

    @OneToMany(targetEntity=Transaction.class,mappedBy="category",cascade={CascadeType.ALL},orphanRemoval=false)
    Set<Transaction> transactions = new HashSet<Transaction>();

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
