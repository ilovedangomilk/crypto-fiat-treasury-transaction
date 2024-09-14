package com.example.crypto_fiat_transaction_demo.Transactions;


import com.example.crypto_fiat_transaction_demo.User.User;
import com.example.crypto_fiat_transaction_demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Timestamp;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Do not forget break statements
    public Transaction performTransaction(Long userid, String source, String target, double amount){
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));

        double conversionRate = getConversionRate(source, target);

        double convertedAmount = amount * conversionRate;

        switch (source.toLowerCase()){
            case "usd":
                double finalUsdAmount = user.getUSD()-amount;
                if (finalUsdAmount < 0) {
                    throw new IllegalArgumentException("Insufficient USD!");
                } else {
                    user.setUSD(finalUsdAmount);
                }
                break;
            case "bitcoin":
                double finalBitcoinAmount = user.getBitcoin()-amount;
                if (finalBitcoinAmount < 0) {
                    throw new IllegalArgumentException("Insufficient BITCOIN!");
                } else {
                    user.setUSD(finalBitcoinAmount);
                }
                break;
            case "etherueum":
                double finalEthereumAmount = user.getEthereum()-amount;
                if (finalEthereumAmount < 0) {
                    throw new IllegalArgumentException("Insufficient Ethereum!");
                } else {
                    user.setEthereum(finalEthereumAmount);
                }
                break;
            case "bnb":
                double finalBnbAmount = user.getBNB()-amount;
                if (finalBnbAmount < 0) {
                    throw new IllegalArgumentException("Insufficient BNB!");
                } else {
                    user.setBNB(finalBnbAmount);
                }
                break;
        }
        switch (target.toLowerCase()){
            case "usd":
                user.setUSD(user.getUSD()+convertedAmount);
                break;
            case "bitcoin":
                user.setBitcoin(user.getBitcoin()+convertedAmount);
                break;
            case "ethereum":
                user.setEthereum(user.getEthereum()+convertedAmount);
                break;
            case "bnb":
                user.setBNB(user.getBNB()+convertedAmount);
                break;
        }
        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setUserid(userid);
        transaction.setSource(source);
        transaction.setTarget(target);
        transaction.setAmount(amount);
        transaction.setConversion_rate(conversionRate);
        transaction.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));

        return transactionRepository.save(transaction);
    }
    public double getConversionRate(String source, String target){
        // Step 1: Define CoinGecko api url
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,binancecoin&vs_currencies=usd";

        // Step 2: Make the API call and capture the response
        ResponseEntity<Map<String, Map<String, Double>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Map<String, Double>>>() {}
        );
//        System.out.println("Response body: " + response.getBody());
        // Step 3: Parse the response and extract the conversion rates
        // Check if the response body is null

        Map<String, Map<String, Double>> rates = response.getBody();

        // Check if rates map is not null
        if (rates != null) {
            System.out.println(rates);  // Should print: {binancecoin={usd=551.63}, bitcoin={usd=59708.0}, ethereum={usd=2416.38}}
        }

        double sourceRateInUsd = 1.0;
        double targetRateInUsd = 1.0;

        if (target.toLowerCase().equals("bnb")){
            target = "binancecoin";
        }

        if (!source.equalsIgnoreCase("usd")){
            sourceRateInUsd = rates.get(source.toLowerCase()).get("usd");
        }

        if (!target.equalsIgnoreCase("usd")){
            targetRateInUsd = rates.get(target.toLowerCase()).get("usd");
        }

        return sourceRateInUsd/targetRateInUsd;
    }
}