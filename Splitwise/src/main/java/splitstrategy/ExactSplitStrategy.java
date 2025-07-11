package main.java.splitstrategy;

import main.java.Split;
import main.java.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExactSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplits(Map<User, Double> splits, double totalAmount) {
        // find total sum of amounts in splits
        double sum = splits.values().stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(sum - totalAmount) > 1e-6) {
            throw new IllegalArgumentException("Exact split amounts must sum up to total amount.");
        }
        List<Split> splitData = new ArrayList<>();
        for(Map.Entry<User, Double> split: splits.entrySet()){
            splitData.add(new Split(split.getKey(), split.getValue()));
        }
        return splitData;
    }
}
