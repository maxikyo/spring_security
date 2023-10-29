package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest
class UserServiceTest extends Specification {

    @Autowired
    private UserServiceImpl userService

    @Test
    void "add money to userBalance"(){
        given:
        User user = userService.registerNewUser(new User(
                name: "name1",
                password: "pass1",
                role: "USER"
        ))
        when:
        user = userService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))

        then:
        user.balance == new BigDecimal("10")
    }
}




