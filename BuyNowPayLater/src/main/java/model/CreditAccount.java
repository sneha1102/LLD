package main.java.model;

import main.java.service.ReminderService;

import java.util.ArrayList;
import java.util.List;

public class CreditAccount {
    private double creditLimit;
    private double currentDues;
    private List<Order> overdueOrders;


    public CreditAccount(double creditLimit) {
        this.creditLimit = creditLimit;
        this.currentDues = 0.0;
        this.overdueOrders = new ArrayList<>();
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getCurrentDues() {
        return currentDues;
    }
    public boolean canUseCredit(double amount){
        return amount <= (creditLimit - currentDues);
    }

    public void addDues(double amt, Order order){
        this.currentDues += amt;
        this.overdueOrders.add(order);
    }

    public void clearDues(double amt, Order order){
        this.currentDues -= amt;
        this.overdueOrders.remove(order);;
    }
    public List<Order> getOverdueOrders() {
        return overdueOrders;
    }
}
