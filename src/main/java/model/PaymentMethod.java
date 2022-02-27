package main.java.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import main.java.controller.DataController;

@DatabaseTable
public class PaymentMethod implements Persistable {

    @DatabaseField( generatedId = true )
    private int paymentMethodId;

    @DatabaseField
    private String paymentMethod;

    public PaymentMethod(String paymentMethod){
        setPaymentMethod(paymentMethod);
    }

    public PaymentMethod(){

    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    @Override
    public Boolean isEmpty() {
        return this.getPaymentMethod().isEmpty();
    }

    @Override
    public Class<?> getThisClass() {
        return this.getClass();
    }

    public static PaymentMethod loadFromDatabase(String paymentMethod){
        DataController dc = new DataController();
        return (PaymentMethod) dc.findByIdentifier(PaymentMethod.class, "payment_method", paymentMethod );
    }
}
