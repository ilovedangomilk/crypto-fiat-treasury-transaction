package com.example.crypto_fiat_transaction_demo.Transactions;


import com.example.crypto_fiat_transaction_demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Transaction performTransaction(Long userid, String source, String target, double amount, Timestamp timestamp){
        Transaction transaction = new Transaction();
        return transactionRepository.save(transaction);
    }
}