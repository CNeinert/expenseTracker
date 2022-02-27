package main.java.model;


import java.util.HashMap;
import java.util.Map;

public class CsvHandler {

    public String toCsvWithNames(Transaction transaction){
        String stringWithIds = String.valueOf(transaction.getTransaction_id());
        stringWithIds += ";"+transaction.getDate();
        stringWithIds += ";"+transaction.getPaymentMethod().getPaymentMethod();
        stringWithIds += ";"+transaction.getReceiver().getReceiverName();
        stringWithIds += ";"+transaction.getTransactionCategory().getCategory();
        stringWithIds += ";"+transaction.getAmount();
        stringWithIds += ";"+transaction.getNotes();
        stringWithIds += ";";

        return stringWithIds;
    }

    public String toCsvWithIds(Transaction transaction){
        String stringWithIds = String.valueOf(transaction.getTransaction_id());
        stringWithIds += ";"+transaction.getDate();
        stringWithIds += ";"+transaction.getPaymentMethod().getPaymentMethodId();
        stringWithIds += ";"+transaction.getReceiver().getReceiverId();
        stringWithIds += ";"+transaction.getTransactionCategory().getCategory_id();
        stringWithIds += ";"+transaction.getAmount();
        stringWithIds += ";"+transaction.getNotes();
        stringWithIds += ";";

        return stringWithIds;
    }

    public Transaction convertStringToTransaction(String csvTransaction){
        Transaction transaction = new Transaction();

        Map <String, String> csvDataAsMap = new HashMap<>();
        String[] csvData = csvTransaction.split(";");
        String[] header = getCsvHeader().split(";");
        for (int i = 0; i < header.length; i++) {
            csvDataAsMap.put(header[i], csvData[i]);
        }

        // todo
        return transaction;
    }

    public String getCsvHeader(){
        return "transaction_id;date;payment_method;receiver;category;amount;notes;";
    }
}
