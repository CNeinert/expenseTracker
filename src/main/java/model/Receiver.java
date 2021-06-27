package main.java.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Receiver {
    @DatabaseField(generatedId = true)
    private int recieverId;
    @DatabaseField
    private String recieverName;

    public Receiver(String receiver){
        this.setRecieverName(receiver);
    }

    public Receiver (){ }

    public int getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

}
