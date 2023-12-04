package com.holovanovmax.springboot_security_api.rest_api.model.userNote

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class NoteCreateDto {

    @NotNull
    @NotEmpty(message = "content cannot be empty")
    String content

    Boolean isPublic = false
}
