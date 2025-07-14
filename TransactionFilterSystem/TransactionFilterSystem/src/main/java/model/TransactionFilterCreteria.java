package main.java.model;

import java.util.Date;

public class TransactionFilterCreteria {
    private String customerId;
    private Date startDate;
    private Date endDate;
    private int minAmount;
    private int maxAmount;

    public TransactionFilterCreteria(Builder builder) {
        this.customerId = builder.customerId;
        this.startDate = builder.startDate;
        this.endDate =builder. endDate;
        this.minAmount = builder.minAmount;
        this.maxAmount = builder.maxAmount;
    }


    public String getCustomerId() {
        return customerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }


    public int getMinAmount() {
        return minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public static class Builder{
        private String customerId;
        private Date startDate;
        private Date endDate;
        private int minAmount;
        private int maxAmount;


        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setMinAmount(int minAmount) {
            this.minAmount = minAmount;
            return this;
        }

        public Builder setMaxAmount(int maxAmount) {
            this.maxAmount = maxAmount;
            return this;
        }

        public TransactionFilterCreteria build(){
            return new TransactionFilterCreteria(this);
        }
    }
}
