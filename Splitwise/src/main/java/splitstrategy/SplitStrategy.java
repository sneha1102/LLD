package main.java.splitstrategy;

import main.java.Split;
import main.java.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    List<Split> calculateSplits(Map<User, Double> splits, double totalAmount);
}
