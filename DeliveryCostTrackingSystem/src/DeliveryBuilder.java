import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeliveryBuilder {
    private String source;
    private String destination;
    private double distance;
    private double weight;
    private DeliveryType type;

    private List<DeliveryObserver> observers = new ArrayList<>();

    public DeliveryBuilder setSource(String source) {
        this.source = source;
        return this;
    }

    public DeliveryBuilder setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public DeliveryBuilder setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public DeliveryBuilder setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public DeliveryBuilder setDeliveryType(DeliveryType type) {
        this.type = type;
        return this;
    }

    public DeliveryBuilder addObserver(DeliveryObserver observer) {
        this.observers.add(observer);
        return this;
    }

    public Delivery build() {
        Delivery delivery = new Delivery();
        delivery.setDeliveryId(UUID.randomUUID().toString());
        delivery.setSource(source);
        delivery.setDestination(destination);
        delivery.setDistance(distance);
        delivery.setWeight(weight);
        delivery.setDeliveryType(type);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setCreatedAt(LocalDateTime.now());
        for (DeliveryObserver observer : observers) {
            delivery.addObserver(observer);
        }
        return delivery;
    }
}
