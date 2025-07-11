package main.java.service;

import main.java.model.Order;
import main.java.model.User;

public class CashPayment implements PaymentService{
    @Override
    public boolean pay(User user, Order order){
        order.markPaid();
        System.out.println("Order placed and paid in cash.");
        return true;
    }
}
