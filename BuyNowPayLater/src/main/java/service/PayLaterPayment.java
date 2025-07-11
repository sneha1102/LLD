package main.java.service;

import main.java.model.CreditAccount;
import main.java.model.Order;
import main.java.model.User;

public class PayLaterPayment implements PaymentService{
    @Override
    public boolean pay(User user, Order order) {
        CreditAccount account = user.getCreditAccount();
        if(user.isBlacklisted()){
            System.out.println("User is blacklisted!");
            return false;
        }
        if(account.canUseCredit(order.getOrderAmount())){
            account.addDues(order.getOrderAmount(), order);
            System.out.println("Order placed using Pay Later. Due Date: " + order.getDueDate());
            return true;
        }else{
            System.out.println("Not enough credit to place order.");
            return false;
        }
    }
}
