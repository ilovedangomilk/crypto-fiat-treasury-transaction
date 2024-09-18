package com.example.crypto_fiat_transaction_demo.Transactions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="crypto-treasury-transaction", schema="crypto-fiat-exchange-demo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid; // Reference to the user
    private String source;
    private String target;
    private double amount;
    private double conversion_rate;
    private Timestamp timestamp;
}
