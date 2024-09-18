package com.example.crypto_fiat_transaction_demo.Transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Long userId;
    private String source;
    private String target;
    private double amount;
}
