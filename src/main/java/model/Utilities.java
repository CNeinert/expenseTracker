package main.java.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Utilities implements Persistable{
    @DatabaseField(generatedId = true)
    private int utilitiesId;

    @DatabaseField
    private String utilitiesName;

    @DatabaseField
    private int consumption;

    public Utilities(String receiver, int consumption){
        this.setUtilitiesName(receiver);
        this.setConsumption(consumption);
    }

    public Utilities(){ }

    public int getUtilitiesId() {
        return utilitiesId;
    }

    public void setUtilitiesId(int utilitiesId) {
        this.utilitiesId = utilitiesId;
    }

    public String getUtilitiesName() {
        return utilitiesName;
    }

    public void setUtilitiesName(String utilitiesName) {
        this.utilitiesName = utilitiesName;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    @Override
    public Boolean isEmpty() {
        return this.getUtilitiesName().isEmpty();
    }

    @Override
    public Class<?> getThisClass() {
        return this.getClass();
    }
}
