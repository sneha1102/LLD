import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Delivery implements DeliverySubject {
    private String deliveryId;
    private String source;
    private String destination;
    private double distance;
    private double weight;
    private DeliveryType deliveryType;
    private DeliveryStatus status;
    private double cost;
    private LocalDateTime createdAt;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<DeliveryObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<DeliveryObserver> observers) {
        this.observers = observers;
    }

    private List<DeliveryObserver> observers = new ArrayList<>();

    // Constructors, Getters, Setters

    public void setStatus(DeliveryStatus status) {
        this.status = status;
        notifyObservers(); // Notify all observers on status change
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    @Override
    public void addObserver(DeliveryObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(DeliveryObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (DeliveryObserver observer : observers) {
            observer.onDeliveryStatusChanged(this);
        }
    }

}
