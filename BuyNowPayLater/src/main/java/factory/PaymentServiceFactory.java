package main.java.factory;

import main.java.common.PaymentMethod;
import main.java.service.CashPayment;
import main.java.service.PayLaterPayment;
import main.java.service.PaymentService;

public class PaymentServiceFactory {
    public static PaymentService getPaymentService(PaymentMethod paymentMethod){
        switch (paymentMethod){
            case CASH :
                return new CashPayment();
            case PAY_LATER :
                return new PayLaterPayment();
            default :
                throw new IllegalArgumentException("Invalid Payment Method");
        }
    }
}
