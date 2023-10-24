package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.repository.UsersRepository
import org.mockito.Mock
import spock.lang.Specification
import spock.lang.Subject


class UserServiceTest extends Specification {

    @Subject
    UserServiceImpl userService

    @Mock
    UsersRepository usersRepository


    def "add money to userBalance"(){
        given:
        User user = userService.getUser("64dbc0d899b0d0031c739f81")

        when:

    }
}

