package com.holovanovmax.springboot_security_api.rest_api.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
class User {

    @Id
    private String id

    String name

    String email

}
