public class EmailNotifier implements DeliveryObserver {
    @Override
    public void onDeliveryStatusChanged(Delivery delivery) {
        System.out.println("[EMAIL] Delivery " + delivery.getDeliveryId() +
                " status changed to: " + delivery.getStatus());
    }
}
