package main.java.service;

import main.java.model.Order;
import main.java.model.User;

public interface PaymentService {
    boolean pay(User user, Order order);

}
