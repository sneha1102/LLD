import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeliveryObserver emailNotifier = new EmailNotifier();
        DeliveryObserver smsNotifier = new SMSNotifier();
        DeliveryObserver dashboardUpdater = new DashboardUpdater();

        List<DeliveryObserver> observers = Arrays.asList(emailNotifier, smsNotifier, dashboardUpdater);

        DeliveryService service = new DeliveryService();

        Delivery delivery = service.createDelivery(
                "Delhi", "Chandigarh",
                250.0, 8.0,
                DeliveryType.EXPRESS,
                observers
        );

        System.out.println("Created delivery with cost: ₹" + delivery.getCost());

        System.out.println("\n--- Changing status to IN_TRANSIT ---");
        service.updateStatus(delivery.getDeliveryId(), DeliveryStatus.IN_TRANSIT);

        System.out.println("\n--- Changing status to DELIVERED ---");
        service.updateStatus(delivery.getDeliveryId(), DeliveryStatus.DELIVERED);
    }
}
