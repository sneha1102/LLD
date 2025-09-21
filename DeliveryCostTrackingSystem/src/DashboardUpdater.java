public class DashboardUpdater implements DeliveryObserver {
    @Override
    public void onDeliveryStatusChanged(Delivery delivery) {
        System.out.println("[DASHBOARD] Updated delivery " + delivery.getDeliveryId() +
                " status to: " + delivery.getStatus());
    }
}