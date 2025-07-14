package main.java.service;

import main.java.model.Transaction;
import main.java.model.TransactionFilterCreteria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilterService {
    public static List<Transaction> filterTransactions(List<Transaction> transactions, TransactionFilterCreteria transactionFilterCreteria){
        Predicate<Transaction> predicate = transaction -> true;
        if(transactionFilterCreteria.getCustomerId() != null){
            predicate = predicate.and(transaction -> transaction.getCustomerId().equals(transactionFilterCreteria.getCustomerId()));
        }
        if(transactionFilterCreteria.getMinAmount() != 0){
            predicate = predicate.and(transaction -> transaction.getAmount() >= transactionFilterCreteria.getMinAmount());
        }

        if(transactionFilterCreteria.getMaxAmount() != 0){
            predicate = predicate.and(transaction -> transaction.getAmount() <= transactionFilterCreteria.getMaxAmount());
        }
        if(transactionFilterCreteria.getStartDate() != null){
            predicate = predicate.and(transaction -> transaction.getTimeStamp().before(transactionFilterCreteria.getStartDate()));
        }
        if(transactionFilterCreteria.getEndDate() != null){
            predicate = predicate.and(transaction -> transaction.getTimeStamp().after(transactionFilterCreteria.getEndDate()));
        }
        List<Transaction> res = new ArrayList<>();
        for(Transaction transaction: transactions){
            if(predicate.test(transaction)){
                res.add(transaction);
            }
        }
        return res;
    }
}
