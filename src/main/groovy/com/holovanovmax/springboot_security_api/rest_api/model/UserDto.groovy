package com.holovanovmax.springboot_security_api.rest_api.model

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class UserDto {

    @NotNull
    @NotEmpty(message = "name can not be empty")
    String name

    @NotNull
    @NotEmpty(message = "password can not be empty")
    String password

    @NotNull
    @NotEmpty(message = "name can not be empty")
    String role

}
