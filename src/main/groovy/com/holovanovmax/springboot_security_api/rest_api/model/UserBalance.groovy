package com.holovanovmax.springboot_security_api.rest_api.model

import org.springframework.data.mongodb.core.mapping.Document

@Document("userBalance")
class UserBalance {

    id - индексация

    bigdecimal

    version

    userId = user.id // ошибка конфликт версий монго. учесть эту ошибку
}
