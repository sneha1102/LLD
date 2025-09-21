public class SMSNotifier implements DeliveryObserver {
    @Override
    public void onDeliveryStatusChanged(Delivery delivery) {
        System.out.println("[SMS] Delivery " + delivery.getDeliveryId() +
                " status changed to: " + delivery.getStatus());
    }
}