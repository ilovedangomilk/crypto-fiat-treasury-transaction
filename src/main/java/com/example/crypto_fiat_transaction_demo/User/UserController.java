package com.example.crypto_fiat_transaction_demo.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user.getName());
    }

    @PostMapping("/change")
    public User changeCurrencyToUserAccount(@RequestBody ChangeCurrency changeCurrency){
        return userService.changeCurrencyToUserAccount(changeCurrency.getId(), changeCurrency.getCurrency(), changeCurrency.getAmount(), changeCurrency.getType());
    }
}

