package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.userBalance.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserBalance
import com.holovanovmax.springboot_security_api.rest_api.data.reposiroty.BalanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BalanceServiceImpl implements BalanceService {

    @Autowired
    UserServiceImpl userService

    @Autowired
    private BalanceRepository balancesRepository


    @Override
    UserBalance saveUserBalance(UserBalance userBalance) {
        balancesRepository.save(userBalance)
    }

    @Override
    UserBalance getUserBalance(String userId) {                                         //создает нулевой баланс.//нужно создавать сразу
        return balancesRepository.findByUserId(userId).orElse(balancesRepository.save(new UserBalance(userId: userId,
                balance: 0 )))
    }

    UserBalance updateUserBalance(String userId, BalanceOperation balanceOperation, BigDecimal amount) {
//        User user = userService.isExist(userId) <- deleted //запрос базы данных
        if (userService.isExist(userId)) {
            UserBalance userBalance = getUserBalance(userId)
            if (balanceOperation == BalanceOperation.PLUS && amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than 0 for addition.")
            }
            if (balanceOperation == BalanceOperation.PLUS) {
                userBalance.balance = userBalance.balance + amount
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