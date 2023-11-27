package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BalanceServiceImpl implements BalanceService{

    @Autowired
    UserServiceImpl userService

    @Override
    User updateUserBalance(String id, BalanceOperation balanceOperation, BigDecimal amount) {
        User user = userService.getUser(id)
        if (user){
            if(balanceOperation == BalanceOperation.PLUS){
                user.balance = user.balance + amount
            }
            if (balanceOperation == BalanceOperation.PLUS && amount <= 0){
                throw new IllegalArgumentException("Amount must be greater than 0 for addition.");
            }

            if(balanceOperation == BalanceOperation.MINUS){
                if (amount <= 0) {
                    throw new IllegalArgumentException("Amount must be greater than 0 for subtraction.")
                }
                if(amount > user.balance){
                    throw new IllegalArgumentException("Balance is ${user.balance} less then ${amount}")
                }
                user.balance = user.balance - amount
            }
            return userService.saveUser(user)
        }else
            throw new IllegalArgumentException("User with ${id} did not found")
    }
}
