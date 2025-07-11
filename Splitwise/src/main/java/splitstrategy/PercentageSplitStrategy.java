package main.java.splitstrategy;

import main.java.Split;
import main.java.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplits(Map<User, Double> splits, double totalAmount) {
        // find total sum of amounts in splits
        double sum = splits.values().stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(sum - 100.0) > 1e-6) {
            throw new IllegalArgumentException("Total percentage must be 100.");
        }
        List<Split> splitData = new ArrayList<>();
        for(Map.Entry<User, Double> split: splits.entrySet()){
            double percentage = split.getValue();
            double amt = (totalAmount * percentage) / 100.0;
            splitData.add(new Split(split.getKey(), amt));
        }
        return splitData;
    }
}
