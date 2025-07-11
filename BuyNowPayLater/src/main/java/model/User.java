package main.java.model;

public class User {
    private String useriD;

    private String name;
    private CreditAccount creditAccount;
    private int paymentDefault;
    private boolean isBlackListed;


    public User(String useriD, String name, double initialCreditLimit) {
        this.useriD = useriD;
        this.name = name;
        this.creditAccount = new CreditAccount(initialCreditLimit);
        this.paymentDefault = 0;
        this.isBlackListed = false;
    }

    public String getName() {
        return name;
    }

    public String getUseriD() {
        return useriD;
    }
    public boolean isBlacklisted() {
        return isBlackListed;
    }

    public void markBlacklisted() {
        this.isBlackListed = true;
    }

    public CreditAccount getCreditAccount() {
        return creditAccount;
    }

    public int getPaymentDefaults() {
        return paymentDefault;
    }

    public void increementPaymentDefaults() {
        this.paymentDefault++;
        if (this.paymentDefault >= 3) {
            markBlacklisted();
        }
    }
    public void receiveReminder(Order order){
        System.out.println("Reminder: Dear, your Pay Later order (ID: " + order.getOrderId() +
                ", amount: " + order.getOrderAmount() + ") is due in less than 5 days on " + order.getDueDate());
    }
}
