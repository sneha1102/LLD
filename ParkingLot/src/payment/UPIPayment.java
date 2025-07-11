package payment;

public class UPIPayment implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        System.out.println("UPI payment processed of amount " + amount);
    }
}
