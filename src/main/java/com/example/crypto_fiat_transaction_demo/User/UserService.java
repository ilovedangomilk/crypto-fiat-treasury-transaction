package com.example.crypto_fiat_transaction_demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
// Where the business logic is
public class UserService {
    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(String name){
        User user = new User();
        user.setName(name);
        user.setUSD(0.0);
        user.setBitcoin(0.0);
        user.setEthereum(0.0);
        user.setBNB(0.0);
        return userRepository.save(user);
    }

    public User changeCurrencyToUserAccount(Long id, String currency, double amount, String type) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Switch case for currency type
        if (type.equals("add")) {
            switch (currency.toLowerCase()) {
                case "usd":
                    user.setUSD(user.getUSD() + amount);
                    break;
                case "bitcoin":
                    user.setBitcoin(user.getBitcoin() + amount);
                    break;
                case "ethereum":
                    user.setEthereum(user.getEthereum() + amount);
                    break;
                case "bnb":
                    user.setBNB(user.getBNB() + amount);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported Currency Type");
            }
            return userRepository.save(user);
        } else if (type.equals("subtract")) {
            switch (currency.toLowerCase()) {
                case "usd":
                    double finalUSDAmount = user.getUSD() - amount;
                    if (finalUSDAmount < 0){
                        throw new IllegalArgumentException("USD cannot be less than zero!");
                    } else {
                        user.setUSD(finalUSDAmount);
                    }
                    break;
                case "bitcoin":
                    double finalBitcoinAmount = user.getBitcoin() - amount;
                    if (finalBitcoinAmount < 0){
                        throw new IllegalArgumentException("Bitcoin cannot be less than zero!");
                    } else {
                        user.setBitcoin(finalBitcoinAmount);
                    }
                    break;
                case "ethereum":
                    double finalEthereumAmount = user.getEthereum() - amount;
                    if (finalEthereumAmount < 0){
                        throw new IllegalArgumentException("Ethereum cannot be less than zero!");
                    } else {
                        user.setEthereum(finalEthereumAmount);
                    }
                    break;
                case "bnb":
                    double finalBnbAmount = user.getBNB() - amount;
                    if (finalBnbAmount < 0) {
                        throw new IllegalArgumentException("BNB cannot be less than zero!");
                    } else {
                        user.setBNB(finalBnbAmount);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported Currency Type");
            }
            return userRepository.save(user);
        }
        return userRepository.save(user);
    }
}
