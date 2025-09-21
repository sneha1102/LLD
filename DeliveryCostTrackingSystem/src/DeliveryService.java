import java.util.*;

public class DeliveryService {
    private Map<String, Delivery> deliveries = new HashMap<>();

    public Delivery createDelivery(String source, String destination, double distance, double weight, DeliveryType type, List<DeliveryObserver> observers) {
        DeliveryBuilder builder = new DeliveryBuilder()
                .setSource(source)
                .setDestination(destination)
                .setDistance(distance)
                .setWeight(weight)
                .setDeliveryType(type);

        for (DeliveryObserver observer : observers) {
            builder.addObserver(observer);
        }

        Delivery delivery = builder.build();

        CostStrategy strategy = CostStrategyFactory.getStrategy(type);
        double cost = strategy.calculateCost(delivery);
        delivery.setCost(cost);

        deliveries.put(delivery.getDeliveryId(), delivery);
        return delivery;
    }

    public void updateStatus(String deliveryId, DeliveryStatus newStatus) {
        Delivery delivery = deliveries.get(deliveryId);
        if (delivery != null) {
            delivery.setStatus(newStatus); // Will trigger notifyObservers()
        }
    }

    public Delivery getDelivery(String deliveryId) {
        return deliveries.get(deliveryId);
    }

    public List<Delivery> getAllDeliveries() {
        return new ArrayList<>(deliveries.values());
    }
}
