package com.example.crypto_fiat_transaction_demo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
// Makes the interface as a Spring Data Repository, making it a Spring Bean. It tells Spring that this interface will be used for CRUD on User
// Optional because Spring will recognise this as a repository
public interface UserRepository extends JpaRepository<User, Long> {
    // By extending JpaRepository, it inherits methods for working with User persistence
    // Methods such as findAll(), findById(), so we dont have to write sql queries
}
