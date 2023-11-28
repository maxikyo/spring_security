package com.holovanovmax.springboot_security_api.rest_api.repository

import com.holovanovmax.springboot_security_api.rest_api.model.userBalance.UserBalance
import org.springframework.data.mongodb.repository.MongoRepository

interface BalancesRepository extends MongoRepository <UserBalance, String>{

}