package main.java;

import main.java.splitstrategy.SplitStrategy;
import main.java.splitstrategy.SplitStrategyFactory;
import main.java.splitstrategy.SplitType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SplitwiseService {
    private static volatile SplitwiseService splitwiseServiceInstance;
    private Map<String, User> userMap;
    private Map<String, Expense> expenseMap;
    private Map<String, Group> groupMap;
    private BalanceSheet balanceSheet;

    public SplitwiseService() {
        this.userMap = new ConcurrentHashMap<>();
        this.expenseMap = new ConcurrentHashMap<>();
        this.groupMap = new ConcurrentHashMap<>();
        this.balanceSheet = new BalanceSheet();
    }
    public static synchronized SplitwiseService getSplitwiseServiceInstance(){
        if(splitwiseServiceInstance == null){
            return new SplitwiseService();
        }
        return splitwiseServiceInstance;
    }

    public User createUser(String name, String email){
        User user = new User(name, email);
        userMap.put(user.getUserId(), user);
        return user;
    }
    public Group createGroup(String name, List<String> userIds){
        List<User> members = userIds.stream().map(userMap::get).collect(Collectors.toList());
        Group group = new Group(name, members);
        groupMap.put(group.getGroupId(), group);
        return  group;
    }

    public Expense addExpense(String description, double amount, String paidByUserId, String groupId,
                              SplitType splitType, Map<String, Double> splitData){
        User paidBy = userMap.get(paidByUserId);
        Group group = groupId == null ? null: groupMap.get(groupId);
        Map<User, Double> userSplitData = new HashMap<>();
        for(Map.Entry<String, Double> entry: splitData.entrySet()){
            userSplitData.put(userMap.get(entry.getKey()), entry.getValue());
        }
        SplitStrategy splitStrategy = SplitStrategyFactory.getSplitStrategy(splitType);
        List<Split> splits = splitStrategy.calculateSplits(userSplitData, amount);

        Expense expense = new Expense.Builder()
                .description(description)
                .amount(amount)
                .paidBy(paidBy)
                .group(group)
                .addSplits(splits)
                .build();

        expenseMap.put(expense.getExpenseId(), expense);
        balanceSheet.updateBalance(paidBy, splits);
        return expense;
    }

    public void settle(String fromUserId, String toUserId, double amount){
        User fromUser = userMap.get(fromUserId);
        User toUser = userMap.get(toUserId);
        balanceSheet.settleAmount(fromUser, toUser, amount);
    }

    public void printBalance(){
        balanceSheet.printbalance();
    }
    public synchronized void deleteExpense(String expenseId){
        Expense expense = expenseMap.get(expenseId);
        if(expense != null){
            // Reverse the balance updates
            List<Split> reverseSplits = expense.getSplits().stream().map(split -> new Split(split.getUser(), -split.getAmount())).collect(Collectors.toList());
            balanceSheet.updateBalance(expense.getPaidBy(), reverseSplits);
        }
    }
    public void printBalanceForUser(String userId) {
        balanceSheet.printBalanceForUser(userMap.get(userId));
    }
}
