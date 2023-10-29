package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User
import com.holovanovmax.springboot_security_api.rest_api.repository.UsersRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest
class UserServiceTest extends Specification{

    @Autowired
    UserServiceImpl userService

    @Autowired
    UsersRepository usersRepository

    @Test
    void "add money to userBalance"(){
        given:

        User user = userService.getUser("64dbc0d899b0d0031c739f81")
//        given:
//        User user = userService.registerNewUser(new User(
//                id: "test5",
//                name: "name15",
//                password: "pass2",
//                role: "USER",
//                balance: "0"
//        ))
        when:
        user = userService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))

        then:
        user.balance == new BigDecimal("10")
    }
}



