package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.userBalance.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserBalance

interface BalanceService {
    UserBalance updateUserBalance(String userId, BalanceOperation balanceOperation, BigDecimal amount)

    UserBalance saveUserBalance (UserBalance userBalance)

    UserBalance getUserBalance (String userId)
}