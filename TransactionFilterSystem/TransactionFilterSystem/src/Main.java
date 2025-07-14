import main.java.controller.FilterController;
import main.java.model.Transaction;
import main.java.model.TransactionFilterCreteria;
import main.java.repository.TransactionRepository;
import main.java.service.TranactionService;

import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new TransactionRepository();
        TransactionFilterCreteria creteria = new TransactionFilterCreteria.Builder().setCustomerId("C1").setMinAmount(100).build();
        FilterController filterController = new FilterController(new TranactionService(transactionRepository));
        // add txns
        filterController.addTransaction(new Transaction("T1", "c1", new Date(), 100));
        filterController.addTransaction(new Transaction("T2", "C1", new Date(), 200));
        filterController.addTransaction(new Transaction("T3", "C2", new Date(), 300));
        filterController.addTransaction(new Transaction("T4", "C1", new Date(), 300));
        filterController.addTransaction(new Transaction("T5", "C1", new Date(), 200));
        filterController.addTransaction(new Transaction("T6", "C2", new Date(), 300));
        // get all txns
        Runnable task = () -> {
            List<Transaction> res = filterController.getFilteredTxn(creteria);
            System.out.println("Thread " + Thread.currentThread().getName() + ": Filtered Transactions:");
            for(Transaction transaction: res){
                System.out.println("Transaction ID: " + transaction.getTransactionId() +
                        ", Customer ID: " + transaction.getCustomerId() +
                        ", Amount: " + transaction.getAmount() +
                        ", Timestamp: " + transaction.getTimeStamp());
            }
        };

        Thread thread1 = new Thread(task, "1");
        Thread thread2 = new Thread(task, "2");

        thread1.start();
        thread2.start();

    }
}