package com.holovanovmax.springboot_security_api.rest_api.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
class User {

    @Id
    String id  // у баланса заиндексировать юзер айди/версия, создать Юзер айди UserId, в балансе создаешь юзер айди.
    // или

    String name

    String password

    String role

    BigDecimal balance = new BigDecimal("0") //создать отдельную сущность + базу и соеленить

    @Version
    Long version = 0L

}
