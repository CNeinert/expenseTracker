package main.java.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Receiver implements Persistable{
    @DatabaseField(generatedId = true)
    private int receiverId;
    @DatabaseField
    private String receiverName;

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
}
