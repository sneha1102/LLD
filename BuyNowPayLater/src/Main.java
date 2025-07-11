import main.java.common.PaymentMethod;
import main.java.factory.PaymentServiceFactory;
import main.java.model.Order;
import main.java.model.User;
import main.java.service.BlackListManager;
import main.java.service.PaymentService;
import main.java.service.ReminderService;

import java.util.Calendar;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // factory design patter
        // observer design pattern
        // strategy design pattern
        // ReminderService
        ReminderService reminderService = new ReminderService();

        // Create Users
        User john = new User("U001", "John Doe", 1000.0);
        User jane = new User("U002", "Jane Smith", 1500.0);

        reminderService.addObserver(john);
        reminderService.addObserver(jane);

        // Factory Instance
        PaymentService payLaterService = PaymentServiceFactory.getPaymentService(PaymentMethod.PAY_LATER);

        // Create Orders
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 4); // 4 days remaining
        Order order1 = new Order("O001", 400.0, PaymentMethod.PAY_LATER,  john.getUseriD(), calendar.getTime());

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 10); // 10 days remaining
        Order order2 = new Order("O002", 600.0, PaymentMethod.PAY_LATER, jane.getUseriD(), calendar.getTime());

        // Pay Later Orders
        payLaterService.pay(john, order1);
        payLaterService.pay(jane, order2);

        BlackListManager blackListManager = new BlackListManager();
        blackListManager.handlePaymentDefault(john);
        blackListManager.handlePaymentDefault(john);
        blackListManager.handlePaymentDefault(john);


        // Add Orders for Reminder
        reminderService.addOrderForReminder(order1);
        reminderService.addOrderForReminder(order2);

        // Trigger reminders for users
        System.out.println("Sending reminders...");
        reminderService.notifyObservers();

    }
}