package com.example.crypto_fiat_transaction_demo.User;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //Marks this class as a JPA entity, which will be mapped to a table in the database
@Table(name= "crypto-treasury", schema="crypto-fiat-exchange-demo") // Specifies the table to map it to
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id // Marks the id as the primary key of the entity (corresponding to the id column in the table)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto assign id
    private Long id;
    private String name;
    private double USD;
    private double Bitcoin;
    private double Ethereum;
    private double BNB;
}
