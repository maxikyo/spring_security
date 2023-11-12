package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User

interface BalanceService {
    User updateUserBalance(String id, BalanceOperation balanceOperation, BigDecimal amount)
}