package main.java.splitstrategy;

public class SplitStrategyFactory {
    public static SplitStrategy getSplitStrategy(SplitType splitType){
        return switch(splitType){
            case EQUAL -> new EqualSplitStrategy();
            case EXACT -> new ExactSplitStrategy();
            case PERCENTAGE -> new PercentageSplitStrategy();
            default -> throw new IllegalArgumentException("Unsupported SplitType: " + splitType);
        };
    }
}
