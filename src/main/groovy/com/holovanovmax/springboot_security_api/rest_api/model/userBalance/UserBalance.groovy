package com.holovanovmax.springboot_security_api.rest_api.model.userBalance

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document

@Document("userBalance")
class UserBalance {

    @Id
    String id  //индексация

    BigDecimal balance = new BigDecimal("0")

    String userId

    @Version
    Long version = 0L
}


//// ошибка конфликт версий монго. учесть эту ошибку