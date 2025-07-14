package main.java.service;

import main.java.model.Transaction;
import main.java.model.TransactionFilterCreteria;
import main.java.repository.TransactionRepository;

import java.util.List;

public class TranactionService {
    private final TransactionRepository transactionRepository;

    public TranactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public synchronized List<Transaction> getFilteredTransactions(TransactionFilterCreteria criteria) {
        List<Transaction> allTransactions = transactionRepository.findAllTransaction();
        return FilterService.filterTransactions(allTransactions, criteria);
    }

    public synchronized void addTransaction(Transaction transaction){
        transactionRepository.addTranction(transaction);
    }
}
