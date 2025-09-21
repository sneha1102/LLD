public class CostStrategyFactory {
    public static CostStrategy getStrategy(DeliveryType type) {
        switch (type) {
            case EXPRESS: return new ExpressCostStrategy();
            case SAME_DAY: return new SameDayCostStrategy();
            default: return new StandardCostStrategy();
        }
    }
}