package main.java.repository;

import main.java.model.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions = Collections.synchronizedList(new ArrayList<>());

    public synchronized List<Transaction> findAllTransaction(){
        return transactions;
    }

    public synchronized void addTranction(Transaction transaction){
        transactions.add(transaction);
    }
}
