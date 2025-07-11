import main.java.Expense;
import main.java.Group;
import main.java.SplitwiseService;
import main.java.User;
import main.java.splitstrategy.SplitType;

import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SplitwiseService splitwiseService = SplitwiseService.getSplitwiseServiceInstance();

        // Create users
        User user1 = splitwiseService.createUser("Alice", "alice@example.com");
        User user2 = splitwiseService.createUser("Bob", "bob@example.com");
        User user3 = splitwiseService.createUser("Charlie", "charlie@example.com");

        // Create a group
        Group group = splitwiseService.createGroup("Apartment",
                List.of(user1.getUserId(), user2.getUserId(), user3.getUserId()));

        // Add group expense
        Expense rentExpense = splitwiseService.addExpense("Rent", 9000.0, user1.getUserId(), group.getGroupId(), SplitType.EQUAL, Map.of(
                user1.getUserId(), 3000.0,
                user2.getUserId(), 3000.0,
                user3.getUserId(), 3000.0
        ));

        // Add non-group expense (groupId = null)
        Expense dinnerExpense = splitwiseService.addExpense("Dinner", 600.0, user2.getUserId(),
                null, SplitType.EXACT,
                Map.of(
                        user1.getUserId(), 400.0,
                        user2.getUserId(), 200.0
                ));

        // Print user balances
        System.out.println("\nPrinting balance for each user:");
        splitwiseService.printBalance();

        // Settle balances
        System.out.println("\nSettling balances between users");
        splitwiseService.settle(user2.getUserId(), user1.getUserId(), 2000.0);
        splitwiseService.settle(user3.getUserId(), user1.getUserId(), 3000.0);

        // Print user balances
        System.out.println("\nPrinting balance for each user:");
        splitwiseService.printBalance();

        splitwiseService.deleteExpense(dinnerExpense.getExpenseId());

        // Print user balances
        System.out.println("\nPrinting balance for each user:");
        splitwiseService.printBalance();

        // Print user balance
        System.out.println("\nPrinting balance for: " + user1.getName());
        splitwiseService.printBalanceForUser(user1.getUserId());

    }
}