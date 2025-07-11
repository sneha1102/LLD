package main.java.service;

import main.java.model.User;

public class BlackListManager {
    public void handlePaymentDefault(User user){
        user.increementPaymentDefaults();
        if(user.isBlacklisted()){
            System.out.printf("User: %s has been blacklisted due to payment defaults! \n", user.getName());
        }
    }
}
