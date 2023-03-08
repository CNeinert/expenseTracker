package main.java.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import main.java.controller.DataController;

@DatabaseTable
public class Receiver implements Persistable{
    @DatabaseField(generatedId = true)
    private int receiverId;
    @DatabaseField
    private String receiverName;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "category_id")
    private TransactionCategory transactionCategory;

    public Receiver(String receiver){
        this.setReceiverName(receiver);
    }

    public Receiver (){ }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Override
    public Boolean isEmpty() {
        return this.getReceiverName().isEmpty();
    }

    @Override
    public Class<?> getThisClass() {
        return this.getClass();
    }

    public TransactionCategory getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategory transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public static Receiver loadFromDatabase(String receiverNameName){
        DataController dc = new DataController();
        return (Receiver) dc.findByIdentifier(Receiver.class, "receiver_name", receiverNameName );
    }
}
