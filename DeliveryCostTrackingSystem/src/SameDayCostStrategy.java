public class SameDayCostStrategy implements CostStrategy {
    public double calculateCost(Delivery delivery) {
        return (5.0 + delivery.getDistance() * 2.0 + delivery.getWeight() * 1.5) * 2.0;
    }
}