public interface DeliverySubject {
    void addObserver(DeliveryObserver observer);
    void removeObserver(DeliveryObserver observer);
    void notifyObservers();
}
