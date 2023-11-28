package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.userBalance.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.userBalance.UserBalance
import com.holovanovmax.springboot_security_api.rest_api.model.userInformation.User
import com.holovanovmax.springboot_security_api.rest_api.repository.BalancesRepository
//import com.holovanovmax.springboot_security_api.rest_api.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BalanceServiceImpl implements BalanceService {

    @Autowired
    UserServiceImpl userService

    @Autowired
    private BalancesRepository balancesRepository


    @Override
    UserBalance saveUserBalance(UserBalance userBalance) {
        balancesRepository.save(userBalance)
    }

    @Override
    UserBalance getUserBalance(String userId) {
        return balancesRepository.findById(userId).orElse(null)
    }
//    @Override
//    User updateUserBalance(String id, BalanceOperation balanceOperation, BigDecimal amount) {
//        User user = userService.getUser(id)
//        if (user){
//            if(balanceOperation == BalanceOperation.PLUS){
//                user.balance = user.balance + amount
//            }
//            if (balanceOperation == BalanceOperation.PLUS && amount <= 0){
//                throw new IllegalArgumentException("Amount must be greater than 0 for addition.");
//            }
//
//            if(balanceOperation == BalanceOperation.MINUS){
//                if (amount <= 0) {
//                    throw new IllegalArgumentException("Amount must be greater than 0 for subtraction.")
//                }
//                if(amount > user.balance){
//                    throw new IllegalArgumentException("Balance is ${user.balance} less then ${amount}")
//                }
//                user.balance = user.balance - amount
//            }
//            return userService.saveUser(user)
//        }else
//            throw new IllegalArgumentException("User with ${id} did not found")
//    }

    UserBalance updateUserBalance(String userId, BalanceOperation balanceOperation, BigDecimal amount) {
        User user = userService.getUser(userId)
        if (user) {
            UserBalance userBalance = user.balances.find {
                it.userId = user.id
            }
            if (balanceOperation == BalanceOperation.PLUS) {
                userBalance.balance = userBalance.balance + amount
            }
            if (balanceOperation == BalanceOperation.PLUS && amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than 0 for addition.")
            }

            if (balanceOperation == BalanceOperation.MINUS) {
                if (amount <= 0) {
                    throw new IllegalArgumentException("Amount must be greater than 0 for subtraction.")
                }
                if (amount > userBalance.balance) {
                    throw new IllegalArgumentException("Balance is ${userBalance.balance} less then ${amount}")
                }
                userBalance.balance = userBalance.balance - amount
            }
            return balancesRepository.save(userBalance)
        } else
            throw new IllegalArgumentException("User with ${userId} did not found")

    }
}