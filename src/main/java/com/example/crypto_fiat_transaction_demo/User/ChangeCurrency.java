package com.example.crypto_fiat_transaction_demo.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCurrency {
    private Long id;
    private String currency;
    private double amount;
    private String type;
}
