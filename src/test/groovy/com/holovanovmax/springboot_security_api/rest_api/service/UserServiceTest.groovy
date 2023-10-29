package com.holovanovmax.springboot_security_api.rest_api.service

import com.holovanovmax.springboot_security_api.rest_api.RestApiApplication
import com.holovanovmax.springboot_security_api.rest_api.model.BalanceOperation
import com.holovanovmax.springboot_security_api.rest_api.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import spock.lang.Specification

@SpringJUnitConfig(classes = RestApiApplication.class)
@SpringBootTest
class UserServiceTest extends Specification {

    @MockBean
    private UserServiceImpl userService

    def "add money to userBalance"() {
        given:
        User user = userService.registerNewUser(new User(
                name: "name1",
                password: "pass1",
                role: "USER"
        ))

        when:
        User updatedUser = userService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))

        then:
        updatedUser.balance == new BigDecimal("10")
    }
}


//@SpringBootTest
//class UserServiceTest extends Specification {
//
//
//    @Autowired
//    private UserServiceImpl userService
//
//
//    def "add money to userBalance"() {
//        given:
//        User user = userService.registerNewUser(new User(
//                name: "name1",
//                password: "pass1",
//                role: "USER"
//        ))
//        when:
//        user = userService.updateUserBalance(user.id, BalanceOperation.PLUS, new BigDecimal("10"))
//
//        then:
//        user.balance == new BigDecimal("10")
//    }
//}




