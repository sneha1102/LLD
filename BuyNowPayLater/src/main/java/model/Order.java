package main.java.model;

import main.java.common.OrderStatus;
import main.java.common.PaymentMethod;

import java.util.Calendar;
import java.util.Date;

public class Order {


    private String orderId;
    private double amount;
    private Date dueDate;
    private String userId;
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;

    public Order(String orderId, double amount, PaymentMethod paymentMethod, String userId, Date dueDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.dueDate = null;
        this.paymentMethod = paymentMethod;
        this.orderStatus = OrderStatus.PLACED;
        if(paymentMethod == PaymentMethod.PAY_LATER){
            this.dueDate = dueDate;
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getOrderAmount() {
        return amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public void markPaid(){
        this.orderStatus = OrderStatus.PAID;
    }

    public String getOrderId() {
        return orderId;
    }
}
