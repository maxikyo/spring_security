package com.holovanovmax.springboot_security_api.rest_api.model

import jakarta.validation.constraints.NotEmpty

class UserData implements Serializable{

    @NotEmpty(message = "name can not be empty")
    String name

    @NotEmpty(message = "password can not be empty")
    String password

}
