package com.example.market.Services.ImplementationServices;

import com.example.market.Services.TransactionService;
import com.example.market.models.Product;
import com.example.market.models.Transaction;
import com.example.market.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }
}
