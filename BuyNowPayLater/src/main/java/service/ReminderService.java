package main.java.service;

import main.java.model.Order;
import main.java.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderService implements Observable{
    private List<User> observers = new ArrayList<>();
    private List<Order> payLaterOrderList = new ArrayList<>();

    @Override
    public void addObserver(User user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(User user) {
        observers.remove(user);
    }

    @Override
    public void notifyObservers() {
        Date now = new Date();
        // Notify observers for Pay Later orders nearing due date
        for(Order order: payLaterOrderList){
            // Calculate days remaining
            long daysUntilDue = now.getDay() - order.getDueDate().getDay();
            // Reminder threshold (e.g., 5 days)
            if(daysUntilDue <= 5 && daysUntilDue > 0){
                for(User user: observers){
                    String userId = order.getUserId();
                    if(userId.equals(user.getUseriD())){
                        user.receiveReminder(order);
                    }
                }
            }
        }
    }

    public void addOrderForReminder(Order order){
        payLaterOrderList.add(order);
    }

    public void removeOrderForReminder(Order order){
        payLaterOrderList.remove(order);
    }
}
