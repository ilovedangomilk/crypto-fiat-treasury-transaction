package com.example.crypto_fiat_transaction_demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String name){
        User user = new User();
        user.setName(name);
        user.setUSD(0.0);
        user.setBitcoin(0.0);
        user.setEthereum(0.0);
        user.setBNB(0.0);
        return userRepository.save(user);
    }
}
