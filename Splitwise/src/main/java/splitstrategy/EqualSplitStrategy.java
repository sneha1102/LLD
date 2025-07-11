package main.java.splitstrategy;

import main.java.Split;
import main.java.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplits(Map<User, Double> splits, double totalAmount) {
        List<Split> splitData = new ArrayList<>();
        int noOfUser = splits.size();
        double equalAmt = totalAmount / noOfUser;

        for(User user: splits.keySet()){
            splitData.add(new Split(user, equalAmt));
        }
        return splitData;
    }
}
