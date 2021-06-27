package main.java.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class PaymentMethod {

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
}
