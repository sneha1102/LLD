package main.java.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {
    private String transactionId;
    private String customerId;
    private Date timeStamp;
    private int amount;

    public Transaction(String transactionId, String customerId, Date timeStamp, int amount) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.timeStamp = timeStamp;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
