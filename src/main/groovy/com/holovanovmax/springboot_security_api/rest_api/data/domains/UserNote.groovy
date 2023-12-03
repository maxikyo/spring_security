package com.holovanovmax.springboot_security_api.rest_api.data.domains

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document

@Document("userNotes")
class UserNote {

    @Id
    String id

    Date date = new Date()

    String content

    String userId

    Boolean isPublic

    @Version
    Long version

}
