public class ExpressCostStrategy implements CostStrategy {
    public double calculateCost(Delivery delivery) {
        return (5.0 + delivery.getDistance() * 2.0 + delivery.getWeight() * 1.5) * 1.5;
    }
}