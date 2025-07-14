package main.java.controller;

import main.java.model.Transaction;
import main.java.model.TransactionFilterCreteria;
import main.java.repository.TransactionRepository;
import main.java.service.FilterService;
import main.java.service.TranactionService;

import java.util.List;

public class FilterController {
    private TranactionService tranactionService;


    public FilterController(TranactionService tranactionService) {
        this.tranactionService = tranactionService;
    }

    public List<Transaction> getFilteredTxn(TransactionFilterCreteria creteria){
        return tranactionService.getFilteredTransactions(creteria);
    }

    public void addTransaction(Transaction transaction){
        tranactionService.addTransaction(transaction);
    }
}
