package com.holovanovmax.springboot_security_api.rest_api.model.userInformation

import com.holovanovmax.springboot_security_api.rest_api.model.userBalance.UserBalance
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

    @Version
    Long version = 0L

}
