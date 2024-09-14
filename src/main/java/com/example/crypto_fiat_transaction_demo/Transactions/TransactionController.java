package com.example.crypto_fiat_transaction_demo.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/convert")
    // @RequestBody for json request, @RequestParam for request within the URL
    // If we want to do a @RequestBody, we need a DTO (data transfer object) which is a class
    public ResponseEntity<Transaction> performTransaction(@RequestBody TransactionRequest transactionRequest){
        Transaction transaction = transactionService.performTransaction(transactionRequest.getUserId(), transactionRequest.getSource(), transactionRequest.getTarget(), transactionRequest.getAmount());
        return ResponseEntity.ok(transaction);
    }
}
