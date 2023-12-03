package com.holovanovmax.springboot_security_api.rest_api.data.reposiroty

import com.holovanovmax.springboot_security_api.rest_api.data.domains.UserBalance
import org.springframework.data.mongodb.repository.MongoRepository

interface BalanceRepository extends MongoRepository <UserBalance, String>{
    Optional<UserBalance> findByUserId(String userId)
}