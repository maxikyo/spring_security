package com.holovanovmax.springboot_security_api.rest_api.model.userInformation

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class UserDto {


    String id

    @NotNull
    @NotEmpty(message = "name can not be empty")
    String name

    @NotNull
    @NotEmpty(message = "password can not be empty")
    String password

    @NotNull
    @NotEmpty(message = "role can not be empty")
    String role

//    @NotNull
//    BigDecimal balance = new BigDecimal("0")

}
